package com.dao.service;

import com.dao.controler.Control;
import com.dao.controler.JFrameManager;
import com.dao.model.admin;
import com.dao.model.cashier;
import com.dao.model.user;
import com.dao.controler.Global;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class loginPane {

	private JFrame frame;
	private JTextField textField;
	private JTextField userIDText;
	private JTextField textField_2;
	private JTextField pwdText;
	private user User=null;
	private JFrameManager manager=null;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPane window = new loginPane(new user("",""));
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
	public loginPane(user User,JFrameManager manager) {
		this.User=User;
		this.manager=manager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Global.background);
		frame.setBounds(manager.RecentX(),manager.RecentY(), Global.frameWidth,Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		textField.setBackground(Global.background);
		textField.setText("\u7528\u6237\u540D");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(Global.frameWidth/4,Global.frameHeight/3,
				Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		userIDText = new JTextField();
		userIDText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/3,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(userIDText);
		userIDText.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Global.background);
		textField_2.setEditable(false);
		textField_2.setText("\u5BC6 \u7801");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(Global.frameWidth/4, 
				Global.frameHeight/3+Global.block_size,Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		pwdText = new JTextField();
		pwdText.setBounds(Global.frameWidth/4+2*Global.block_size, 
				Global.frameHeight/3+Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(pwdText);
		pwdText.setColumns(10);
		
		JButton loginBtn = new JButton("\u767B \u9646");
		loginBtn.setBackground(Global.background);
		loginBtn.setBounds(Global.frameWidth/2,
				Global.frameHeight/3+2*Global.block_size,Global.block_size*2,Global.block_size);
		frame.getContentPane().add(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			      String name=userIDText.getText();
			      String passWord=pwdText.getText();
				Control.login(User,name,passWord,manager,frame);
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
