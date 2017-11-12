import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class LineObject extends JPanel{
	BasicObject from;
	BasicObject to;
	int fromArea;
	int toArea;
	int type; //  1 = AssociationLine , 2 = GeneralizationLine , 3 = CompositionLine
	public LineObject() {
		
	}
	public LineObject(BasicObject from, BasicObject to, int fromArea, int toArea){
		this.from = from;
		this.to = to;
		this.fromArea = fromArea;
		this.toArea = toArea;
		type = -1;
		this.setBounds(0, 0, 1030, 700);
		this.setOpaque(false);
	}
}
