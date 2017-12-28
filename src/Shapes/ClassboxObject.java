package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import Global.GlobalVar;

public class ClassboxObject extends BasicObject{
	public ClassboxObject(int no, int x, int y) {
		super(x, y, GlobalVar.CLASSBOX_SIZE_W, GlobalVar.CLASSBOX_SIZE_H);
		this.no = no;
		type = GlobalVar.CLASS;
		name = GlobalVar.CLASSBOX_NAME;
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		setBounds(x-portSize, y-portSize, width+portSize*2, height+portSize*2);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(portSize, portSize, width, height);
        g.setColor(Color.black);
        g.drawRect(portSize, portSize, width, height);
        g.drawLine(portSize, portSize + height/3, portSize + width, portSize + height/3);;
        g.drawLine(portSize, portSize + height*2/3, portSize + width, portSize + height*2/3);
        g.drawString(name, portSize + width/2, portSize + height/3 - 10);
    }
}
