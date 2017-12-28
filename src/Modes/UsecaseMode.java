package Modes;
import java.awt.event.MouseEvent;
import Shapes.UsecaseObject;

public class UsecaseMode extends Mode{
	public UsecaseMode(GUI.Canvas canvas){
		super(canvas);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		canvas.cancelSelect();
		canvas.basicObjects.add(new UsecaseObject(canvas.depth, clickX, clickY));
		canvas.depth++;
		canvas.repaint();
	}
}