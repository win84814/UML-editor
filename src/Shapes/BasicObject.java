package Shapes;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.*;
import Global.GlobalVar;

public class BasicObject extends JComponent {
	public int no;
	public int x;
	public int y;
	public int width;
	public int height;
	public int portSize;
	public int type;
	public String name;
	public ArrayList<BasicObject> container;
	public boolean isSelected;
	public BasicObject() {
		super();
		this.setBackground(Color.white);
		portSize = GlobalVar.PORT_SIZE;
		type = GlobalVar.NO_SELECT;
	}
	public BasicObject(int x, int y, int width, int height) {
		this();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height =height;
		portSize = GlobalVar.PORT_SIZE;
		setBounds(x-portSize, y-portSize, width+portSize*2, height+portSize*2);
		add(new Ports(portSize, width, height));
		this.getComponent(0).setVisible(false);
	}
	public void relocate(int addX, int addY){
		int newX = x + addX;
		int newY = y + addY;
		x = newX;
		y = newY;
		if(type == GlobalVar.GROUP){
			for(BasicObject basicObject : container){
				basicObject.relocate(addX, addY);
			}
		}
	}
	public Point getPoint(int area){
		switch (area) {
			case GlobalVar.UP: return new Point(x + width/2, y);
			case GlobalVar.LEFT: return new Point(x , y + height/2);
			case GlobalVar.DOWN: return new Point(x + width/2, y + height);
			case GlobalVar.RIGHT: return new Point(x + width, y + height/2);
			default:return new Point(-1,-1);
		}
	}
	public int getPort(int x, int y){
		int area = -1;
		double maxDis = Double.MAX_VALUE;
		for(int i = 0; i < 4; i++){
			Point point = getPoint(i);
			double dis = distance(x,y,point.x,point.y);
			if(dis < maxDis){
				area = i;
				maxDis = dis;
			}
		}
		return area;
	}
	public double distance(int x, int y, int x2, int y2){
		return Math.sqrt(Math.abs(x - x2)*Math.abs(x - x2) + Math.abs(y - y2) * Math.abs(y - y2));
	}
	public void setSelected(boolean bool){
		if(bool){
			getComponent(0).setVisible(true);
			isSelected = true;
		}
		else{
			getComponent(0).setVisible(false);
			isSelected = false;
		}
	}
	public void addShape(BasicObject basicObject){
		
	}
	public void removeShape(BasicObject basicObject){
		
	}
}
