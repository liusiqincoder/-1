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

public class orderTicketPane {

	private JFrame frame;
	private JTextField textField;
	private JTextField ticketText;
	private Person person;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderTicketPane window = new orderTicketPane(new Person("",""));
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
	public orderTicketPane(Person person) {
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
		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(), Global.Frame_width,Global.Frame_height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		textField.setText("请输入车票名");
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(Global.Frame_width/4,Global.Frame_height/3,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		ticketText = new JTextField();
		ticketText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(ticketText);
		ticketText.setColumns(10);
		
		JButton orderBtn = new JButton("订 票");
		orderBtn.setBackground(Color.LIGHT_GRAY);
		orderBtn.setBounds(Global.Frame_width/2,
				Global.Frame_height/3+2*Global.block_size,Global.block_size*2,Global.block_size);
		frame.getContentPane().add(orderBtn);
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int sel=JOptionPane.showConfirmDialog(frame, "确认订票？");
				if(sel!=JOptionPane.YES_OPTION)
					return;
				String ticket=ticketText.getText();
				if(orderTicket(ticket))
					JOptionPane.showMessageDialog(frame, "订票成功");
				else
					JOptionPane.showMessageDialog(frame, "订票失败");
				
			}
		});
	}

	private boolean orderTicket(String ticketName){
		/*
		 * 用户person定票
		 * 车票名  ticketName
		 * 返回订票结果
		 */
		
		return true;
	}
	
}
