package Modes;
import java.awt.event.MouseEvent;
import Shapes.ClassboxObject;

public class ClassboxMode extends Mode{
	public ClassboxMode(GUI.Canvas canvas){
		super(canvas);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
			canvas.cancelSelect();
			canvas.basicObjects.add(new ClassboxObject(canvas.depth, clickX, clickY));
			canvas.depth++;
		canvas.repaint();
	}
}
