package Modes;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Global.GlobalVar;
import Shapes.BasicObject;
import Shapes.ClassboxObject;
import Shapes.GroupObject;
import Shapes.UsecaseObject;
public class SelectMode extends Mode{
	int onPressedX;
	int onPressedY;
	public SelectMode(GUI.Canvas canvas){
		super(canvas);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		canvas.cancelSelect();
		BasicObject basicObject = canvas.clickSomething(clickX, clickY);
		if(basicObject != null) {
			basicObject.setSelected(true);
			canvas.selectedNo = basicObject.no;
		}
		// test group
		if(e.getButton() == 3){
			BasicObject shape1 = new UsecaseObject(canvas.depth, clickX+100, clickY+100);
			BasicObject shape2 = new UsecaseObject(canvas.depth, clickX+200, clickY+200);
			BasicObject shape3 = new UsecaseObject(canvas.depth, clickX+300, clickY+300);
			BasicObject group = new GroupObject(0);
			group.addShape(shape1);
			group.addShape(shape2);
			group.addShape(shape3);
			canvas.basicObjects.add(group);
		}
		canvas.repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		onPressedX = e.getX();
		onPressedY = e.getY();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		int releasedX = e.getX();
		int releasedY = e.getY();
		if(onPressedX != releasedX && onPressedY != releasedY){
			BasicObject fromeBasicObject= canvas.clickSomething(onPressedX, onPressedY);
			if(fromeBasicObject != null){
				if(fromeBasicObject.getComponent(0).isVisible()){
					int valueX = releasedX - onPressedX;
					int valueY = releasedY - onPressedY;
					fromeBasicObject.relocate(valueX, valueY);
				}
			}
			else{
				canvas.cancelSelect();
				canvas.selectObjects = null;
				int leftUpX = onPressedX < releasedX ? onPressedX : releasedX;
				int leftUpY = onPressedY < releasedY ? onPressedY : releasedY;
				int rightDownX = onPressedX > releasedX ? onPressedX : releasedX;
				int rightDownY = onPressedY > releasedY ? onPressedY : releasedY;
				ArrayList<BasicObject> temp = new ArrayList<BasicObject>();
				for(BasicObject basicObject : canvas.basicObjects){
					if(basicObject.x1 > leftUpX && basicObject.y1 > leftUpY && 
						basicObject.x1 + basicObject.width < rightDownX && basicObject.y1 + basicObject.height < rightDownY){
						basicObject.setSelected(true);
						temp.add(basicObject);
					}
				}
				canvas.selectObjects = temp.toArray(new BasicObject[temp.size()]);
			}
		}
		canvas.repaint();
	}
}
