package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class NhanVien implements Serializable {
	private int maNhanVien;
	private String tenNhanVien;
	private PhongBan phongBan;
	private Date ngaySinh;
	private boolean gioiTinh;
	private float luong;
	private String diaChi;

	public NhanVien() {
	}

	public NhanVien(int maNhanVien, String tenNhanVien, PhongBan phongBan, Date ngaySinh, boolean gioiTinh, float luong,
			String diaChi) {
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.phongBan = phongBan;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.luong = luong;
		this.diaChi = diaChi;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public float getLuong() {
		return luong;
	}

	public void setLuong(float luong) {
		this.luong = luong;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", phongBan=" + phongBan
				+ ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", luong=" + luong + ", diaChi=" + diaChi + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(diaChi, gioiTinh, luong, maNhanVien, ngaySinh, phongBan, tenNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Float.floatToIntBits(luong) == Float.floatToIntBits(other.luong) && gioiTinh == other.gioiTinh
				&& maNhanVien == other.maNhanVien && Objects.equals(ngaySinh, other.ngaySinh)
				&& Objects.equals(phongBan, other.phongBan) && Objects.equals(tenNhanVien, other.tenNhanVien)
				&& Objects.equals(diaChi, other.diaChi);
	}
}