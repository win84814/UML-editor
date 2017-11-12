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

public class Canvas extends JLayeredPane implements MouseListener {
	public final static int SELECT = 0, ASSOCIATION = 1, GENERALIZATION = 2, COMPOSITION = 3, CLASS = 4, USECASE = 5, COMPOSITE = 6;
	int selectedButton, selectedNo, onPressedX, onPressedY;
	Integer depth;
	BasicObject[] selectObjects;
	public Canvas() {
		super();
		this.setBounds(140, 20, 1030, 700);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.addMouseListener(this);
		depth = 99;
		selectedNo = -1;
		selectedButton = -1;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//repaint();
		int clickX = e.getX();
		int clickY = e.getY();
		try{
			if (e.getButton() == MouseEvent.BUTTON1) {
				cancelSelect();
				if (selectedButton == SELECT) {
					BasicObject basicObject = clickSomething(clickX, clickY);
					basicObject.getComponent(0).setVisible(true);
					selectedNo = basicObject.no;
				}
				else if (selectedButton == CLASS) {
					add(new ClassObject(depth, clickX, clickY), depth);
					depth++;
				} else if (selectedButton == USECASE) {
					add(new UsecaseObject(depth, clickX, clickY), depth);
					depth++;
				}
			}
		}
		catch (Exception exception) {
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		onPressedX = e.getX();
		onPressedY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int releasedX = e.getX();
		int releasedY = e.getY();
		if(onPressedX != releasedX && onPressedY != releasedY){
			if (selectedButton == SELECT){
					BasicObject fromeComponent = clickSomething(onPressedX, onPressedY);
					if(fromeComponent.type != 0){
						if(fromeComponent.type >=CLASS){
							if(fromeComponent.getComponent(0).isVisible()){
								int valueX = releasedX - onPressedX;
								int valueY = releasedY - onPressedY;
								//System.out.println("Move from "+fromeComponent.x1 +" "+fromeComponent.y1 +" to "+(fromeComponent.x1+valueX) +" "+(fromeComponent.y1+valueY));
								fromeComponent.relocate(valueX, valueY);
							}
						}
					}
					else{
						cancelSelect();
						selectObjects = null;
						int leftUpX = onPressedX < releasedX ? onPressedX : releasedX;
						int leftUpY = onPressedY < releasedY ? onPressedY : releasedY;
						int rightDownX = onPressedX > releasedX ? onPressedX : releasedX;
						int rightDownY = onPressedY > releasedY ? onPressedY : releasedY;
						ArrayList<BasicObject> temp = new ArrayList<BasicObject>();
						for (Component component : getComponents()) {
							try{
								BasicObject basicObject = (BasicObject) component;
								if(basicObject.x1 > leftUpX && basicObject.y1 > leftUpY && 
									basicObject.x1 + basicObject.width < rightDownX && basicObject.y1 + basicObject.height < rightDownY){
									// basicObject show four points and set to array
									basicObject.getComponent(0).setVisible(true);
									temp.add(basicObject);
								}
							}
							catch (Exception exception) {
							}
						}
						selectObjects = temp.toArray(new BasicObject[temp.size()]);
					}
			}
			else if (selectedButton == ASSOCIATION || selectedButton == GENERALIZATION || selectedButton == COMPOSITION) {
				BasicObject fromeComponent = (BasicObject) getComponentAt(onPressedX, onPressedY);
				BasicObject toComponent = (BasicObject) getComponentAt(releasedX, releasedY);
				//System.out.println("HH " + fromeComponent.height + " HHH " + toComponent.height);
				if (fromeComponent.type <= USECASE && toComponent.type <= USECASE && fromeComponent.no != toComponent.no) {
					int fromArea = fromeComponent.whichArea(onPressedX, onPressedY);
					int toArea = toComponent.whichArea(releasedX, releasedY);
					boolean duplicate = false;
					LineObject lineObject;
					switch (selectedButton) {
					case ASSOCIATION:
						lineObject = new AssociationLine(fromeComponent, toComponent, fromArea, toArea);
						break;
					case GENERALIZATION:
						lineObject = new GeneralizationLine(fromeComponent, toComponent, fromArea, toArea);
						break;
					case COMPOSITION:
						lineObject = new CompositionLine(fromeComponent, toComponent, fromArea, toArea);
						break;
					default:
						lineObject = new LineObject();
					}
					for (Component component : getComponents()) {
						try {
							if (((LineObject) component).from.no == fromeComponent.no
									&& ((LineObject) component).to.no == toComponent.no
									&& ((LineObject) component).fromArea == fromArea
									&& ((LineObject) component).toArea == toArea
									&& ((LineObject) component).type == selectedButton) {
								System.out.println("DUPIDATE!!!!!!!!!!!!");
								duplicate = true;
							}
						} catch (Exception exception) {
						}
					}
					// add(new LineObject(fromeComponent, toComponent, fromArea,
					// toArea));
					if (!duplicate)
						add(lineObject);
					//System.out.println("AREA!!!!!!!!!!!!!!!" + fromArea + " " + toArea);
				} else {
					//System.out.println("can't draw line");
				}
			}
		}
		repaint();
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
		//selectObjects = null;
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
				CompositeObject compositeObject = new CompositeObject(depth);
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
		if(selectedNo != -1){
			for(Component component : getComponents()){
				try {
					BasicObject basicObject = ((BasicObject) component);
					if(basicObject.no == selectedNo){
						if(basicObject.type == COMPOSITE){
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
