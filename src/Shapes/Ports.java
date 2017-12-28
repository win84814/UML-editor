package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import Global.GlobalVar;

public class Ports extends JPanel{
	int size;
	int basicObjectWidth;
	int basicObjectHeight;
	boolean inGroup = false;
	public Ports(int size, int basicObjectWidth, int basicObjectHeight) {
		this.size = size;
		this.basicObjectWidth = basicObjectWidth;
		this.basicObjectHeight = basicObjectHeight;
		setBounds(0, 0, basicObjectWidth+size*2, basicObjectHeight+size*2);
		setOpaque(false);
	}
	public Ports(int size, int x, int y, int basicObjectWidth, int basicObjectHeight){
		this(size, basicObjectWidth, basicObjectHeight);
		inGroup = true;
		setBounds(x, y, basicObjectWidth+size*2, basicObjectHeight+size*2);
	}
	public int[] getPortPoint(int portNO){
		int[] point = new int[2];
		switch (portNO) {
			case GlobalVar.UP:
				point[GlobalVar.X] = basicObjectWidth/2 + size/2;
				point[GlobalVar.Y] = size/2;
				break;
			case GlobalVar.LEFT:
				point[GlobalVar.X] = size/2;
				point[GlobalVar.Y] = basicObjectHeight/2 + size/2;
				break;
			case GlobalVar.DOWN:
				point[GlobalVar.X] = basicObjectWidth/2 + size/2;
				point[GlobalVar.Y] = basicObjectHeight + size/2;
				break;
			case GlobalVar.RIGHT:
				point[GlobalVar.X] = basicObjectWidth + size/2;
				point[GlobalVar.Y] = basicObjectHeight/2 + size/2;
				break;
			default:
				break;
		}
		return point;
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGroup){
            g.setColor(Color.lightGray);
            g.drawRect(0, 0, basicObjectWidth + size*2-1, basicObjectHeight + size*2-1);
        }
        else{
            g.setColor(Color.blue);
        	for(int i = 0; i < GlobalVar.PORT_COUNT; i++){
                int[] point = getPortPoint(i);
                g.fillRect(point[GlobalVar.X], point[GlobalVar.Y], size, size);
        	}
        }
    }
}
