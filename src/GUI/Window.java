package GUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import Global.GlobalVar;
import Global.ModeFactory;


public class Window extends JFrame {
	private JMenuBar jmb = new JMenuBar();
	private JMenu fileMenu = new JMenu("File"), editMenu = new JMenu("Edit");
	private JMenuItem[] editItem = new JMenuItem[3];

	private ButtonHandler bh = new ButtonHandler();// 按鈕事件
	private MenuHandler mh = new MenuHandler();// 功能表單事件
	private ButtonTable buttons  = new ButtonTable(); // test
	private Canvas canvas;
    private Modes.Mode mode;
    
	public Window() {
		this.setTitle("UML editor");
		this.setSize(GlobalVar.WINDOW_SIZE_H, GlobalVar.WINDOW_SIZE_W);

		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initMenuBar();
		initButton();
		initCanvas();
		this.update(getGraphics());
	}
	void initMenuBar() {
		this.setJMenuBar(jmb);
		jmb.add(fileMenu);
		jmb.add(editMenu);
		editItem[0] = new JMenuItem("Group");
		editItem[1] = new JMenuItem("UnGroup");
		editItem[2] = new JMenuItem("Change object name");
		editMenu.add(editItem[0]);
		editMenu.add(editItem[1]);
		editMenu.add(editItem[2]);
		editItem[0].addActionListener(mh);
		editItem[1].addActionListener(mh);
		editItem[2].addActionListener(mh);
	}
	void initButton(){
		try{
			Container cp = this.getContentPane();
			String imagePath = "image/";
			File dir = new File(imagePath);
			String[] imageName = dir.list();		
			ImageButton[] imageButton = new ImageButton[imageName.length];
			for(int i = 0; i < imageName.length; i++){
			    ImageIcon image = new ImageIcon(imagePath+imageName[i]);
				imageButton[i] = new ImageButton(i, image);
				imageButton[i].addActionListener(bh);
				cp.add(imageButton[i]);
				buttons.add(imageButton[i]);
			}
		}
		catch (Exception e) {
			System.out.println("error");
		}
	}
	void initCanvas(){
		canvas = new Canvas(getGraphics());
		add(canvas);
	}
	void changeMode(){
		System.out.println("now "+buttons.no);
		canvas.removeMouseListener(mode);
		mode = ModeFactory.create(buttons.no, canvas);
		canvas.addMouseListener(mode);
	}
	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			buttons.Update();
			canvas.selectedButton = buttons.no;
			changeMode();
		}
	}
	class MenuHandler implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == editItem[0]) {
				canvas.group();
			} 
			else if (ae.getSource() == editItem[1]) {
				canvas.ungroup();
			}
			else if (ae.getSource() == editItem[2]) {
			    String rename = JOptionPane.showInputDialog("請輸入物件名稱");
			    if(rename != null)
			    	canvas.renameBasicObject(rename);
			}
		}
	}
}
