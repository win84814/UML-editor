package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
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
		System.out.println("SIZE "+getComponentCount());
	}
	
	public void cancelSelect() {
		for(BasicObject basicObject : basicObjects){
			basicObject.getComponent(0).setVisible(false);
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
	public boolean group(){
		cancelSelect();
		if(selectObjects != null){
			if(selectObjects.length > 1){
				GroupObject compositeObject = new GroupObject(depth);
				int max = Integer.MIN_VALUE;
				int leftUpX = Integer.MAX_VALUE;
				int leftUpY = Integer.MAX_VALUE;
				int rightDownX = Integer.MIN_VALUE;
				int rightDownY = Integer.MIN_VALUE;
				for(int i = 0; i < selectObjects.length; i++){
					max = Math.max(max, selectObjects[i].no);
					leftUpX = Math.min(leftUpX, selectObjects[i].x1);
					leftUpY = Math.min(leftUpY, selectObjects[i].y1);
					rightDownX = Math.max(rightDownX, selectObjects[i].x1+selectObjects[i].width);
					rightDownY = Math.max(rightDownY, selectObjects[i].y1+selectObjects[i].height);
					compositeObject.add(selectObjects[i]);
					remove(selectObjects[i]);
				}

				compositeObject.Initial(leftUpX, leftUpY, rightDownX - leftUpX, rightDownY - leftUpY);
				add(compositeObject, new Integer(max));
				selectedNo = depth;
				selectObjects = null;
				depth ++;
				return true;
			}
		}
		return false;
	}
	public boolean ungroup(){
		if(selectedNo != GlobalVar.NO_SELECT){
			for(Component component : getComponents()){
				try {
					BasicObject basicObject = ((BasicObject) component);
					if(basicObject.no == selectedNo){
						if(basicObject.type == GlobalVar.GROUP){
							for(Component component2 : basicObject.getComponents()){
								try{
									BasicObject basicObject2 = ((BasicObject) component2);
									add(basicObject2, new Integer(basicObject2.no));
								}
								catch (Exception exception) {
								}
							}
							remove(basicObject);
							return true;
						}
					}
				} catch (Exception exception) {
				}
			}
		}
		return false;
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
