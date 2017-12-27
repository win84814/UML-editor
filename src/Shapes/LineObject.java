package Shapes;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JPanel;

import Global.GlobalVar;

public class LineObject extends JComponent{
	public BasicObject from;
	public BasicObject to;
	public int fromArea;
	public int toArea;
	public int type; 
	public LineObject() {
		
	}
	public LineObject(BasicObject from, BasicObject to, int fromArea, int toArea){
		this.from = from;
		this.to = to;
		this.fromArea = fromArea;
		this.toArea = toArea;
		type = -1;
		this.setBounds(0, 0, GlobalVar.CANVAS_SIZE_W, GlobalVar.CANVAS_SIZE_H);
		this.setOpaque(false);
	}
}
