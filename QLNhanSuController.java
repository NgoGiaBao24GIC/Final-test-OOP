package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import model.PhongBan;
import model.DanhSachPhongBan;
import view.QLNhanSuView;

public class QLNhanSuController implements ActionListener {
	private QLNhanSuView view;

	public QLNhanSuController(QLNhanSuView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		try {
			switch (command) {
			case "Thêm":
				handleThem();
				break;
			case "Lưu":
				handleLuu();
				break;
			case "Cập nhật":
				handleCapNhat();
				break;
			case "Xóa":
				handleXoa();
				break;
			case "About Me":
				JOptionPane.showMessageDialog(view, "Quản Lý Nhân Sự v1.0\nAuthor: Ngo Gia Bao");
				break;
			case "Exit":
				handleExit();
				break;
			case "Save":
				handleSave();
				break;
			case "Open":
				handleOpen();
				break;
			default:
				JOptionPane.showMessageDialog(view, "Chức năng không được hỗ trợ: " + command);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	private void handleThem() {

		view.textField_ID.setText("");
		view.textField_HoVaTen.setText("");
		view.textField_NgaySinh.setText("");
		view.textField_Luong.setText("");
		view.textField_DiaChi.setText("");
		view.btn_gioiTinh.clearSelection();
		view.comboBox_phongBan.setSelectedIndex(0);
		updateTable();
	}

	private void handleLuu() {
		if (!validateInput()) {
			return;
		}

		try {

			NhanVien nv = new NhanVien();
			nv.setMaNhanVien(Integer.parseInt(view.textField_ID.getText()));
			nv.setTenNhanVien(view.textField_HoVaTen.getText());
			try {
				nv.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(view.textField_NgaySinh.getText().trim()));
			} catch (ParseException e) {

				e.printStackTrace();
			}
			nv.setGioiTinh(view.radioButton_nam.isSelected());
			nv.setLuong(Float.parseFloat(view.textField_Luong.getText().trim()));
			nv.setDiaChi(view.textField_DiaChi.getText());

			String selectedPhongBanName = (String) view.comboBox_phongBan.getSelectedItem();
			PhongBan selectedPhongBan = null;
			for (PhongBan pb : DanhSachPhongBan.getDSPB()) {
				if (pb.getTenPhongBan().equals(selectedPhongBanName)) {
					selectedPhongBan = pb;
					break;
				}
			}

			if (selectedPhongBan == null) {
				throw new IllegalStateException("Không tìm thấy phòng ban đã chọn");
			}

			nv.setPhongBan(selectedPhongBan);

			view.model.getDsNhanVien().add(nv);

			updateTable();

			handleThem();

			JOptionPane.showMessageDialog(view, "Thêm nhân viên thành công!");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(view, "Mã nhân viên và lương phải là số!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void handleCapNhat() {

		int selectedRow = view.table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên cần cập nhật!");
			return;
		}

		DefaultTableModel model = (DefaultTableModel) view.table.getModel();
		view.textField_ID.setText(model.getValueAt(selectedRow, 0).toString());
		view.textField_HoVaTen.setText(model.getValueAt(selectedRow, 1).toString());
		view.comboBox_phongBan.setSelectedItem(model.getValueAt(selectedRow, 2));
		view.textField_NgaySinh.setText(model.getValueAt(selectedRow, 3).toString());

		String gioiTinh = model.getValueAt(selectedRow, 4).toString();
		if (gioiTinh.equals("Nam")) {
			view.radioButton_nam.setSelected(true);
		} else {
			view.radioButton_nu.setSelected(true);
		}

		view.textField_Luong.setText(model.getValueAt(selectedRow, 5).toString());
		view.textField_DiaChi.setText(model.getValueAt(selectedRow, 6).toString());

		int maNV = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
		view.model.getDsNhanVien().removeIf(nv -> nv.getMaNhanVien() == maNV);
		model.removeRow(selectedRow);

		JOptionPane.showMessageDialog(view, "Bạn có thể cập nhật thông tin và bấm 'Lưu' để hoàn tất!");
	}

	private void handleXoa() {
		int selectedRow = view.table.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên cần xóa!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa nhân viên này?", "Xác nhận xóa",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			DefaultTableModel model = (DefaultTableModel) view.table.getModel();
			int maNV = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

			view.model.getDsNhanVien().removeIf(nv -> nv.getMaNhanVien() == maNV);

			try {
				Connection conn = view.model.getConnection();
				if (conn == null) {
					JOptionPane.showMessageDialog(view, "Không thể kết nối tới cơ sở dữ liệu!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String sql = "DELETE FROM `nhanvien` WHERE `maNhanVien` = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, maNV);
				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(view, "Xóa nhân viên thành công!");
				} else {
					JOptionPane.showMessageDialog(view, "Không tìm thấy nhân viên trong cơ sở dữ liệu!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(view, "Lỗi khi xóa nhân viên: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			model.removeRow(selectedRow);
			handleThem();
		}
	}

	private void handleExit() {
		int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn thoát chương trình?", "Xác nhận thoát",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private void handleSave() {
		try {
			Connection conn = view.model.getConnection();
			if (conn == null) {
				JOptionPane.showMessageDialog(view, "Chưa kết nối tới cơ sở dữ liệu!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String insertSQL = "INSERT INTO `nhanvien` (maNhanVien, hoTen, phongBan, ngaySinh, gioiTinh, luong, diaChi) VALUES (?, ?, ?, ?, ?, ?, ?)";
			String updateSQL = "UPDATE `nhanvien` SET hoTen = ?, phongBan = ?, ngaySinh = ?, gioiTinh = ?, luong = ?, diaChi = ? WHERE maNhanVien = ?";
			PreparedStatement insertPstmt = conn.prepareStatement(insertSQL);
			PreparedStatement updatePstmt = conn.prepareStatement(updateSQL);

			for (NhanVien nv : view.model.getDsNhanVien()) {

				String checkSQL = "SELECT COUNT(*) FROM `nhanvien` WHERE `maNhanVien` = ?";
				PreparedStatement checkPstmt = conn.prepareStatement(checkSQL);
				checkPstmt.setInt(1, nv.getMaNhanVien());
				ResultSet rs = checkPstmt.executeQuery();
				rs.next();

				if (rs.getInt(1) > 0) {

					updatePstmt.setString(1, nv.getTenNhanVien());
					updatePstmt.setString(2, nv.getPhongBan().getTenPhongBan());
					updatePstmt.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
					updatePstmt.setBoolean(4, nv.isGioiTinh());
					updatePstmt.setFloat(5, nv.getLuong());
					updatePstmt.setString(6, nv.getDiaChi());
					updatePstmt.setInt(7, nv.getMaNhanVien());
					updatePstmt.addBatch();
				} else {

					insertPstmt.setInt(1, nv.getMaNhanVien());
					insertPstmt.setString(2, nv.getTenNhanVien());
					insertPstmt.setString(3, nv.getPhongBan().getTenPhongBan());
					insertPstmt.setDate(4, new java.sql.Date(nv.getNgaySinh().getTime()));
					insertPstmt.setBoolean(5, nv.isGioiTinh());
					insertPstmt.setFloat(6, nv.getLuong());
					insertPstmt.setString(7, nv.getDiaChi());
					insertPstmt.addBatch();
				}
			}

			insertPstmt.executeBatch();
			updatePstmt.executeBatch();
			JOptionPane.showMessageDialog(view, "Lưu dữ liệu thành công vào cơ sở dữ liệu!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Lỗi khi lưu dữ liệu: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void handleOpen() {
		try {
			Connection conn = view.model.getConnection();
			if (conn == null) {
				JOptionPane.showMessageDialog(view, "Chưa kết nối tới cơ sở dữ liệu!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String sql = "SELECT * FROM `nhanvien`";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			view.model.getDsNhanVien().clear();
			DefaultTableModel modelTable = (DefaultTableModel) view.table.getModel();
			modelTable.setRowCount(0);

			while (rs.next()) {
				int maNhanVien = rs.getInt("maNhanVien");
				String phongBan = rs.getString("phongBan");
				String hoTen = rs.getString("hoTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				float luong = rs.getFloat("luong");
				String diaChi = rs.getString("diaChi");

				PhongBan pb = DanhSachPhongBan.getPhongBanByTen(phongBan);
				if (pb == null) {
					pb = new PhongBan(-1, phongBan);
				}

				NhanVien nv = new NhanVien(maNhanVien, hoTen, pb, ngaySinh, gioiTinh, luong, diaChi);
				view.model.getDsNhanVien().add(nv);

				modelTable.addRow(
						new Object[] { maNhanVien, hoTen, phongBan, ngaySinh, gioiTinh ? "Nam" : "Nữ", luong, diaChi });
			}

			JOptionPane.showMessageDialog(view, "Tải dữ liệu thành công từ cơ sở dữ liệu!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean validateInput() {
		if (view.textField_ID.getText().trim().isEmpty() || view.textField_HoVaTen.getText().trim().isEmpty()
				|| view.textField_NgaySinh.getText().trim().isEmpty() || view.textField_Luong.getText().trim().isEmpty()
				|| view.textField_DiaChi.getText().trim().isEmpty() || view.comboBox_phongBan.getSelectedIndex() == 0
				|| (!view.radioButton_nam.isSelected() && !view.radioButton_nu.isSelected())) {

			JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
			return false;
		}
		return true;
	}

	private void updateTable() {
		DefaultTableModel model = (DefaultTableModel) view.table.getModel();
		model.setRowCount(0);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (NhanVien nv : view.model.getDsNhanVien()) {
			String formattedDate = dateFormat.format(nv.getNgaySinh());
			model.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getPhongBan().getTenPhongBan(),
					formattedDate, nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(), nv.getDiaChi() });
		}
	}

}