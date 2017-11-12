import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class BasicObject extends JComponent {
	int no;
	int x1;
	int y1;
	int width;
	int height;
	int areaSize;
	int type; // 0 is basic , 4 is class , 5 is usecase , 6 is composite
	String name;
	public BasicObject() {
		super();
		this.setBackground(Color.white);
		areaSize = 10;
		type = 0;
	}
	public BasicObject(int x1, int y1, int width, int height) {
		this();
		this.x1 = x1;
		this.y1 = y1;
		this.width = width;
		this.height =height;
		setBounds(x1-areaSize, y1-areaSize, width+areaSize*2, height+areaSize*2);
		add(new FourArea(areaSize, width, height));
		this.getComponent(0).setVisible(false);
	}
	public void relocate(int addX, int addY){
		int newX = x1 + addX;
		int newY = y1 + addY;
		if(type != Canvas.COMPOSITE){
			setBounds(newX-areaSize, newY-areaSize, width+areaSize*2, height+areaSize*2);
		}
		else{
			boolean passFirst = true;
			for (Component component : getComponents()) {
				if(passFirst) {
					passFirst = false;
					((FourArea)component).setXY(newX - areaSize, newY - areaSize);;
					continue;
				}
				try {
					((BasicObject) component).relocate(addX, addY);
				} catch (Exception exception) {
				}
			}
		}
		x1 = newX;
		y1 = newY;
	}
	public Point getArea(int area){
		switch (area) {
		case 0: return new Point(x1 + width/2, y1);
		case 1: return new Point(x1 , y1 + height/2);
		case 2: return new Point(x1 + width/2, y1 + height);
		case 3: return new Point(x1 + width, y1 + height/2);
		default:return new Point(-1,-1);
		}
	}
	public int whichArea(int x, int y){
		int area = -1;
		double maxDis = Double.MAX_VALUE;
		for(int i = 0; i < 4; i++){
			Point point = getArea(i);
			double dis = distance(x,y,point.x,point.y);
			if(dis < maxDis){
				area = i;
				maxDis = dis;
			}
		}
		return area;
	}
	public double distance(int x1, int y1, int x2, int y2){
		return Math.sqrt(Math.abs(x1 - x2)*Math.abs(x1 - x2) + Math.abs(y1 - y2) * Math.abs(y1 - y2));
	}
}
