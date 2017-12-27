package Shapes;
import java.awt.Color;
import java.awt.Graphics;

import Global.GlobalVar;

public class ClassboxObject extends BasicObject{
	
	public ClassboxObject() {
	}
	
	public ClassboxObject(int x1, int y1) {
		super(x1, y1, 100, 120);
		type = GlobalVar.CLASS;
		name = "Class";
	}
	public ClassboxObject(int no, int x1, int y1) {
		super(x1, y1, 100, 120);
		this.no = no;
		type = GlobalVar.CLASS;
		name = "Class";
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		setBounds(x1-areaSize, y1-areaSize, width+areaSize*2, height+areaSize*2);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(areaSize, areaSize, width, height);
        g.setColor(Color.black);
        g.drawRect(areaSize, areaSize, width, height);
        g.drawLine(areaSize, areaSize + height/3, areaSize + width, areaSize + height/3);;
        g.drawLine(areaSize, areaSize + height*2/3, areaSize + width, areaSize + height*2/3);
        g.drawString(name, areaSize + width/2, areaSize + height/3 - 10);
        System.out.println("paint classbox");
    }
}
