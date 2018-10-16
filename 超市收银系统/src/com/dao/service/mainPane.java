package com.dao.service;

import com.dao.controler.Control;
import com.dao.controler.JFrameManager;
import com.dao.model.admin;
import com.dao.model.cashier;
import com.dao.model.user;
import com.dao.controler.Global;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class mainPane implements Runnable{

	private JFrame frame;
	private JTextField txtXxxx;
    private int colNum,rowNum;
    private user User=null;
    private JFrameManager manager=null;
	/**
	 * Create the application.
	 */
	public mainPane() {
		initialize();
	}
	

	@Override
	public void run() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(Global.background);
		frame.setBounds(Global.StartX, Global.StartY, Global.frameWidth, Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		manager=new JFrameManager();
		manager.setRoot(frame);
		
		txtXxxx = new JTextField();
		txtXxxx.setBackground(Global.background);
		txtXxxx.setFont(new Font("宋体", Font.BOLD, 15));
		txtXxxx.setEditable(false);
		txtXxxx.setHorizontalAlignment(SwingConstants.CENTER);
		txtXxxx.setText("xxxx\u8D85\u5E02\u6536\u94F6\u7CFB\u7EDF");
		txtXxxx.setBounds(Global.frameWidth/4, 10, Global.frameWidth/2, Global.frameHeight/10);
		frame.getContentPane().add(txtXxxx);
		txtXxxx.setColumns(10);
		
		JButton loginBtn = new JButton("登陆");
		loginBtn.setBackground(Global.background);
		loginBtn.setBounds(Global.frameWidth/6,
				Global.frameHeight/6, Global.block_size*4, Global.block_size*4);
		frame.getContentPane().add(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Control.preLogin(frame,User,manager);
			}
		});
		
		JButton queryBtn = new JButton("\u67E5\u8BE2");
		queryBtn.setBackground(Global.background);
		queryBtn.setBounds(Global.frameWidth/6+ Global.block_size*4,
				Global.frameHeight/6, Global.block_size*7, Global.block_size*7);
		frame.getContentPane().add(queryBtn);
		queryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				queryPane qp=new queryPane(manager);
				manager.add(qp.getFrame());
				manager.Previous();
			}
		});
		
		JButton registerBtn = new JButton("\u6CE8\u518C");
		registerBtn.setBackground(Global.background);
		registerBtn.setBounds(Global.frameWidth/6,
				Global.frameHeight/6+ Global.block_size*4, Global.block_size*4, Global.block_size*3);
		frame.getContentPane().add(registerBtn);
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Control.preRegister(frame,User,manager);
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
		
	}

}
