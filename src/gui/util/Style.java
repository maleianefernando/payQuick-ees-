package gui.util;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

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
	public static Color jmenu_bar_bg = new Color(0xedbe39);
	public static Color table_bg = new Color(0xc3d169);
	public static Color table_head_bg = new Color(0xf19e00);
	public static Color table_head_fg = new Color(0x86c4c1);
	public static Color btn_click_bg = new Color(0x377685);
	public static Color btn_bg = new Color(0x039e18);

	public static Font btn_font = new Font("Consolas", Font.PLAIN, 20);
	public static Font table_head_font = new Font("Consolas", Font.BOLD, 18);
	public static Font table_font = new Font("Consolas", Font.PLAIN, 16);

	public static Integer table_row_height = 30;

	public static Dimension cell_spacing = new Dimension(10, 10);
	
	public static Border table_border = BorderFactory.createLineBorder(Color.black);

	public Style(){
		
	}
}
