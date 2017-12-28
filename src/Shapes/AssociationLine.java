package Shapes;
import java.awt.Graphics;
import java.awt.Point;

import Global.GlobalVar;

public class AssociationLine extends LineObject{
	public AssociationLine(BasicObject from, BasicObject to, int fromPort, int toPort){
		super(from,to,fromPort,toPort);
		type = GlobalVar.ASSOCIATION;
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point fromPoint = from.getPoint(fromPort);
        Point toPoint = to.getPoint(toPort);
        g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
    }
}
