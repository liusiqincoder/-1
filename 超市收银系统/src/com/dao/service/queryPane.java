package com.dao.service;

import com.dao.controler.Control;
import com.dao.controler.JFrameManager;
import com.dao.model.goods;
import com.dao.controler.Global;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class queryPane {

	private JFrame frame;
	private JTextField textField;
	private JTextField nameText;
	private JTextField TextField1;
	private JTextField productDateText;
	private JButton queryKindBtn;
	private JFrameManager manager=null;
	
	public queryPane(JFrameManager manager) {
		this.manager=manager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Global.background);
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
		textField.setText("商品名");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Global.background);
		textField.setBounds(Global.frameWidth/4, Global.frameHeight/3,
				2* Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		nameText = new JTextField();
		nameText.setBounds(Global.frameWidth/4+2* Global.block_size,
				Global.frameHeight/3, Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		TextField1 = new JTextField();
		TextField1.setText("生产日期");
		TextField1.setHorizontalAlignment(SwingConstants.CENTER);
		TextField1.setEditable(false);
		TextField1.setBackground(Global.background);
		TextField1.setBounds(Global.frameWidth/4, 
				Global.frameHeight/3+ Global.block_size,2* Global.block_size, Global.block_size/2);
		frame.getContentPane().add(TextField1);
		TextField1.setColumns(10);
		
		productDateText = new JTextField();
		productDateText.setBounds(Global.frameWidth/4+2* Global.block_size,
				Global.frameHeight/3+ Global.block_size, Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(productDateText);
		productDateText.setColumns(10);
		
		queryKindBtn = new JButton("查找");
		queryKindBtn.setBackground(Global.background);
		queryKindBtn.setBounds(Global.frameWidth/2,
				Global.frameHeight/3+2* Global.block_size, Global.block_size*3, Global.block_size/2);
		frame.getContentPane().add(queryKindBtn);
		queryKindBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Control.queryGoodsKind(manager,frame,nameText.getText());
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
