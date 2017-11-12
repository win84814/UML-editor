import java.awt.Color;
import java.awt.Graphics;

public class ClassObject extends BasicObject{
	
	public ClassObject() {
	}
	
	public ClassObject(int x1, int y1) {
		super(x1, y1, 100, 120);
	}
	public ClassObject(int no, int x1, int y1) {
		super(x1, y1, 100, 120);
		this.no = no;
		type = 4;
		name = "Class";
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(areaSize, areaSize, width, height);
        g.setColor(Color.black);
        g.drawRect(areaSize, areaSize, width, height);
        g.drawLine(areaSize, areaSize + height/3, areaSize + width, areaSize + height/3);;
        g.drawLine(areaSize, areaSize + height*2/3, areaSize + width, areaSize + height*2/3);
        g.drawString(name, areaSize + width/2, areaSize + height/3 - 10);
    }
}
