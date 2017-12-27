package Modes;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Global.GlobalVar;
import Shapes.BasicObject;
import Shapes.ClassboxObject;
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
		try{
			if (e.getButton() == MouseEvent.BUTTON1) {
				canvas.cancelSelect();
				if (canvas.selectedButton == GlobalVar.SELECT) {
					BasicObject basicObject = canvas.clickSomething(clickX, clickY);
					basicObject.getComponent(0).setVisible(true);
					canvas.selectedNo = basicObject.no;
				}
			}
		}
		catch (Exception exception) {
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
			BasicObject fromeComponent = canvas.clickSomething(onPressedX, onPressedY);
			if(fromeComponent.type != 0){
				if(fromeComponent.type >= GlobalVar.CLASS){
					if(fromeComponent.getComponent(0).isVisible()){
						int valueX = releasedX - onPressedX;
						int valueY = releasedY - onPressedY;
						fromeComponent.relocate(valueX, valueY);
					}
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
				for (Component component : canvas.getComponents()) {
					try{
						BasicObject basicObject = (BasicObject) component;
						if(basicObject.x1 > leftUpX && basicObject.y1 > leftUpY && 
							basicObject.x1 + basicObject.width < rightDownX && basicObject.y1 + basicObject.height < rightDownY){
							// basicObject show four points and set to array
							basicObject.getComponent(0).setVisible(true);
							temp.add(basicObject);
						}
					}
					catch (Exception exception) {
					}
				}
				canvas.selectObjects = temp.toArray(new BasicObject[temp.size()]);
			}
		}
		canvas.repaint();
	}
}
