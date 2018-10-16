package com.dao.service;

import com.dao.controler.Control;
import com.dao.controler.JFrameManager;
import com.dao.model.goods;
import com.dao.model.user;
import com.dao.controler.Global;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class userPane {

	private JFrame frame;
	private user User=null;
	private JFrameManager manager=null;
	
	public userPane(user User, JFrameManager manager2) {
		this.User=User;
		manager=manager2;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
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
		
		
		JButton buyHistoryBtn = new JButton("购买记录");
		buyHistoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Control.showBuyHistory(manager,User,frame);
			}
		});
		buyHistoryBtn.setBackground(Color.LIGHT_GRAY);
		buyHistoryBtn.setBounds(Global.frameWidth/4,Global.frameHeight/3,
				   Global.frameWidth/4,Global.frameHeight/4);
		frame.getContentPane().add(buyHistoryBtn);
		
		JButton buyBtn = new JButton("购买");
		buyBtn.setBackground(Color.LIGHT_GRAY);
		buyBtn.setBounds(Global.frameWidth/2,Global.frameHeight/3,
				   Global.frameWidth/4,Global.frameHeight/4);
		frame.getContentPane().add(buyBtn);
		frame.setBounds(manager.RecentX(),manager.RecentY(), Global.frameWidth,Global.frameHeight);
		buyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				userBuyPane ubp=new userBuyPane(User,manager);
				manager.add(ubp.getFrame());
				manager.Previous();
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JFrameManager getManager() {
		return manager;
	}

	public void setManager(JFrameManager manager) {
		this.manager = manager;
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

	
}
