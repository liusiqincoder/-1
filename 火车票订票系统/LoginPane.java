package 火车票订票系统;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class LoginPane {

	private JFrame frame;
	private Person person=null;
	private JTextField textField;
	private JTextField userText;
	private JTextField textField_1;
	private JTextField pwdText;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPane window = new LoginPane(new Person("",""));
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
	public LoginPane(Person person) {
		this.person=person;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setText("用户名");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(Global.Frame_width/4,Global.Frame_height/3,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		userText = new JTextField();
		userText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(userText);
		userText.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setEditable(false);
		textField_1.setText("密 码");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(Global.Frame_width/4,Global.Frame_height/3+Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		pwdText = new JTextField();
		pwdText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3+Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(pwdText);
		pwdText.setColumns(10);
		
		JButton backBtn = new JButton("<");
		backBtn.setBackground(Color.LIGHT_GRAY);
		backBtn.setBounds(10, 10, Global.block_size,Global.block_size/2);
		frame.getContentPane().add(backBtn);
        backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				RollBack.Back();
			}
		});
		
		JButton loginBtn = new JButton("登 陆");
		loginBtn.setBackground(Color.LIGHT_GRAY);
		loginBtn.setBounds(Global.Frame_width/2,
				Global.Frame_height/3+2*Global.block_size,Global.block_size*2,Global.block_size);
		frame.getContentPane().add(loginBtn);
		frame.setBounds(RollBack.recent().getX(), RollBack.recent().getY(), Global.Frame_width,Global.Frame_height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=userText.getText();
				String pwd=pwdText.getText();
				if(person.getClass()==Manager.class){
					if(!exisitManeger(name,pwd)){
						JOptionPane.showMessageDialog(frame, "管理员不存在");
						return;
					}
					person.setUser_id(name);
					person.setUser_pw(pwd);
					person.setLogin(true);
//					JOptionPane.showMessageDialog(frame, "登陆成功");
					RollBack.add(frame);
					new ManagerDo(person);
				}else if(person.getClass()==Person.class){
					if(!exisitPerson(name,pwd)){
						JOptionPane.showMessageDialog(frame, "用户不存在");
						return;
					}
					person.setUser_id(name);
					person.setUser_pw(pwd);
					person.setLogin(true);
//					JOptionPane.showMessageDialog(frame, "登陆成功");
					RollBack.add(frame);
					new personDo(person);
				}else{
					JOptionPane.showMessageDialog(frame, "LoginPane类加载错误");
					return;
				}
			}
		});
	}

	private boolean exisitManeger(String name,String pwd){
		/*
		 * 登陆页面
		 * 通过name(user_id)和pwd(user_pwd)查找是否存在用户
		 */
		
		return false;
	}
	
	private boolean exisitPerson(String name,String pwd){
		/*
		 * 登陆页面
		 * 通过name(user_id)和pwd(user_pwd)查找是否存在管理员
		 */
		
		return false;
	}
	
}
