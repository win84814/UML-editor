package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import Global.GlobalVar;

public class UsecaseObject extends BasicObject {
	public UsecaseObject(int no, int x, int y) {
		super(x, y, GlobalVar.USECASE_SIZE_W, GlobalVar.USECASE_SIZE_H);
		this.no = no;
		type = GlobalVar.USECASE;
		name = GlobalVar.USECASE_NAME;
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		setBounds(x-portSize, y-portSize, width+portSize*2, height+portSize*2);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(portSize, portSize, width, height);
        g.setColor(Color.black);
        g.drawOval(portSize, portSize, width, height);
        g.drawString(name, portSize + width/2, portSize + height/2);
    }
}
