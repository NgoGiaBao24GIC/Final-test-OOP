package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class QLNhanSuModel implements Serializable {
    private ArrayList<NhanVien> dsNhanVien;
    private String luaChon;
    private String tenFile;
    private Connection connection; 

    public QLNhanSuModel() {
        this.dsNhanVien = new ArrayList<>();
        this.luaChon = "";
        this.tenFile = "";
    }

    public ArrayList<NhanVien> getDsNhanVien() {
        return dsNhanVien;
    }

    public void setDsNhanVien(ArrayList<NhanVien> dsNhanVien) {
        this.dsNhanVien = dsNhanVien;
    }

    public void insert(NhanVien nv) {
        dsNhanVien.add(nv);
    }

    public void delete(NhanVien nv) {
        dsNhanVien.remove(nv);
    }

    public void update(NhanVien nv) {
        int index = dsNhanVien.indexOf(nv);
        if (index != -1) {
            dsNhanVien.set(index, nv);
        }
    }

    public boolean kiemTraTonTai(NhanVien nv) {
        return dsNhanVien.contains(nv);
    }

    public String getLuaChon() {
        return luaChon;
    }

    public void setLuaChon(String luaChon) {
        this.luaChon = luaChon;
    }

    public String getTenFile() {
        return tenFile;
    }

    public void setTenFile(String tenFile) {
        this.tenFile = tenFile;
    }


    public void connectToDatabase() {
        try {
			String url = "jdbc:mysql://localhost:3306/ngb"; 
            String username = "root"; 
            String password = ""; 
            connection = DriverManager.getConnection(url, username, password); 
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể kết nối tới cơ sở dữ liệu!");
        }
    }

    public Connection getConnection() {
        return connection; 
    }
    
}
