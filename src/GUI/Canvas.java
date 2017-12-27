package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;

import Global.GlobalVar;
import Shapes.BasicObject;
import Shapes.GroupObject;
import Shapes.LineObject;

public class Canvas extends JPanel  {
	public int selectedButton;
	public int selectedNo;
	public Integer depth;
	public BasicObject[] selectObjects;
	public ArrayList<BasicObject> basicObjects = new ArrayList<BasicObject>();
	public ArrayList<LineObject> lineObjects  = new ArrayList<LineObject>();
	public Graphics graphics;
	public Canvas(Graphics graphics) {
		super();
		this.setBounds(140, 20, GlobalVar.CANVAS_SIZE_W, GlobalVar.CANVAS_SIZE_H);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.graphics = graphics;
		depth = 99;
		selectedNo = GlobalVar.NO_SELECT;
		selectedButton = GlobalVar.NO_SELECT;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		removeAll();
		for(BasicObject basicObject : basicObjects){
			add(basicObject);
		}
		for(LineObject lineObject : lineObjects){
			add(lineObject);
		}
		System.out.println("SIZE "+basicObjects.size());
	}
	
	public void cancelSelect() {
		for(BasicObject basicObject : basicObjects){
			basicObject.setSelected(false);
		}
		selectedNo = GlobalVar.NO_SELECT;
	}
	public void renameBasicObject(String newName){
		if(selectedNo != GlobalVar.NO_SELECT){
			for (BasicObject basicObject : basicObjects) {
				if(basicObject.no == selectedNo){
					basicObject.name = newName;
					break;
				}
			}
		}
		repaint();
	}
	public void group(){
		cancelSelect();
		if(selectObjects != null){
			if(selectObjects.length > 1){
				GroupObject groupObject = new GroupObject(depth);
				for(BasicObject basicObject : selectObjects){
					groupObject.addShape(basicObject);
					basicObjects.remove(basicObject);
				}
				basicObjects.add(groupObject);
				selectedNo = depth;
				selectObjects = null;
				depth ++;
			}
		}
	}
	public void ungroup(){
		if(selectedNo != GlobalVar.NO_SELECT){
			for(BasicObject basicObject : basicObjects){
				if(basicObject.no == selectedNo && basicObject.type == GlobalVar.GROUP){
					for(BasicObject subBasicObject : basicObject.container){
						basicObjects.add(subBasicObject);
					}
					basicObjects.remove(basicObject);
					break;
				}
			}
		}
		cancelSelect();
		repaint();
	}
	public BasicObject clickSomething(int clickX, int clickY){
		BasicObject result = null;
		int max = Integer.MIN_VALUE;
		for (BasicObject basicObject : basicObjects) {
			if((clickX >= basicObject.x1 && clickX <= basicObject.x1+basicObject.width &&
					clickY >= basicObject.y1 && clickY <= basicObject.y1+basicObject.height)){
				if(basicObject.no > max){
					max = basicObject.no;
					result = basicObject;
				}
			}
		}
		return result;
	}
}
