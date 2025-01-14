package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.QLNhanSuController;
import model.DanhSachPhongBan;
import model.NhanVien;
import model.PhongBan;
import model.QLNhanSuModel;

public class QLNhanSuView extends JFrame {

	private JPanel contentPane;
	public QLNhanSuModel model;
	public JTextField textField_MaNhanVien_TimKiem;
	public JComboBox<String> comboBox_PhongBan_TimKiem;
	public JTable table;
	public JTextField textField_ID;
	public JTextField textField_HoVaTen;
	public JTextField textField_NgaySinh;
	public JTextField textField_Luong;
	public JTextField textField_DiaChi;
	public ButtonGroup btn_gioiTinh;
	public JComboBox<String> comboBox_phongBan;
	public JRadioButton radioButton_nam;
	public JRadioButton radioButton_nu;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				QLNhanSuView frame = new QLNhanSuView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public QLNhanSuView() {
		this.model = new QLNhanSuModel();
		this.model.connectToDatabase();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 750);

		QLNhanSuController controller = new QLNhanSuController(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuFile);

		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuOpen.addActionListener(controller);
		menuOpen.setActionCommand("Open");
		menuFile.add(menuOpen);

		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuSave.addActionListener(controller);
		menuSave.setActionCommand("Save");
		menuFile.add(menuSave);

		JSeparator separatorMenu = new JSeparator();
		menuFile.add(separatorMenu);

		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuExit.addActionListener(controller);
		menuExit.setActionCommand("Exit");
		menuFile.add(menuExit);

		JMenu menuAbout = new JMenu("About");
		menuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuAbout);

		JMenuItem menuAboutMe = new JMenuItem("About Me");
		menuAboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuAboutMe.addActionListener(controller);
		menuAboutMe.setActionCommand("About Me");
		menuAbout.add(menuAboutMe);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(240, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelForm = new JPanel();
		panelForm.setBounds(20, 20, 500, 370);
		panelForm.setLayout(null);
		panelForm.setBackground(new Color(240, 248, 255));
		contentPane.add(panelForm);

		JLabel label_PhongBan = new JLabel("Phòng ban");
		label_PhongBan.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_PhongBan.setBounds(20, 20, 150, 40);
		label_PhongBan.setForeground(new Color(0, 102, 204));
		panelForm.add(label_PhongBan);

		comboBox_phongBan = new JComboBox<>();
		comboBox_phongBan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_phongBan.setBounds(180, 20, 280, 40);
		comboBox_phongBan.addItem("");
		ArrayList<PhongBan> listPhongBan = DanhSachPhongBan.getDSPB();
		for (PhongBan pb : listPhongBan) {
			comboBox_phongBan.addItem(pb.getTenPhongBan());
		}
		panelForm.add(comboBox_phongBan);

		JLabel label_MaNhanVien = new JLabel("Mã nhân viên");
		label_MaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_MaNhanVien.setBounds(20, 70, 150, 40);
		label_MaNhanVien.setForeground(new Color(0, 102, 204));
		panelForm.add(label_MaNhanVien);

		textField_ID = new JTextField();
		textField_ID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_ID.setBounds(180, 70, 280, 40);
		panelForm.add(textField_ID);

		JLabel label_HoVaTen = new JLabel("Họ và tên");
		label_HoVaTen.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_HoVaTen.setBounds(20, 120, 150, 40);
		label_HoVaTen.setForeground(new Color(0, 102, 204));
		panelForm.add(label_HoVaTen);

		textField_HoVaTen = new JTextField();
		textField_HoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_HoVaTen.setBounds(180, 120, 280, 40);
		panelForm.add(textField_HoVaTen);

		JLabel label_NgaySinh = new JLabel("Ngày sinh");
		label_NgaySinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_NgaySinh.setBounds(20, 170, 150, 40);
		label_NgaySinh.setForeground(new Color(0, 102, 204));
		panelForm.add(label_NgaySinh);

		textField_NgaySinh = new JTextField();
		textField_NgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_NgaySinh.setBounds(180, 170, 280, 40);
		panelForm.add(textField_NgaySinh);

		JLabel label_GioiTinh = new JLabel("Giới tính");
		label_GioiTinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_GioiTinh.setBounds(20, 220, 150, 40);
		label_GioiTinh.setForeground(new Color(0, 102, 204));
		panelForm.add(label_GioiTinh);

		radioButton_nam = new JRadioButton("Nam");
		radioButton_nam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		radioButton_nam.setBounds(180, 220, 100, 40);
		radioButton_nam.setBackground(new Color(240, 248, 255));
		panelForm.add(radioButton_nam);

		radioButton_nu = new JRadioButton("Nữ");
		radioButton_nu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		radioButton_nu.setBounds(300, 220, 100, 40);
		radioButton_nu.setBackground(new Color(240, 248, 255));
		panelForm.add(radioButton_nu);

		btn_gioiTinh = new ButtonGroup();
		btn_gioiTinh.add(radioButton_nam);
		btn_gioiTinh.add(radioButton_nu);

		JLabel label_Luong = new JLabel("Lương");
		label_Luong.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_Luong.setBounds(20, 270, 150, 40);
		label_Luong.setForeground(new Color(0, 102, 204));
		panelForm.add(label_Luong);

		textField_Luong = new JTextField();
		textField_Luong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_Luong.setBounds(180, 270, 280, 40);
		panelForm.add(textField_Luong);

		JLabel label_DiaChi = new JLabel("Địa chỉ");
		label_DiaChi.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_DiaChi.setBounds(20, 320, 150, 40);
		label_DiaChi.setForeground(new Color(0, 102, 204));
		panelForm.add(label_DiaChi);

		textField_DiaChi = new JTextField();
		textField_DiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_DiaChi.setBounds(180, 320, 280, 40);
		panelForm.add(textField_DiaChi);

		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(30, 390, 500, 60);
		panelButtons.setLayout(null);
		panelButtons.setBackground(new Color(240, 248, 255));
		contentPane.add(panelButtons);

		JButton btnThem = new JButton("Làm mới");
		btnThem.addActionListener(controller);
		btnThem.setActionCommand("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setBounds(20, 10, 120, 40);
		btnThem.setBackground(new Color(102, 204, 255));
		panelButtons.add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(controller);
		btnXoa.setActionCommand("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBounds(150, 10, 85, 40);
		btnXoa.setBackground(new Color(255, 102, 102));
		panelButtons.add(btnXoa);

		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(controller);
		btnCapNhat.setActionCommand("Cập nhật");
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCapNhat.setBounds(246, 10, 120, 40);
		btnCapNhat.setBackground(new Color(102, 255, 178));
		panelButtons.add(btnCapNhat);

		JButton btnLu = new JButton("Lưu");
		btnLu.addActionListener(controller);
		btnLu.setActionCommand("Lưu");
		btnLu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLu.setBounds(376, 10, 85, 40);
		btnLu.setBackground(new Color(255, 255, 0));
		panelButtons.add(btnLu);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã NV", "Họ tên", "Phòng ban", "Ngày sinh", "Giới tính", "Lương", "Địa chỉ" }));
		table.setRowHeight(25);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 460, 850, 200);
		contentPane.add(scrollPane);

		JLabel lblMNv = new JLabel("Mã NV");
		lblMNv.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMNv.setBounds(606, 20, 100, 40);
		contentPane.add(lblMNv);

		textField_MaNhanVien_TimKiem = new JTextField();
		textField_MaNhanVien_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_MaNhanVien_TimKiem.setBounds(736, 21, 120, 40);
		contentPane.add(textField_MaNhanVien_TimKiem);

		JLabel lblPhngBan = new JLabel("Phòng Ban");
		lblPhngBan.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPhngBan.setBounds(606, 84, 120, 40);
		contentPane.add(lblPhngBan);

		comboBox_PhongBan_TimKiem = new JComboBox<>();
		comboBox_PhongBan_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_PhongBan_TimKiem.setBounds(736, 85, 140, 40);
		comboBox_PhongBan_TimKiem.addItem("");
		for (PhongBan pb : listPhongBan) {
			comboBox_PhongBan_TimKiem.addItem(pb.getTenPhongBan());
		}
		contentPane.add(comboBox_PhongBan_TimKiem);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTimKiem.setBounds(606, 154, 120, 40);
		btnTimKiem.setBackground(new Color(255, 128, 0));
		btnTimKiem.addActionListener(e -> thucHienTimKiem());
		contentPane.add(btnTimKiem);

		JSeparator separatorUI = new JSeparator(JSeparator.VERTICAL);
		separatorUI.setForeground(new Color(0, 0, 0));
		separatorUI.setBounds(580, 10, 2, 450);
		contentPane.add(separatorUI);

		this.setVisible(true);
	}

	private void thucHienTimKiem() {
		String maNhanVien = textField_MaNhanVien_TimKiem.getText();
		String tenPhongBan = (String) comboBox_PhongBan_TimKiem.getSelectedItem();
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();

		while (model_table.getRowCount() > 0) {
			model_table.removeRow(0);
		}

		for (NhanVien nv : model.getDsNhanVien()) {
			boolean matches = true;

			if (!maNhanVien.isEmpty() && !String.valueOf(nv.getMaNhanVien()).equals(maNhanVien)) {
				matches = false;
			}

			if (tenPhongBan != null && !tenPhongBan.isEmpty()
					&& !nv.getPhongBan().getTenPhongBan().equals(tenPhongBan)) {
				matches = false;
			}

			if (matches) {
				model_table.addRow(
						new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getPhongBan().getTenPhongBan(),
								nv.getNgaySinh(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(), nv.getDiaChi() });
			}
		}
	}

	private void taiLaiDuLieu() {
		textField_MaNhanVien_TimKiem.setText("");
		comboBox_PhongBan_TimKiem.setSelectedIndex(0);
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		while (model_table.getRowCount() > 0) {
			model_table.removeRow(0);
		}
		for (NhanVien nv : model.getDsNhanVien()) {
			model_table
					.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getPhongBan().getTenPhongBan(),
							nv.getNgaySinh(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getLuong(), nv.getDiaChi() });
		}
	}
}