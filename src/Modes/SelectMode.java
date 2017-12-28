package Modes;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Shapes.BasicObject;

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
				if(fromeBasicObject.isSelected){
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
					if(basicObject.x > leftUpX && basicObject.y > leftUpY && 
						basicObject.x + basicObject.width < rightDownX && basicObject.y + basicObject.height < rightDownY){
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
