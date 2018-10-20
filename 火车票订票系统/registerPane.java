package 火车票订票系统;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class registerPane {

	private JFrame frame;
    private JTextField textField;
    private JTextField nameText;
    private JTextField textField_2;
    private JTextField pwdText;
    private JTextField textField_4;
    private JTextField phoneText;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerPane window = new registerPane();
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
	public registerPane() {
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
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEditable(false);
		textField.setText("用户名");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(Global.Frame_width/4,Global.Frame_height/3,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		nameText = new JTextField();
		nameText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setEditable(false);
		textField_2.setText("密 码");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(Global.Frame_width/4,Global.Frame_height/3+Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		pwdText = new JTextField();
		pwdText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3+Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(pwdText);
		pwdText.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setEditable(false);
		textField_4.setText("手机号码");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBounds(Global.Frame_width/4,Global.Frame_height/3+2*Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		phoneText = new JTextField();
		phoneText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3+2*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(phoneText);
		phoneText.setColumns(10);
		
		JButton registerBtn = new JButton("注 册");
		registerBtn.setBackground(Color.LIGHT_GRAY);
		registerBtn.setBounds(Global.Frame_width/2,
				Global.Frame_height/3+3*Global.block_size,Global.block_size*2,Global.block_size);
		frame.getContentPane().add(registerBtn);
		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(), Global.Frame_width, Global.Frame_height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=nameText.getText();
				String pwd=pwdText.getText();
				String phone=phoneText.getText();
				if(name.length()==0||pwd.length()==0||phone.length()==0){
					JOptionPane.showMessageDialog(frame, "用户名，密码，手机号码不能为空");
					return;
				}
				if(!register(name,pwd,phone)){
					JOptionPane.showMessageDialog(frame, "注册失败");
					return;
				}
				JOptionPane.showMessageDialog(frame, "注册成功");
			}
		});
	}

	private boolean register(String name,String pwd,String phone){
		/*
		 * 注册用户
		 * name 用户id   pwd用户密码  phone 用户手机号码
		 * 返回注册的结果
		 */
		
		return true;
	}
	
}
