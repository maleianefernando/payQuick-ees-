package gui.util;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Style {
	
	public static BorderLayout border = new BorderLayout();
	public static FlowLayout flow_center = new FlowLayout(FlowLayout.CENTER);
	public static GridLayout grid42 = new GridLayout(16, 1);
	public static Font tf_font = new Font("Sans Serif", Font.PLAIN, 16);	//text field font
	public static Font consolas_16 = new Font("Consolas", Font.PLAIN, 16);
	public static Font tf_font2 = new Font("Sans Serif", Font.BOLD, 16);
	public static Font ft = new Font("Arial Black", Font.PLAIN, 16);
	public static Font menu_font = new Font("Arial", Font.PLAIN, 14);
	
	public static Color bg = new Color(0xdddddb); // Linux backgroung = 0x2c2c2c
	public static Color fg = new Color(0x3e455f);	// Orange linux foreground
	//Color tf_bg = new Color(0xed6817);	//text field background
	public static Color tf_bg = new Color(0xffffff);	//text-field background - lightgray
	public static Color tf_fg = new Color(0x123456);	//text-field forground - blue
	public static Color label_fg = new Color(0x000000);	//label text-field - black
	public static Color blue = new Color(0x3960a1);	//button background
	
	public Style(){
		
	}
}
