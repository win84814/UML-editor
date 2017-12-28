package Shapes;
import java.awt.Graphics;
import java.util.ArrayList;
import Global.GlobalVar;

public class GroupObject extends BasicObject{
	public GroupObject(int no) {
		this.no = no;
		type = GlobalVar.GROUP;
		container = new ArrayList<BasicObject>();
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBounds(0, 0, GlobalVar.CANVAS_SIZE_W, GlobalVar.CANVAS_SIZE_H);
        removeAll();
		if(isSelected) add(new Ports(portSize, x - portSize, y - portSize, width, height),0);
		for(BasicObject basicObject : container){
			add(basicObject);
		}
    }
	public void addShape(BasicObject basicObject){
		container.add(basicObject);
		setBound();
	}
	public void removeShape(BasicObject basicObject){
		container.remove(basicObject);
		setBound();
	}
	public void setBound(){
		removeAll();
		int max = Integer.MIN_VALUE;
		int leftUpX = Integer.MAX_VALUE;
		int leftUpY = Integer.MAX_VALUE;
		int rightDownX = Integer.MIN_VALUE;
		int rightDownY = Integer.MIN_VALUE;
		for(BasicObject basicObject : container){
			max = Math.max(max, basicObject.no);
			leftUpX = Math.min(leftUpX, basicObject.x);
			leftUpY = Math.min(leftUpY, basicObject.y);
			rightDownX = Math.max(rightDownX, basicObject.x+basicObject.width);
			rightDownY = Math.max(rightDownY, basicObject.y+basicObject.height);
		}
		x = leftUpX;
		y = leftUpY;
		width = rightDownX - leftUpX;
		height = rightDownY - leftUpY;
		setBounds(x, y, width, height);
		add(new Ports(portSize, x - portSize, y - portSize, width, height),0);
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
