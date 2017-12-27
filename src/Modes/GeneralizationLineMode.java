package Modes;

import java.awt.Component;
import java.awt.event.MouseEvent;
import Global.GlobalVar;
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
		BasicObject fromeComponent = (BasicObject) canvas.getComponentAt(onPressedX, onPressedY);
		BasicObject toComponent = (BasicObject) canvas.getComponentAt(releasedX, releasedY);
		if (fromeComponent.type <= GlobalVar.USECASE && toComponent.type <= GlobalVar.USECASE && fromeComponent.no != toComponent.no) {
			int fromArea = fromeComponent.whichArea(onPressedX, onPressedY);
			int toArea = toComponent.whichArea(releasedX, releasedY);
			LineObject lineObject = new GeneralizationLine(fromeComponent, toComponent, fromArea, toArea);
			canvas.add(lineObject);
		}
	}
}
