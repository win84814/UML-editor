package Modes;

import java.awt.Canvas;
import java.awt.event.MouseEvent;

import Global.GlobalVar;
import Shapes.ClassboxObject;

public class ClassboxMode extends Mode{
	public ClassboxMode(GUI.Canvas canvas){
		super(canvas);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		try{
			if (e.getButton() == MouseEvent.BUTTON1) {
				canvas.cancelSelect();
				canvas.add(new ClassboxObject(canvas.depth, clickX, clickY), canvas.depth);
				canvas.depth++;
			}
		}
		catch (Exception exception) {
		}
		canvas.repaint();
	}
}
