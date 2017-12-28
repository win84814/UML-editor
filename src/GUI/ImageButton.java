package GUI;
import java.awt.Color;
import javax.swing.*;

import Global.GlobalVar;

public class ImageButton extends JRadioButton {
	int no;
	public ImageButton(int no, ImageIcon imageIcon) {
		super();
		this.no = no;
        this.setIcon(imageIcon);
        this.setBackground(Color.white);
        this.setBounds(GlobalVar.BLANK,GlobalVar.BLANK+GlobalVar.BUTTON_INTERVAL*no,GlobalVar.BUTTON_SIZE,GlobalVar.BUTTON_SIZE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.setVisible(true);
        this.setActionCommand(String.valueOf(no));
	}
}
