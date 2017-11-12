import java.awt.Color;

import javax.swing.ButtonGroup;

public class ButtonTable extends ButtonGroup{
	int no;
	public ButtonTable() {
		super();
		no = -1;
	}
	public void Update () {
		int clickNo = Integer.parseInt(this.getSelection().getActionCommand());
		if(clickNo == no){
			no = -1;
			InitState();
		}
		else{
			if(no != -1){
				buttons.get(no).setSelected(false);
				buttons.get(no).setBackground(Color.white);
			}
			buttons.get(clickNo).setSelected(true);
			buttons.get(clickNo).setBackground(Color.black);
			no = clickNo;
		}
		System.out.println(no);
	}
	private void InitState(){
		clearSelection();
		for(int i = 0; i < this.getButtonCount(); i ++){
			buttons.get(i).setBackground(Color.white);
		}
	}
}
