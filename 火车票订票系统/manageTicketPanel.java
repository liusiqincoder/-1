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

public class manageTicketPanel {
	/*
	 * 订单信息管理模块
	 */

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextArea TickNumArea;    //订单号
	private JTextArea ticketUserArea;  //订单用户
	private JTextField textField_1;
	private JTextArea ticketInfoArea;  //订单车票
	private JTextField textField_3;
	private JTextField makeDateArea; //创建时间
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageTicketPanel window = new manageTicketPanel();
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
	public manageTicketPanel() {
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
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("请输入订单号");
		textField.setBounds(Global.block_size*2,Global.Frame_height/3,
				3*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton findButton = new JButton("查 找");
		findButton.setBackground(Color.LIGHT_GRAY);
		findButton.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(findButton);
		findButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RollBack.add(frame);
				new Thread(){
					ArrayList<String> res=new ArrayList<String>();
					showInfoPane sp=new showInfoPane(res,4);
					public void run(){
						while(sp.isLive()){
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							String ticket=TickNumArea.getText();
							String userName=ticketUserArea.getText();
							String ticketInfo=ticketInfoArea.getText();
							String date=makeDateArea.getText();
							res.add("订单号");
							res.add("订单用户");
							res.add("订单车票");
							res.add("创建时间");
							if(!findTicketInfo(res, ticket, userName, ticketInfo, date)){
								JOptionPane.showMessageDialog(null, "找不到该用户");
								break;
							}
							sp.repaint();
							res.clear();
        			    }
					}
		       	}.start();
			}
		});
		
		JButton eraseButton = new JButton("删 除");
		eraseButton.setBackground(Color.LIGHT_GRAY);
		eraseButton.setBounds(Global.Frame_width-3*Global.block_size,
				Global.Frame_height/3+2*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(eraseButton);
		eraseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "确认删除？");
				if(ret==JOptionPane.CANCEL_OPTION||ret==JOptionPane.NO_OPTION)
					return;
				String ticketNum=TickNumArea.getText();
				String userName=ticketUserArea.getText();
				String ticketInfo=ticketInfoArea.getText();
				String date=makeDateArea.getText();
				if(!DeleUser(ticketNum, userName, ticketInfo, date)){
					JOptionPane.showMessageDialog(null, "删除失败");
					return;
				}
				JOptionPane.showMessageDialog(null, "删除成功");
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
				if(ret==JOptionPane.CANCEL_OPTION||ret==JOptionPane.NO_OPTION)
					return;
				String ticketNum=TickNumArea.getText();
				String userName=ticketUserArea.getText();
				String ticketInfo=ticketInfoArea.getText();
				String date=makeDateArea.getText();
				if(!modifyTicketInfo(ticketNum, userName, ticketInfo, date)){
					JOptionPane.showMessageDialog(null, "修改失败");
					return;
				}
				JOptionPane.showMessageDialog(null, "修改成功");
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setText("请输入订单用户");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setEditable(false);
		textField_2.setBounds(Global.block_size*2,Global.Frame_height/3+Global.block_size,
				3*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		TickNumArea = new JTextArea();
		TickNumArea.setBounds(5*Global.block_size,
				Global.Frame_height/3,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(TickNumArea);
		
		ticketUserArea = new JTextArea();
		ticketUserArea.setBounds(5*Global.block_size,
				Global.Frame_height/3+Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(ticketUserArea);
		
		textField_1 = new JTextField();
		textField_1.setText("请输入订单车票");
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(Global.block_size*2,Global.Frame_height/3+2*Global.block_size,
				3*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		ticketInfoArea = new JTextArea();
		ticketInfoArea.setBounds(5*Global.block_size,
				Global.Frame_height/3+2*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(ticketInfoArea);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setText("请输入创建时间");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setBounds(Global.block_size*2,Global.Frame_height/3+3*Global.block_size,
				3*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		makeDateArea = new JTextField();
		makeDateArea.setBounds(5*Global.block_size,
				Global.Frame_height/3+3*Global.block_size,Global.Frame_width/3, Global.block_size/2);
		frame.getContentPane().add(makeDateArea);
		makeDateArea.setColumns(10);
        
		frame.setBounds(RollBack.recent().getX(),RollBack.recent().getY(), Global.Frame_width,Global.Frame_height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    
	private boolean findTicketInfo(ArrayList<String> res,String ticketNum,
			String ticketUserName,String ticketInfo,String date){
		/*
		 * 查找对应车次号的车票信息,信息之间以以逗号隔开
		 */
		
		return true;
	}
	
	public boolean DeleUser(String num,String UserName,String ticketInfo,String date){
		/*
		 * 根据订单号删除订单信息
		 * 返回是否删除成功
		 */
		
		return true;
	}
	
	public boolean modifyTicketInfo(String num,String name,String ticket,String buildTime){
		/*   订单号,订单用户,订单车票,创建时间
		 * 修改车票信息，如果为null则不用修改，num!=null
		 * 返回是否修改成功
		 */
		
		return true;
	}
	
}
