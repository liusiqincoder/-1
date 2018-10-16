package com.dao.service;

import com.dao.controler.Control;
import com.dao.controler.JFrameManager;
import com.dao.model.user;
import com.dao.controler.Global;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class userBuyPane {
/*
 * buyArea ��д�������Ʒ���(��������)������  ��:�ֿ�
 *         ��ͬ��Ʒ��;�������з�
 */
	private JFrame frame;
	private JTextField textField;
	private user User=null;
	private JFrameManager manager=null;
	private JTextField textField_1;
	private JTextField payText;
	
	public userBuyPane(user User,JFrameManager manager) {
		this.manager=manager;
		this.User=User;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		frame.setBounds(manager.RecentX(),manager.RecentY(), Global.frameWidth,Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton backBtn = new JButton("<");
		backBtn.setBackground(Color.LIGHT_GRAY);
		backBtn.setBounds(10, 10, Global.block_size,Global.block_size/2);
		frame.getContentPane().add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manager.RollBack();
			}
		});
		
		JButton preBtn = new JButton(">");
		preBtn.setBackground(Global.background);
		preBtn.setBounds(Global.frameWidth-2*Global.block_size,
				10,Global.block_size,Global.block_size/2);
		frame.getContentPane().add(preBtn);
		 preBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					manager.Previous();
				}
			});
		
		textField = new JTextField();
		textField.setText("\u8BF7\u8F93\u5165\u6761\u5F62\u7801\uFF1A");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(Global.frameWidth/4,Global.frameHeight/3,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea buyItem = new JTextArea();
		buyItem.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/3,Global.frameWidth/3, Global.block_size);
		frame.getContentPane().add(buyItem);
		

		textField_1 = new JTextField();
		textField_1.setText("\u9700\u652F\u4ED8\u91D1\u989D\uFF1A");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setEditable(false);
		textField_1.setBounds(Global.frameWidth/4,Global.frameHeight/3+Global.block_size,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		payText = new JTextField();
		payText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/3+Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(payText);
		payText.setColumns(10);
		
		JButton payBtn = new JButton("\u652F \u4ED8");
		payBtn.setBackground(Color.LIGHT_GRAY);
		payBtn.setBounds(Global.frameWidth/3+2*Global.block_size,
				Global.frameHeight/3+3*Global.block_size,Global.block_size*2,Global.block_size);
		frame.getContentPane().add(payBtn);
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!User.isBuyOk()){
					JOptionPane.showMessageDialog(frame, "支付失败");
					payText.setText("0.0");
					return;
				}
				if(User==null)
					return;
				JOptionPane.showMessageDialog(frame, "支付成功");
			}
		});
		
		JButton buyBtn = new JButton("\u8D2D \u4E70");
		buyBtn.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(buyBtn);
		buyBtn.setBounds(Global.frameWidth/3,
				Global.frameHeight/3+3*Global.block_size,Global.block_size*2,Global.block_size);
		buyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!Control.userAndCashier.isAlive()) {
				    System.out.println("run");
					Control.userAndCashier.start();
				}
				int num=JOptionPane.showConfirmDialog(frame, "确定购买？");
				if(num==JOptionPane.CANCEL_OPTION||num==JOptionPane.NO_OPTION)
					return;
				String[] item=buyItem.getText().split(";|\\\n");
				Control.buy(User,item);
				
				new Thread(new Runnable(){

					@Override
					public void run() {
						while(!User.isOk()){
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						payText.setText(String.valueOf(User.getTotal()));
						if(User.isBuyOk())
							JOptionPane.showMessageDialog(frame, "购买成功");
						else
							JOptionPane.showMessageDialog(frame, "购买失败");

					}
					
				}).start();

			}
		});
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public user getUser() {
		return User;
	}

	public void setUser(user user) {
		User = user;
	}

	public JFrameManager getManager() {
		return manager;
	}

	public void setManager(JFrameManager manager) {
		this.manager = manager;
	}

	public JTextField getPayText() {
		return payText;
	}

	public void setPayText(JTextField payText) {
		this.payText = payText;
	}
	
}
