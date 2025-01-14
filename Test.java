package test;

import javax.swing.UIManager;

import view.QLNhanSuView;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new QLNhanSuView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}