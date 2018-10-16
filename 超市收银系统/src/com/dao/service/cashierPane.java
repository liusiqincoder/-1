package com.dao.service;

import com.dao.controler.JFrameManager;
import com.dao.model.cashier;
import com.dao.controler.Global;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class cashierPane {

	private JFrame frame;
	private cashier c=null;
	private JFrameManager manager=null;
	private JTextField textField;
	private JTextField userIdText;
	private JTextField textField_1;
	private JTextArea goodsArea=null;
	
	public cashierPane(cashier c, JFrameManager manager) {
		this.c=c;
		c.setPane(this);
		this.manager=manager;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		frame.setBounds(manager.RecentX(),manager.RecentY(), Global.frameWidth, Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton backBtn = new JButton("<");
		backBtn.setBackground(Color.LIGHT_GRAY);
		backBtn.setBounds(10, 10, Global.block_size, Global.block_size/2);
		frame.getContentPane().add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manager.RollBack();
			}
		});
		
		JButton preBtn = new JButton(">");
		preBtn.setBackground(Global.background);
		preBtn.setBounds(Global.frameWidth-2* Global.block_size,
				10, Global.block_size, Global.block_size/2);
		frame.getContentPane().add(preBtn);
        preBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.Previous();
			}
		});
		
		textField = new JTextField();
		textField.setText("\u987E\u5BA2\u6635\u79F0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(Global.frameWidth/4, Global.frameHeight/4,
				2* Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		userIdText = new JTextField();
		userIdText.setHorizontalAlignment(SwingConstants.CENTER);
		userIdText.setEditable(false);
		userIdText.setBackground(Color.LIGHT_GRAY);
		userIdText.setBounds(Global.frameWidth/4+2* Global.block_size,
				Global.frameHeight/4, Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(userIdText);
		userIdText.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("\u5546\u54C1");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(Global.frameWidth/4, Global.frameHeight/4+ Global.block_size,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		goodsArea = new JTextArea();
		goodsArea.setBounds(Global.frameWidth/4+2* Global.block_size,
				Global.frameHeight/4+ Global.block_size, Global.frameWidth/3, 4* Global.block_size);
		frame.getContentPane().add(goodsArea);
		
		JButton modifyBtn = new JButton("\u4FEE\u6539");
		modifyBtn.setBackground(Color.LIGHT_GRAY);
		modifyBtn.setBounds(Global.frameWidth/2,
				Global.frameHeight/3+5* Global.block_size, Global.block_size*3, Global.block_size/2);
		frame.getContentPane().add(modifyBtn);
		modifyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(c.getUser()==null)
					return;
				String[] items=goodsArea.getText().split("\\\n");
				ArrayList<String> item=new ArrayList<>();
				for(int i=0;i<items.length;i++){
					String[] s=items[i].split(":");
					item.add(s[0]);
					item.add(s[1]);
				}
				c.getUser().setItems(item);
			}
		});
		
		JButton saleBtn = new JButton("\u7ED3 \u7B97");
		saleBtn.setBackground(Color.LIGHT_GRAY);
		saleBtn.setBounds(Global.frameWidth/3,
				Global.frameHeight/3+5* Global.block_size, Global.block_size*3, Global.block_size/2);
		frame.getContentPane().add(saleBtn);
		saleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(c.getUser()==null){
		    		System.out.println("null");
					c.free();
					userIdText.setText("暂时没有顾客");
					goodsArea.setText("");
					return;
				}
				if(!c.sale(c.getUser())||c.getUser()!=null&&!c.getUser().isBuyOk()){
					userIdText.setText("暂时没有顾客");
					goodsArea.setText("");
					return;
				}
				userIdText.setText("暂时没有顾客");
				goodsArea.setText("");
				JOptionPane.showMessageDialog(frame, "结算成功");
			}
		});
	}

	public void run(){
		userIdText.setText(c.getUser().getUserID());
		StringBuilder content=new StringBuilder();
		for(int i=0;i<c.getUser().getItems().size();i+=2)
			content.append(c.getUser().getItems().get(i)+":"+c.getUser().getItems().get(i+1)+"\n");
		goodsArea.setText(content.toString());
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public cashier getC() {
		return c;
	}

	public void setC(cashier c) {
		this.c = c;
	}

	public JFrameManager getManager() {
		return manager;
	}

	public void setManager(JFrameManager manager) {
		this.manager = manager;
	}

	public JTextField getUserIdText() {
		return userIdText;
	}

	public void setUserIdText(String con) {
		userIdText.setText(con);
	}

	public JTextArea getGoodsArea() {
		return goodsArea;
	}

	public void setGoodsArea(String con) {
		goodsArea.setText(con);
	}
	
}
