package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import Global.GlobalVar;

public class GeneralizationLine extends LineObject{
	public GeneralizationLine(BasicObject from, BasicObject to, int fromPort, int toPort){
		super(from,to,fromPort,toPort);
		type = GlobalVar.GENERALIZATION;
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point fromPoint = from.getArea(fromPort);
        Point toPoint = to.getArea(toPort);
        g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
        Polygon polygon = new Polygon();
        polygon.addPoint(fromPoint.x, fromPoint.y);
        switch (fromPort) {
			case GlobalVar.UP : polygon.addPoint(fromPoint.x - GlobalVar.PORT_SIZE, fromPoint.y - GlobalVar.PORT_SIZE);polygon.addPoint(fromPoint.x + GlobalVar.PORT_SIZE, fromPoint.y - GlobalVar.PORT_SIZE);break;
			case GlobalVar.LEFT : polygon.addPoint(fromPoint.x - GlobalVar.PORT_SIZE, fromPoint.y - GlobalVar.PORT_SIZE);polygon.addPoint(fromPoint.x - GlobalVar.PORT_SIZE, fromPoint.y + GlobalVar.PORT_SIZE);break;
			case GlobalVar.DOWN : polygon.addPoint(fromPoint.x - GlobalVar.PORT_SIZE, fromPoint.y + GlobalVar.PORT_SIZE);polygon.addPoint(fromPoint.x + GlobalVar.PORT_SIZE, fromPoint.y + GlobalVar.PORT_SIZE);break;
			case GlobalVar.RIGHT : polygon.addPoint(fromPoint.x + GlobalVar.PORT_SIZE, fromPoint.y - GlobalVar.PORT_SIZE);polygon.addPoint(fromPoint.x + GlobalVar.PORT_SIZE, fromPoint.y + GlobalVar.PORT_SIZE);break;
			default : break;
		}
        g.setColor(Color.white);
        g.fillPolygon(polygon);
        g.setColor(Color.black);
        g.drawPolygon(polygon);
    }
}
