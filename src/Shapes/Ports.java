package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Global.GlobalVar;

public class Ports extends JPanel{
	int areaSize;
	int width;
	int height;
	boolean isGroup = false;
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
		isGroup = true;
		setBounds(x, y, width+areaSize*2, height+areaSize*2);
	}
	public int[] getAreaPoint(int areaNo){
		int[] area = new int[2];
		switch (areaNo) {
		case GlobalVar.UP:
			area[0] = width/2 + areaSize/2;
			area[1] = areaSize/2;
			break;
		case GlobalVar.LEFT:
			area[0] = areaSize/2;
			area[1] = height/2 + areaSize/2;
			break;
		case GlobalVar.DOWN:
			area[0] = width/2 + areaSize/2;
			area[1] = height + areaSize/2;
			break;
		case GlobalVar.RIGHT:
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
        if(isGroup){
            g.setColor(Color.lightGray);
            g.drawRect(0, 0, width + areaSize*2-1, height + areaSize*2-1);
        }
        else{
            g.setColor(Color.blue);
        	for(int i = 0; i < 4; i++){
                int[] area = getAreaPoint(i);
                g.fillRect(area[0], area[1], areaSize, areaSize);
        	}
        }
    }
}
