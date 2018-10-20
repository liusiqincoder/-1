package 火车票订票系统;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import 超市收银系统.admin;
import 超市收银系统.cashier;
import 超市收银系统.loginPane;
import 超市收银系统.user;

public class Welcome {

	private JFrame frame;
	private JTextField txtJsfsjfi;
	private JButton loginBtn;
	private JButton ticketFindBtn;
	private JButton manageInfoBtn;
    private Person person=null;
    private JTextField textField;
	/**
	 * Launch the application.
	 * manager 管理员
	 * 管理员登陆后才可使用信息管理子系统
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		frame.setBounds(Global.startX, Global.startY, Global.Frame_width, Global.Frame_height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setText("火车票订票系统");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(Global.Frame_width/4,80,Global.Frame_width/2,Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("用户登陆");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(Global.Frame_width/4,Global.Frame_height/3,
				Global.Frame_width/4, 4*Global.block_size);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * 跳转到登陆模块
				 */
				Object[] obj2 ={ "用户", "管理员"};  
				String s = (String) JOptionPane.showInputDialog(frame,"请选择你的登陆身份:\n", "登陆", 
						JOptionPane.PLAIN_MESSAGE, null, obj2, "顾客"); 
				if("用户".equals(s))
					person=new Person("","");
				else if("管理员".equals(s))
					person=new Manager("", "");
				else 
					return;
				RollBack.add(frame);
				new LoginPane(person);
			}
		});
		
		JButton registerBtn = new JButton("用户注册");
		registerBtn.setBackground(Color.LIGHT_GRAY);
		registerBtn.setBounds(Global.Frame_width/2,Global.Frame_height/3,
				Global.Frame_width/4, 2*Global.block_size);
		frame.getContentPane().add(registerBtn);
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 */
				Object[] obj2 ={ "用户", "管理员"};  
				String s = (String) JOptionPane.showInputDialog(frame,"请选择你的注册身份:\n", "注册", 
						JOptionPane.PLAIN_MESSAGE, null, obj2, "顾客"); 
				if("用户".equals(s))
					person=new Person("","");
				else if("管理员".equals(s))
					person=new Manager("", "");
				else 
					return;
				RollBack.add(frame);
				new registerPane();
			}
		});
		
		JButton TicketQueryBtn = new JButton("火车票查询");
		TicketQueryBtn.setBackground(Color.LIGHT_GRAY);
		TicketQueryBtn.setBounds(Global.Frame_width/2,Global.Frame_height/3+2*Global.block_size,
				Global.Frame_width/4, 2*Global.block_size);
		frame.getContentPane().add(TicketQueryBtn);
		TicketQueryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RollBack.add(frame);
				new QueryTicketInfoPanel();
			}
		});
		
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
