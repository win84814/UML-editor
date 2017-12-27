package GUI;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class ImageButton extends JRadioButton {
	int no;
	boolean isSelected;
	public ImageButton() {
		
	}
	public ImageButton(int no, ImageIcon imageIcon) {
		super();
		isSelected = false;
		this.no = no;
        this.setIcon(imageIcon);
        this.setBackground(Color.white);
        this.setBounds(20,20+120*no,75,75);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.setVisible(true);
        this.setActionCommand(String.valueOf(no));
	}
}
