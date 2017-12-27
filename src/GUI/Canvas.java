package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.net.ssl.SSLException;
import javax.sound.sampled.Line;
import javax.swing.*;

import Global.GlobalVar;
import Shapes.AssociationLine;
import Shapes.BasicObject;
import Shapes.ClassboxObject;
import Shapes.GroupObject;
import Shapes.CompositionLine;
import Shapes.GeneralizationLine;
import Shapes.LineObject;
import Shapes.UsecaseObject;

public class Canvas extends JLayeredPane  {
	public int selectedButton;
	public int selectedNo;
	public Integer depth;
	public BasicObject[] selectObjects;
	public Canvas() {
		super();
		this.setBounds(140, 20, GlobalVar.CANVAS_SIZE_W, GlobalVar.CANVAS_SIZE_H);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		depth = 99;
		selectedNo = GlobalVar.NO_SELECT;
		selectedButton = GlobalVar.NO_SELECT;
	}
	public void cancelSelect() {
		for (Component component : getComponents()) {
			try {
				BasicObject basicObject = (BasicObject) component;
				basicObject.getComponent(0).setVisible(false);
				
			} catch (Exception exception) {
			}
		}
		selectedNo = -1;
	}
	public void renameBasicObject(String newName){
		if(selectedNo != -1){
			for (Component component : getComponents()) {
				try {
					if(((BasicObject) component).no == selectedNo){
						((BasicObject) component).name = newName;
					}
				} catch (Exception exception) {
				}
			}
		}
		else{
			System.out.println("rename fail !!");
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
		BasicObject result = new BasicObject();
		int max = Integer.MIN_VALUE;
		for (Component component : getComponents()) {
			try {
				BasicObject basicObject = ((BasicObject) component);
				if((clickX >= basicObject.x1 && clickX <= basicObject.x1+basicObject.width &&
						clickY >= basicObject.y1 && clickY <= basicObject.y1+basicObject.height)){
					if(basicObject.no > max){
						max = basicObject.no;
						result = basicObject;
					}
				}
			} catch (Exception exception) {
			}
		}
		return result;
	}
}
