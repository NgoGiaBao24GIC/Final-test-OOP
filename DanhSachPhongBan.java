package model;

import java.util.ArrayList;

public class DanhSachPhongBan {
	public static ArrayList<PhongBan> getDSPB() {
		String[] arrPhongBan = { "Kinh doanh", "Nhân sự", "Kế toán", "Marketing", "IT" };
		ArrayList<PhongBan> listPhongBan = new ArrayList<>();
		for (int i = 0; i < arrPhongBan.length; i++) {
			PhongBan pb = new PhongBan(i, arrPhongBan[i]);
			listPhongBan.add(pb);
		}
		return listPhongBan;
	}

	public static PhongBan getPhongBanById(int maPhongBan) {
		return DanhSachPhongBan.getDSPB().get(maPhongBan);
	}

	public static PhongBan getPhongBanByTen(String tenPhongBan) {
		for (PhongBan pb : getDSPB()) {
			if (pb.getTenPhongBan().equals(tenPhongBan)) {
				return pb;
			}
		}
		return null;
	}
}