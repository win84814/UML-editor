package Modes;
import java.awt.event.MouseEvent;
import Shapes.BasicObject;
import Shapes.CompositionLine;
import Shapes.LineObject;

public class CompositionLineMode extends Mode{
	int onPressedX;
	int onPressedY;
	public CompositionLineMode(GUI.Canvas canvas){
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
			LineObject lineObject = new CompositionLine(fromeComponent, toComponent, fromArea, toArea);
			canvas.lineObjects.add(lineObject);
			canvas.repaint();
		}
	}
}
