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

public class manageTicketInfoPanel {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField ticketNoText;
	private JTextField startStationText;
	private JTextField endStationText;
	private JButton addBtn;
	private JButton deleBtn;
	private JButton modifyBtn;
	private JTextField textField_6;
	private JTextField startDateText;
	private JTextField walkTicketNumText;
	private JTextField textField_7;
	private JTextField ticketNumText;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageTicketInfoPanel window = new manageTicketInfoPanel();
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
	public manageTicketInfoPanel() {
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
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setText("车次号");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(Global.block_size*2,Global.Frame_height/3,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setEditable(false);
		textField_1.setText("始发站");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(Global.block_size*2,Global.Frame_height/3+Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setEditable(false);
		textField_2.setText("终点站");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(Global.block_size*2,Global.Frame_height/3+2*Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		ticketNoText = new JTextField();
		ticketNoText.setBounds(5*Global.block_size,
				Global.Frame_height/3,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(ticketNoText);
		ticketNoText.setColumns(10);
		
		startStationText = new JTextField();
		startStationText.setBounds(5*Global.block_size,
				Global.Frame_height/3+Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(startStationText);
		startStationText.setColumns(10);
		
		endStationText = new JTextField();
		endStationText.setBounds(5*Global.block_size,
				Global.Frame_height/3+2*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(endStationText);
		endStationText.setColumns(10);
		
		JButton backButton = new JButton("<");
		backButton.setFont(new Font("宋体", Font.BOLD, 12));
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
		
		JButton findBtn = new JButton("查 询");
		findBtn.setBackground(Color.LIGHT_GRAY);
		findBtn.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(findBtn);
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
		
		addBtn = new JButton("添 加");
		addBtn.setBackground(Color.LIGHT_GRAY);
		addBtn.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+2*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "确认添加？");
				if(ret==JOptionPane.NO_OPTION||ret==JOptionPane.CANCEL_OPTION)
					return;
				String ticketNo=ticketNoText.getText();
				String start=startStationText.getText();
				String end=endStationText.getText();
				String date=startDateText.getText();
				String walk=walkTicketNumText.getText();
				String ticketNum=ticketNumText.getText();
				if(ticketNo.equals("")||start.equals("")||end.equals("")||
						date.equals("")||walk.equals("")||ticketNum.equals("")){
					JOptionPane.showMessageDialog(null, "车票信息不全，请补充");
				}
				if(!addTicketType(ticketNo, start, end, date, walk, ticketNum)){
					JOptionPane.showMessageDialog(null, "添加不成功");
					return;
				}
				JOptionPane.showMessageDialog(null, "添加成功");
			}
		});
		
		deleBtn = new JButton("删 除");
		deleBtn.setBackground(Color.LIGHT_GRAY);
		deleBtn.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+3*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(deleBtn);
		deleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "确认删除？");
				if(ret==JOptionPane.NO_OPTION||ret==JOptionPane.CANCEL_OPTION)
					return;
				String ticketNo=ticketNoText.getText();
				String start=startStationText.getText();
				String end=endStationText.getText();
				if(start.equals("")&&!end.equals("")){
					JOptionPane.showMessageDialog(null, "请添加终点站");
					return;
				}
				if(!start.equals("")&&end.equals("")){
					JOptionPane.showMessageDialog(null, "请添加始发站");
					return;
				}
				deleTicket(ticketNo, start, end);
				JOptionPane.showMessageDialog(null, "删除成功");
			}
		});
		
		modifyBtn = new JButton("修 改");
		modifyBtn.setBackground(Color.LIGHT_GRAY);
		modifyBtn.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+4*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(modifyBtn);
		modifyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "确认修改？");
				if(ret==JOptionPane.NO_OPTION||ret==JOptionPane.CANCEL_OPTION)
					return;
				String ticketNo=ticketNoText.getText();
				String start=startStationText.getText();
				String end=endStationText.getText();
				String date=startDateText.getText();
				String walk=walkTicketNumText.getText();
				String ticketNum=ticketNumText.getText();
				if(ticketNo.equals("")){
					JOptionPane.showMessageDialog(null, "车次号不能为空");
					return;
				}
				modifyTicket(ticketNo, start, end, date, walk, ticketNum);
				JOptionPane.showMessageDialog(null, "修改成功");
			}
		});
		
		textField_6 = new JTextField();
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setText("出发日期");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEditable(false);
		textField_6.setBounds(Global.block_size*2,Global.Frame_height/3+3*Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		startDateText = new JTextField();
		startDateText.setBounds(5*Global.block_size,
				Global.Frame_height/3+3*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(startDateText);
		startDateText.setColumns(10);
		
		walkTicketNumText = new JTextField();
		walkTicketNumText.setBackground(Color.LIGHT_GRAY);
		walkTicketNumText.setEditable(false);
		walkTicketNumText.setText("卧铺数量");
		walkTicketNumText.setHorizontalAlignment(SwingConstants.CENTER);
		walkTicketNumText.setBounds(Global.block_size*2,Global.Frame_height/3+4*Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(walkTicketNumText);
		walkTicketNumText.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(5*Global.block_size,
				Global.Frame_height/3+4*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		ticketNumText = new JTextField();
		ticketNumText.setBackground(Color.LIGHT_GRAY);
		ticketNumText.setEditable(false);
		ticketNumText.setText("余量");
		ticketNumText.setHorizontalAlignment(SwingConstants.CENTER);
		ticketNumText.setBounds(Global.block_size*2,Global.Frame_height/3+5*Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(ticketNumText);
		ticketNumText.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(5*Global.block_size,
				Global.Frame_height/3+5*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(), Global.Frame_width, Global.Frame_height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void findTicket(ArrayList<String> res,String ticketNo,String start,String end){
		/*
		 * 通过车次号（ticketNo），或开始站（start）和终点站(end)确定查找的车票信息,存入res中
		 * 
		 */
	}
	
	private boolean addTicketType(String ticketNo,String start,String end,
			String date,String walkTicketnum,String TicketNum){
		/*
		 * 添加车票类型(车次号，始发站，终点站，出发时间，卧铺数量，车票数量)
		 */
		
		return true;
	}
	
	private void modifyTicket(String ticketNo,String start,String end,
			String date,String walkTicketnum,String TicketNum){
		/*
		 * 添加车票类型(车次号，始发站，终点站，出发时间，卧铺数量，车票数量)
		 */
		
	}
	
	private void deleTicket(String ticketNo,String start,String end){
		/*
		 * 删除ticketNo的票，且删除(start,end)的
		 */
	}
	
}
