package model;

import java.io.Serializable;
import java.util.Objects;

public class PhongBan implements Serializable {
	private int maPhongBan;
	private String tenPhongBan;

	public PhongBan(int maPhongBan, String tenPhongBan) {
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
	}

	public int getMaPhongBan() {
		return maPhongBan;
	}

	public void setMaPhongBan(int maPhongBan) {
		this.maPhongBan = maPhongBan;
	}

	public String getTenPhongBan() {
		return tenPhongBan;
	}

	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}

	@Override
	public String toString() {
		return "PhongBan [maPhongBan=" + maPhongBan + ", tenPhongBan=" + tenPhongBan + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhongBan, tenPhongBan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongBan other = (PhongBan) obj;
		return maPhongBan == other.maPhongBan && Objects.equals(tenPhongBan, other.tenPhongBan);
	}
}