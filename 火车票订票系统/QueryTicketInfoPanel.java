package 火车票订票系统;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



public class QueryTicketInfoPanel {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField ticketNoText;
	private JTextField startStationText;
	private JTextField endStationText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryTicketInfoPanel window = new QueryTicketInfoPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QueryTicketInfoPanel() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		textField = new JTextField();
		textField.setBounds(Global.Frame_width/4,Global.Frame_height/3,
				2*Global.block_size, Global.block_size/2);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setText("车次号");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(Global.Frame_width/4,Global.Frame_height/3+Global.block_size,
				2*Global.block_size, Global.block_size/2);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setEditable(false);
		textField_1.setText("始发站");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(Global.Frame_width/4,Global.Frame_height/3+2*Global.block_size,
				2*Global.block_size, Global.block_size/2);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setEditable(false);
		textField_2.setText("终点站");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		
		ticketNoText = new JTextField();
		ticketNoText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3,Global.Frame_width/3, Global.block_size/2);
		ticketNoText.setColumns(10);
		
		startStationText = new JTextField();
		startStationText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3+Global.block_size,Global.Frame_width/3, Global.block_size/2);
		startStationText.setColumns(10);
		
		endStationText = new JTextField();
		endStationText.setBounds(Global.Frame_width/4+2*Global.block_size,
				Global.Frame_height/3+2*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		endStationText.setColumns(10);
		
		JButton backButton = new JButton("<");
		backButton.setBounds(10, 10, Global.block_size,Global.block_size/2);
		backButton.setFont(new Font("宋体", Font.BOLD, 12));
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    frame.dispose();
				RollBack.Back();
				
			}
		});
		
		JButton findBtn = new JButton("查 询");
		findBtn.setBounds(Global.Frame_width/2,
				Global.Frame_height/3+3*Global.block_size,Global.block_size*2,Global.block_size);
		findBtn.setHorizontalAlignment(SwingConstants.CENTER);
		findBtn.setVerticalAlignment(SwingConstants.CENTER);
		findBtn.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(textField_1);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(textField_2);
		frame.getContentPane().add(startStationText);
		frame.getContentPane().add(ticketNoText);
		frame.getContentPane().add(findBtn);
		frame.getContentPane().add(endStationText);
		frame.getContentPane().add(backButton);
		findBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RollBack.add(frame);
				new Thread(){
					ArrayList<String> arr=new ArrayList<String>();
					showInfoPane sp=new showInfoPane(arr,6);
					public void run(){
						while(sp.isLive()){
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							arr.add("车次号");
							arr.add("始发站");
							arr.add("终点站");
							arr.add("出发日期");
							arr.add("卧铺数量");
							arr.add("车票数量");
							findTicket(arr, ticketNoText.getText(),
							  startStationText.getText(), endStationText.getText());
							sp.repaint();
							arr.clear();
        			    }
					}
		       	}.start();
			}
		 });
//		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(),
//				Global.Frame_width,Global.Frame_height);
		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(),
				RollBack.recent().getWidth(),RollBack.recent().getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void findTicket(ArrayList<String> res,String ticketNo,String start,String end){
		/*
		 * 通过车次号（ticketNo），或开始站（start）和终点站(end)确定查找的车票信息,存入res中
		 * 
		 */
		
	}

}
