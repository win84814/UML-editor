package GUI;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import Global.GlobalVar;
import Shapes.BasicObject;

public class ButtonTable extends ButtonGroup{
	int no;
	public ButtonTable() {
		super();
		no = GlobalVar.NO_SELECT;
	}
	public void Update () {
		int clickNo = Integer.parseInt(this.getSelection().getActionCommand());
		clearSelected();
		if(clickNo == no){
			no = GlobalVar.NO_SELECT;
		}
		else{
			buttons.get(clickNo).setSelected(true);
			buttons.get(clickNo).setBackground(Color.black);
			no = clickNo;
		}
	}
	private void clearSelected(){
		clearSelection();
		for(AbstractButton button : buttons){
			button.setBackground(Color.white);
		}
	}
}
