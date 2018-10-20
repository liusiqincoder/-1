package 火车票订票系统;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class ManagerDo {

	private JFrame frame;
	private JTextField textField;
	private JTextField manageUser;
	private JTextField manageTicket;
	private JTextField manageTicketInfo;
	private Person person=null;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerDo window = new ManagerDo(new Person("",""));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public ManagerDo(Person person) {
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
		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(),
				RollBack.recent().getWidth(),RollBack.recent().getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setFont(new Font("宋体", Font.BOLD, 37));
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("信息管理");
		textField.setBounds(Global.Frame_width/4,100,
				Global.Frame_width/2, Global.block_size);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton backBtn = new JButton("<");
		backBtn.setToolTipText("返回");
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
		
		manageUser = new JTextField();
		manageUser.setBackground(Color.LIGHT_GRAY);
		manageUser.setEditable(false);
		manageUser.setHorizontalAlignment(SwingConstants.CENTER);
		manageUser.setText("用 户 管 理");
		manageUser.setBounds(Global.Frame_width/4,
				Global.Frame_height/3,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(manageUser);
		manageUser.setColumns(10);
		
		JButton userGo = new JButton(">");
		userGo.setBackground(Color.LIGHT_GRAY);
		userGo.setBounds(Global.Frame_width*3/4-Global.block_size,
				Global.Frame_height/3,Global.block_size, Global.block_size/2);
		frame.getContentPane().add(userGo);
		userGo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RollBack.add(frame);
				new manageUserPanel();
			}
			
		});
		
		manageTicket = new JTextField();
		manageTicket.setBackground(Color.LIGHT_GRAY);
		manageTicket.setText("订 单 管 理");
		manageTicket.setHorizontalAlignment(SwingConstants.CENTER);
		manageTicket.setEditable(false);
		manageTicket.setBounds(Global.Frame_width/4,
				Global.Frame_height/3+Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(manageTicket);
		manageTicket.setColumns(10);
		
		JButton ticketGo = new JButton(">");
		ticketGo.setBackground(Color.LIGHT_GRAY);
		ticketGo.setBounds(Global.Frame_width*3/4-Global.block_size,
				Global.Frame_height/3+Global.block_size,Global.block_size, Global.block_size/2);
		frame.getContentPane().add(ticketGo);
		ticketGo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RollBack.add(frame);
				new manageTicketPanel();
			}
			
		});
		
		manageTicketInfo = new JTextField();
		manageTicketInfo.setBackground(Color.LIGHT_GRAY);
		manageTicketInfo.setEditable(false);
		manageTicketInfo.setText("车 票 信 息 管 理");
		manageTicketInfo.setHorizontalAlignment(SwingConstants.CENTER);
		manageTicketInfo.setBounds(Global.Frame_width/4,Global.Frame_height/3+2*Global.block_size,
				Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(manageTicketInfo);
		manageTicketInfo.setColumns(10);
		
		JButton ticketInfoGo = new JButton(">");
		ticketInfoGo.setBackground(Color.LIGHT_GRAY);
		ticketInfoGo.setBounds(Global.Frame_width*3/4-Global.block_size,
				Global.Frame_height/3+2*Global.block_size,Global.block_size, Global.block_size/2);
		frame.getContentPane().add(ticketInfoGo);
		ticketInfoGo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RollBack.add(frame);
				new manageTicketInfoPanel();
			}
			
		});
	}
}
