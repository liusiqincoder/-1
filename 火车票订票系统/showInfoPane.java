package 火车票订票系统;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;

public class showInfoPane {

	private JFrame frame;
	private JTextField textField;
    private ArrayList<String> content=null;
    private int colNum;
    private JTextArea textArea;
    private boolean Live=true;

	/**
	 * Create the application.
	 */
    /*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showInfoPane window = new showInfoPane(null,0);
//					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
    
	public showInfoPane(ArrayList<String> con,int colNum) {
		content=con;
		this.colNum=colNum;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		int width=frame.getWidth(),height=frame.getHeight();
		
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(Global.Frame_width/4,80,Global.Frame_width/2,Global.block_size/2);
		textField.setEditable(false);
		textField.setText("搜 索 结 果 如 下");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(Global.block_size, 160,Global.Frame_width-2*Global.block_size,500);
		frame.getContentPane().add(textArea);
		
		JButton backButton = new JButton("<");
		backButton.setBounds(10, 10,Global.block_size,Global.block_size/2);
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setToolTipText("返回");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    frame.dispose();
				RollBack.Back();
				Live=false;
				
			}
		});
		frame.getContentPane().add(backButton);
		
		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(),
				RollBack.recent().getWidth(),RollBack.recent().getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showContent();
	}

	public void repaint(){
		showContent();
	}

	private String format(String str,int length){
		StringBuilder res=new StringBuilder(str);
		int len=str.length();
		while(len<length){
			res.append(" ");
			len++;
		}
		return res.toString();
	}
	
	private void showContent(){
		textArea.setText("");
		textArea.setEditable(false);
		for(int i=0;i<content.size();i++){
			if(i!=0&&i%colNum==0)
				textArea.append("\r\n");
			textArea.append(format(content.get(i),24));
		}
	}
	
	public boolean isLive() {
		return Live;
	}

	public void setLive(boolean live) {
		Live = live;
	}

}
