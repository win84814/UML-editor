import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window extends JFrame {
	private JMenuBar jmb = new JMenuBar();
	private JMenu fileMenu = new JMenu("File"), editMenu = new JMenu("Edit");
	private JMenuItem[] fileItem = new JMenuItem[1], editItem = new JMenuItem[3];
	private ButtonHandler bh = new ButtonHandler();// 功能表單事件
	private ButtonTable buttons  = new ButtonTable(); // test
	private Canvas canvas;
    //private JPanel jPanel;
    
	Window() {
		
		// init window
		this.setTitle("UML editor");
		this.setSize(1200, 800);
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
		this.setJMenuBar(jmb); // 加入工具列
		// init File Menu
		jmb.add(fileMenu);
		fileItem[0] = new JMenuItem("Save as");
		fileMenu.add(fileItem[0]);

		// init Edit Menu
		jmb.add(editMenu);
		editItem[0] = new JMenuItem("Group");
		editItem[1] = new JMenuItem("UnGroup");
		editItem[2] = new JMenuItem("Change object name");
		editMenu.add(editItem[0]);
		editMenu.add(editItem[1]);
		editMenu.add(editItem[2]);

		// toolbar event
		fileItem[0].addActionListener(bh);
		editItem[0].addActionListener(bh);
		editItem[1].addActionListener(bh);
		editItem[2].addActionListener(bh);
	}
	void initButton(){
		Container cp = this.getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.WHITE);
		String[] imageSource = {"image/select.png", "image/association.png", "image/generalization.png", 
								"image/composition.png", "image/class.png", "image/use case.png"};

		ImageButton[] imageButton = new ImageButton[6];
		for(int i = 0; i < imageSource.length; i++){
		    ImageIcon image = new ImageIcon(imageSource[i]);
			imageButton[i] = new ImageButton(i, image);
			imageButton[i].addActionListener(bh);
			cp.add(imageButton[i]);

			buttons.add(imageButton[i]); // test
		}
	}
	void initCanvas(){
		Container cp = this.getContentPane();
		canvas = new Canvas();
		cp.add(canvas);
	}
	// 功能表單事件處理
	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == fileItem[0]) {
				JOptionPane.showMessageDialog(null, "Save as", "File", JOptionPane.INFORMATION_MESSAGE);
			} 
			else if (ae.getSource() == editItem[0]) {
				if(canvas.group()){
					System.out.println("Group success !!");
				}
				else{
					System.out.println("Group fail !!!!!!");
				}
			} 
			else if (ae.getSource() == editItem[1]) {
				if(canvas.ungroup()){
					System.out.println("Ungroup success !!");
				}
				else{
					System.out.println("Ungroup fail !!!!!!");
				}
			}
			else if (ae.getSource() == editItem[2]) {
			    String rename = JOptionPane.showInputDialog("請輸入物件名稱");
			    if(rename != null)
			    	canvas.renameBasicObject(rename);
			    System.out.println(rename);
			}
			else if(ae.getSource().getClass().isInstance(new ImageButton())){
				buttons.Update();
				canvas.selectedButton = buttons.no;
			}
		}
	}
}
