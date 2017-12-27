package Shapes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTree;

import Global.GlobalVar;

public class GroupObject extends BasicObject{
	public GroupObject(int no) {
		this.no = no;
		type = GlobalVar.GROUP;
		container = new ArrayList<BasicObject>();
	}
	public GroupObject(int no, int x1, int y1) {
		super(x1, y1, 150, 120);
		this.no = no;
		type = GlobalVar.GROUP;
		container = new ArrayList<BasicObject>();
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBounds(0, 0, 1030, 700);
        removeAll();
		if(isSelected) add(new Ports(areaSize, x1 - areaSize, y1 - areaSize, width, height),0);
		for(BasicObject basicObject : container){
			add(basicObject);
		}
    }
	public void addShape(BasicObject basicObject){
		container.add(basicObject);
		resetBound();
	}
	public void removeShape(BasicObject basicObject){
		container.remove(basicObject);
		resetBound();
	}
	public void resetBound(){
		removeAll();
		int max = Integer.MIN_VALUE;
		int leftUpX = Integer.MAX_VALUE;
		int leftUpY = Integer.MAX_VALUE;
		int rightDownX = Integer.MIN_VALUE;
		int rightDownY = Integer.MIN_VALUE;
		for(BasicObject basicObject : container){
			max = Math.max(max, basicObject.no);
			leftUpX = Math.min(leftUpX, basicObject.x1);
			leftUpY = Math.min(leftUpY, basicObject.y1);
			rightDownX = Math.max(rightDownX, basicObject.x1+basicObject.width);
			rightDownY = Math.max(rightDownY, basicObject.y1+basicObject.height);
		}
		x1 = leftUpX;
		y1 = leftUpY;
		width = rightDownX - leftUpX;
		height = rightDownY - leftUpY;
		setBounds(x1, y1, width, height);
		add(new Ports(areaSize, x1 - areaSize, y1 - areaSize, width, height),0);
		System.out.println("DO RESETBOUND");
	}
	public void setSelected(boolean bool){
		if(bool){
			isSelected = true;
		}
		else{
			isSelected = false;
		}
	}
}
