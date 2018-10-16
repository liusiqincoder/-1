package com.dao.service;

import com.dao.controler.Control;
import com.dao.controler.JFrameManager;
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
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class manageUserPane {

	private JFrame frame;
	private JFrameManager manager=null;
	private JTextField textField;
	private JTextField nameText;
	private JTextField textField_2;
	private JTextField pwdText;
	
	public manageUserPane(JFrameManager manager) {
		this.manager=manager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
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
		textField.setText("\u7528\u6237\u540D");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(Global.frameWidth/4,Global.frameHeight/4,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		nameText = new JTextField();
		nameText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("\u7528\u6237\u5BC6\u7801");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(Global.frameWidth/4,Global.frameHeight/4+Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		pwdText = new JTextField();
		pwdText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(pwdText);
		pwdText.setColumns(10);
		
		JButton addbtn = new JButton("\u6DFB \u52A0");
		addbtn.setBackground(Color.LIGHT_GRAY);
		addbtn.setBounds(Global.frameWidth/3,
				Global.frameHeight/4+2*Global.block_size,Global.block_size*2,Global.block_size/2);
		frame.getContentPane().add(addbtn);
         addbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=nameText.getText();
				String pwd=pwdText.getText();
				Control.addUser(frame,name, pwd);
			}
		});
         
		JButton modifyBtn = new JButton("\u4FEE \u6539");
		modifyBtn.setBackground(Color.LIGHT_GRAY);
		modifyBtn.setBounds(Global.frameWidth/3+2*Global.block_size,
				Global.frameHeight/4+2*Global.block_size,Global.block_size*2,Global.block_size/2);
		frame.getContentPane().add(modifyBtn);
        modifyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=nameText.getText();
				String pwd=pwdText.getText();
				Control.modifyUser(frame,name, pwd);
			}
		});
		
		JButton deleBtn = new JButton("\u5220 \u9664");
		deleBtn.setBackground(Color.LIGHT_GRAY);
		deleBtn.setBounds(Global.frameWidth/3+4*Global.block_size,
				Global.frameHeight/4+2*Global.block_size,Global.block_size*2,Global.block_size/2);
		frame.getContentPane().add(deleBtn);
        deleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=nameText.getText();
				if(Control.deleteUser(name))
				    JOptionPane.showMessageDialog(frame,"删除成功");
				else
					JOptionPane.showMessageDialog(frame,"删除失败");

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
