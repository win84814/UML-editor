package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Ports extends JPanel{
	int areaSize;
	int width;
	int height;
	boolean isComposite = false;
	public Ports() {
		super();
	}
	public Ports(int areaSize, int width, int height) {
		this();
		this.areaSize = areaSize;
		this.width = width;
		this.height = height;
		setBounds(0, 0, width+areaSize*2, height+areaSize*2);
		setOpaque(false);
	}
	public Ports(int areaSize, int x, int y, int width, int height){
		this(areaSize, width, height);
		isComposite = true;
		setBounds(x, y, width+areaSize*2, height+areaSize*2);
	}
	public int[] getAreaPoint(int areaNo){
		int[] area = new int[2];
		switch (areaNo) {
		case 0:
			area[0] = width/2 + areaSize/2;
			area[1] = areaSize/2;
			break;
		case 1:
			area[0] = areaSize/2;
			area[1] = height/2 + areaSize/2;
			break;
		case 2:
			area[0] = width/2 + areaSize/2;
			area[1] = height + areaSize/2;
			break;
		case 3:
			area[0] = width + areaSize/2;
			area[1] = height/2 + areaSize/2;
			break;
		default:
			break;
		}
		return area;
	}
	public void setXY(int x, int y){
		setBounds(x, y, width+areaSize*2, height+areaSize*2);
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isComposite){
            g.setColor(Color.lightGray);
            g.drawRect(0, 0, width + areaSize*2-1, height + areaSize*2-1);
        }
        else{
            Color[] colors = new Color[4];
            colors[0] = Color.blue;
            colors[1] = Color.red;
            colors[2] = Color.green;
            colors[3] = Color.orange;
        	for(int i = 0; i < 4; i++){
                g.setColor(colors[i]);
                int[] area = getAreaPoint(i);
                g.fillRect(area[0], area[1], areaSize, areaSize);
        	}
        }
    }
}
