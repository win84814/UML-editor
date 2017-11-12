import java.awt.Graphics;
import java.awt.Point;

public class AssociationLine extends LineObject{
	public AssociationLine() {
		
	}
	public AssociationLine(BasicObject from, BasicObject to, int fromArea, int toArea){
		super(from,to,fromArea,toArea);
		type = 1;
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point fromPoint = from.getArea(fromArea);
        Point toPoint = to.getArea(toArea);
        g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
        System.out.println("draw AssociationLine from "+fromArea+" to "+toArea);
    }
}
