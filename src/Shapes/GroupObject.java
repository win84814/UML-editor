package Shapes;
import java.awt.Component;
import java.awt.Graphics;

import javax.naming.InitialContext;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTree;

import Global.GlobalVar;

public class GroupObject extends BasicObject{
	public GroupObject(int no) {
		this.no = no;
		areaSize = 10;
		type = GlobalVar.GROUP;
		setBounds(0, 0, GlobalVar.CANVAS_SIZE_W, GlobalVar.CANVAS_SIZE_H);
	}
	public void Initial(int x, int y, int width, int height){
		this.x1 = x;
		this.y1 = y;
		this.width = width;
		this.height = height;
		add(new Ports(areaSize, x - areaSize, y - areaSize, width, height),0);
	}
}
