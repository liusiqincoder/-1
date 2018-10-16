package com.dao.service;

import com.dao.controler.Control;
import com.dao.controler.JFrameManager;
import com.dao.model.admin;
import com.dao.model.cashier;
import com.dao.model.user;
import com.dao.controler.Global;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class registerPane {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField userText;
	private JTextField passWordText;
	private JTextField textField_2;
	private JTextField retypeText;
    private user User=null;
	private JFrameManager manager=null;
	
	public registerPane(user User,JFrameManager manager) {
		this.User=User;
		this.manager=manager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(manager.RecentX(),manager.RecentY()
				, Global.frameWidth, Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Global.background);
		frame.getContentPane().setLayout(null);
		
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
		textField.setText("用户名");
		textField.setBackground(Global.background);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(Global.frameWidth/4,Global.frameHeight/3,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Global.background);
		textField_1.setEditable(false);
		textField_1.setText("密码");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(Global.frameWidth/4, 
				Global.frameHeight/3+Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		userText = new JTextField();
		userText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/3,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(userText);
		userText.setColumns(10);
		
		passWordText = new JTextField();
		passWordText.setBounds(Global.frameWidth/4+2*Global.block_size, 
				Global.frameHeight/3+Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(passWordText);
		passWordText.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Global.background);
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("确认密码");
		textField_2.setBounds(Global.frameWidth/4, 
		    Global.frameHeight/3+2*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		retypeText = new JTextField();
		retypeText.setBounds(Global.frameWidth/4+2*Global.block_size, 
				Global.frameHeight/3+2*Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(retypeText);
		retypeText.setColumns(10);
		
		JButton registerBtn = new JButton("注册");
		registerBtn.setBackground(Color.LIGHT_GRAY);
		registerBtn.setBounds(Global.frameWidth/2,
				Global.frameHeight/3+3*Global.block_size,Global.block_size*2,Global.block_size);
		frame.getContentPane().add(registerBtn);
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Control.register(frame,manager,userText.getText(),
						passWordText.getText(),retypeText.getText(),User);
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrameManager getManager() {
		return manager;
	}

	public void setManager(JFrameManager manager) {
		this.manager = manager;
	}
	
}
