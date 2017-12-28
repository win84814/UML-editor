package Shapes;
import javax.swing.JComponent;
import Global.GlobalVar;

public class LineObject extends JComponent{
	public BasicObject from;
	public BasicObject to;
	public int fromPort;
	public int toPort;
	public int type; 
	public LineObject(BasicObject from, BasicObject to, int fromPort, int toPort){
		this.from = from;
		this.to = to;
		this.fromPort = fromPort;
		this.toPort = toPort;
		type = GlobalVar.NO_SELECT;
		this.setBounds(0, 0, GlobalVar.CANVAS_SIZE_W, GlobalVar.CANVAS_SIZE_H);
	}
}
