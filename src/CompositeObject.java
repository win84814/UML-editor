import java.awt.Component;
import java.awt.Graphics;

import javax.naming.InitialContext;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTree;

public class CompositeObject extends BasicObject{
	public CompositeObject(int no) {
		this.no = no;
		areaSize = 10;
		type =6;
		setBounds(0, 0, 1030, 700);
	}
	public void Initial(int x, int y, int width, int height){
		this.x1 = x;
		this.y1 = y;
		this.width = width;
		this.height = height;
		add(new FourArea(areaSize, x - areaSize, y - areaSize, width, height),0);
	}
}
