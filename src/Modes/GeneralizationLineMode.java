package Modes;

import java.awt.Component;
import java.awt.event.MouseEvent;
import Global.GlobalVar;
import Shapes.AssociationLine;
import Shapes.BasicObject;
import Shapes.GeneralizationLine;
import Shapes.LineObject;

public class GeneralizationLineMode extends Mode{
	int onPressedX;
	int onPressedY;
	public GeneralizationLineMode(GUI.Canvas canvas){
		super(canvas);
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
		BasicObject fromeComponent = canvas.clickSomething(onPressedX, onPressedY);
		BasicObject toComponent = canvas.clickSomething(releasedX, releasedY);
		if (fromeComponent != null && toComponent != null && fromeComponent.no != toComponent.no) {
			int fromArea = fromeComponent.whichArea(onPressedX, onPressedY);
			int toArea = toComponent.whichArea(releasedX, releasedY);
			LineObject lineObject = new GeneralizationLine(fromeComponent, toComponent, fromArea, toArea);
			canvas.lineObjects.add(lineObject);
			canvas.repaint();
		}
	}
}
