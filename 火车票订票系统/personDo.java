package 火车票订票系统;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class personDo {

	private JFrame frame;
	private Person person=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					personDo window = new personDo(new Person("",""));
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
	public personDo(Person person) {
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
		
		JButton orderTicketBtn = new JButton("订票");
		orderTicketBtn.setBackground(Color.LIGHT_GRAY);
		orderTicketBtn.setBounds(Global.Frame_width/4,Global.Frame_height/4,Global.Frame_width/4,Global.Frame_height/4);
		frame.getContentPane().add(orderTicketBtn);
		orderTicketBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RollBack.add(frame);
				new orderTicketPane(person);
			}
		});
		
		JButton historyBtn = new JButton("订票记录");
		historyBtn.setBackground(Color.LIGHT_GRAY);
		historyBtn.setBounds(Global.Frame_width/2,Global.Frame_height/4,Global.Frame_width/4,Global.Frame_height/4);
		frame.getContentPane().add(historyBtn);
        historyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> arr=new ArrayList<>();
				arr.add("订单号");
				arr.add("订单车票");
				arr.add("创建时间");
				queryTicket(arr);
				RollBack.add(frame);
				new showInfoPane(arr, 3);
			}
		});
		
		JButton queryTicketInfoBtn = new JButton("查看火车票信息");
		queryTicketInfoBtn.setBackground(Color.LIGHT_GRAY);
		queryTicketInfoBtn.setBounds(Global.Frame_width/4,Global.Frame_height/2,Global.Frame_width/2 , Global.Frame_height/8);
		frame.getContentPane().add(queryTicketInfoBtn);
		frame.setBounds(100, 100,Global.Frame_width,Global.Frame_height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		queryTicketInfoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RollBack.add(frame);
				new QueryTicketInfoPanel();
			}
		});
		
	}

	private void queryTicket(ArrayList<String> arr){
		/*
		 * 按personuser_id查找车票，将<车票号，车票编号，创建时间>存入arr
		 */
		
	}
	
}
