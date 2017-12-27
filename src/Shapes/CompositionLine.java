package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class CompositionLine extends LineObject{
	public CompositionLine() {
		
	}
	public CompositionLine(BasicObject from, BasicObject to, int fromArea, int toArea){
		super(from,to,fromArea,toArea);
		type = 3;
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point fromPoint = from.getArea(fromArea);
        Point toPoint = to.getArea(toArea);
        g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
        Polygon polygon = new Polygon();
        polygon.addPoint(fromPoint.x, fromPoint.y);
        switch (fromArea) {
			case 0:polygon.addPoint(fromPoint.x - 10, fromPoint.y - 10);polygon.addPoint(fromPoint.x, fromPoint.y - 20);polygon.addPoint(fromPoint.x + 10, fromPoint.y - 10);break;
			case 1:polygon.addPoint(fromPoint.x - 10, fromPoint.y - 10);polygon.addPoint(fromPoint.x - 20, fromPoint.y);polygon.addPoint(fromPoint.x - 10, fromPoint.y + 10);break;
			case 2:polygon.addPoint(fromPoint.x - 10, fromPoint.y + 10);polygon.addPoint(fromPoint.x, fromPoint.y + 20);polygon.addPoint(fromPoint.x + 10, fromPoint.y + 10);break;
			case 3:polygon.addPoint(fromPoint.x + 10, fromPoint.y - 10);polygon.addPoint(fromPoint.x + 20, fromPoint.y);polygon.addPoint(fromPoint.x + 10, fromPoint.y + 10);break;
			default:break;
		}
        g.setColor(Color.white);
        g.fillPolygon(polygon);
        g.setColor(Color.black);
        g.drawPolygon(polygon);
    }
}
