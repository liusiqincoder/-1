package com.dao.service;

import com.dao.controler.Control;
import com.dao.controler.JFrameManager;
import com.dao.model.goods;
import com.dao.controler.Global;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class anaPane {

	private JFrame frame;
	private JFrameManager manager=null;
	private JTextField textField;
	private JTextField profiText;
	private JTextField textField_1;
	private JTextField saleNumText;
	
	public anaPane(JFrameManager manager) {
		this.manager=manager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		frame.setBounds(manager.RecentX(),manager.RecentY(), Global.frameWidth, Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setText("\u4ECA\u65E5\u6536\u76CA");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(Global.frameWidth/4, Global.frameHeight/3,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		profiText = new JTextField();
		profiText.setEditable(false);
		profiText.setBackground(Color.LIGHT_GRAY);
		profiText.setBounds(Global.frameWidth/4+2* Global.block_size,
				Global.frameHeight/3, Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(profiText);
		profiText.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("\u552E\u51FA\u5546\u54C1\u6570\u91CF");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(Global.frameWidth/4, Global.frameHeight/3+ Global.block_size,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		saleNumText = new JTextField();
		saleNumText.setEditable(false);
		saleNumText.setBackground(Color.LIGHT_GRAY);
		saleNumText.setBounds(Global.frameWidth/4+2* Global.block_size,
				Global.frameHeight/3+ Global.block_size, Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(saleNumText);
		saleNumText.setColumns(10);

		Control.setContent(profiText,saleNumText);
		
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
        
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
