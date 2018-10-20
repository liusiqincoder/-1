package 火车票订票系统;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class manageUserPanel {

	private JFrame frame;
	private JTextField textField;
    private ArrayList<String> UserInfo=new ArrayList<>();
    private JTextArea userArea;    //用户名
    private JTextField pwdText;//密码
    private JTextField makeDateText;//创建时间
    private JTextField phoneText;//手机号码
	
    /*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageUserPanel window = new manageUserPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	/**
	 * Create the application.
	 */
	public manageUserPanel() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(),
				Global.Frame_width,Global.Frame_height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("请输入用户名：");
		textField.setBounds(Global.block_size*2,Global.Frame_height/3,
				3*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		userArea = new JTextArea();
		userArea.setBounds(5*Global.block_size,
				Global.Frame_height/3,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(userArea);
		
		JButton findButton = new JButton("查找");
		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RollBack.add(frame);
				new Thread(){
					ArrayList<String> content=new ArrayList<String>();
					showInfoPane sp=new showInfoPane(content,4);
					public void run(){
						while(sp.isLive()){
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							content.add("用户名");
							content.add("密码");
							content.add("创建时间");
							content.add("手机号码");
							if(userArea.getText().length()==0)
								FindUser(content, null, true);
							else{
								String[] users=userArea.getText().split(",|\n");
								
								for(int i=0;i<users.length;i++)
									FindUser(content, users[i], false);
							}
							sp.repaint();
							content.clear();
        			    }
					}
		       	}.start();
			}
		});
		findButton.setBackground(Color.LIGHT_GRAY);
		findButton.setToolTipText("输入为空则查找全部用户");
		findButton.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(findButton);
		
		JButton eraseButton = new JButton("删除");
		eraseButton.setBackground(Color.LIGHT_GRAY);
		eraseButton.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+2*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(eraseButton);
		eraseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "确认删除？");
				if(ret==JOptionPane.NO_OPTION||ret==JOptionPane.CANCEL_OPTION)
					return;
				if(DeleUser(userArea.getText()))
    				JOptionPane.showConfirmDialog(null, "删除成功");
				else
					JOptionPane.showConfirmDialog(null, "删除不成功");
			}
			
		});
		
		JButton backButton = new JButton("<");
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setBounds(10, 10, Global.block_size,Global.block_size/2);
		frame.getContentPane().add(backButton);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    frame.dispose();
				RollBack.Back();
				
			}
		});
		
		JButton modifyButton = new JButton("修 改");
		modifyButton.setBackground(Color.LIGHT_GRAY);
		modifyButton.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+3*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(modifyButton);
        modifyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "确认修改？");
				if(ret==JOptionPane.NO_OPTION||ret==JOptionPane.CANCEL_OPTION)
					return;
				modifyUser(userArea.getText(),pwdText.getText(),phoneText.getText());
			}
		});
		
		JTextField textField_1 = new JTextField();
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("请输入密码");
		textField_1.setEditable(false);
		textField_1.setBounds(Global.block_size*2,Global.Frame_height/3+Global.block_size,
				3*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		pwdText = new JTextField();
		pwdText.setBounds(5*Global.block_size,
				Global.Frame_height/3+Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(pwdText);
		pwdText.setColumns(10);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setEditable(false);
		textField_3.setText("请输入创建时间");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(Global.block_size*2,Global.Frame_height/3+2*Global.block_size,
				3*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		makeDateText = new JTextField();
		makeDateText.setBounds(5*Global.block_size,
				Global.Frame_height/3+2*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(makeDateText);
		makeDateText.setColumns(10);
		
		JTextField textField_5 = new JTextField();
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setEditable(false);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setText("请输入手机号码");
		textField_5.setBounds(Global.block_size*2,Global.Frame_height/3+3*Global.block_size,
				3*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		phoneText = new JTextField();
		phoneText.setBounds(5*Global.block_size,
				Global.Frame_height/3+3*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(phoneText);
		phoneText.setColumns(10);
		
		
	}
	
	private void FindUser(ArrayList<String> res,String name,boolean findAll){
		/*
		 * 从数据库用户信息表获取数据，并存在集合UserInfo中,同一用户的信息用逗号隔开,结果存在res中
		 * @param:findAll  是否查找所有用户
		 *        name   被查找的用户名 
		 */
		
	}
	
	private boolean DeleUser( String users){
		/*
		 * 将Users里的用户在数据库中删除
		 * 并返回是否删除成功
		 */
        String[] Users=users.split(",|\n");
       
        return true;
	}
	
	public void modifyUser(String name,String pwd,String phone){
		//修改用户信息
		
	}
	
}
