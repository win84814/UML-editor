package Shapes;
import java.awt.Color;
import java.awt.Graphics;

import Global.GlobalVar;

public class UsecaseObject extends BasicObject {
	public UsecaseObject() {
	}
	
	public UsecaseObject(int x1, int y1) {
		super(x1, y1, 150, 120);
		type = GlobalVar.USECASE;
		name = "Use case";
	}
	public UsecaseObject(int no, int x1, int y1) {
		super(x1, y1, 150, 120);
		this.no = no;
		type = GlobalVar.USECASE;
		name = "Use case";
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		setBounds(x1-areaSize, y1-areaSize, width+areaSize*2, height+areaSize*2);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(areaSize, areaSize, width, height);
        g.setColor(Color.black);
        g.drawOval(areaSize, areaSize, width, height);
        g.drawString(name, areaSize + width/2, areaSize + height/2);
    }
}
