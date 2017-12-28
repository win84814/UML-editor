package Modes;
import java.awt.event.MouseEvent;
import Shapes.AssociationLine;
import Shapes.BasicObject;
import Shapes.LineObject;

public class AssociationLineMode extends Mode{
	int onPressedX;
	int onPressedY;
	public AssociationLineMode(GUI.Canvas canvas){
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
			int fromArea = fromeComponent.getPort(onPressedX, onPressedY);
			int toArea = toComponent.getPort(releasedX, releasedY);
			LineObject lineObject = new AssociationLine(fromeComponent, toComponent, fromArea, toArea);
			canvas.lineObjects.add(lineObject);
			canvas.repaint();
		}
	}
}
