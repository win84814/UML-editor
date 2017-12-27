package Modes;

import java.awt.Canvas;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.nio.charset.MalformedInputException;

import Global.GlobalVar;
import Shapes.UsecaseObject;
public class UsecaseMode extends Mode{
	public UsecaseMode(GUI.Canvas canvas){
		super(canvas);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		try{
			if (e.getButton() == MouseEvent.BUTTON1) {
				canvas.cancelSelect();
				canvas.add(new UsecaseObject(canvas.depth, clickX, clickY), canvas.depth);
				canvas.depth++;
			}
		}
		catch (Exception exception) {
		}
		canvas.repaint();
	}
}