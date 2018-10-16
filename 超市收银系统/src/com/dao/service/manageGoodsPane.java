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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class manageGoodsPane {

	private JFrame frame;
	private JTextField textField;
	private JTextField goodsNameText;
	private JTextField textField_1;
	private JTextField goodsIdText;
	private JTextField textField_2;
	private JTextField weightText;
	private JTextField textField_3;
	private JTextField priceText;
	private JTextField textField_4;
	private JTextField numText;
	private JFrameManager manager=null;
	private JTextField textField_5;
	private JTextField goodsProductDateText;
	
	public manageGoodsPane(JFrameManager manager) {
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
		
		textField = new JTextField();
		textField.setText("\u5546\u54C1\u540D");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(Global.frameWidth/4,Global.frameHeight/4,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		goodsNameText = new JTextField();
		goodsNameText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(goodsNameText);
		goodsNameText.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("\u5546\u54C1\u7F16\u53F7");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(Global.frameWidth/4,Global.frameHeight/4+Global.block_size,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		goodsIdText = new JTextField();
		goodsIdText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(goodsIdText);
		goodsIdText.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("\u91CD \u91CF");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(Global.frameWidth/4,Global.frameHeight/4+2*Global.block_size,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		weightText = new JTextField();
		weightText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+2*Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(weightText);
		weightText.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText("\u4EF7 \u683C");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setBounds(Global.frameWidth/4,Global.frameHeight/4+3*Global.block_size,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		priceText = new JTextField();
		priceText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+3*Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(priceText);
		priceText.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setText("\u6570 \u91CF");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(Global.frameWidth/4,Global.frameHeight/4+4*Global.block_size,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		numText = new JTextField();
		numText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+4*Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(numText);
		numText.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setEditable(false);
		textField_5.setText("\u751F\u4EA7\u65E5\u671F");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setBounds(Global.frameWidth/4,Global.frameHeight/4+5*Global.block_size,
				Global.block_size*2, Global.block_size/2);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		goodsProductDateText = new JTextField();
		goodsProductDateText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+5*Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(goodsProductDateText);
		goodsProductDateText.setColumns(10);
		
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
		
		JButton findBtn = new JButton("\u67E5 \u8BE2");
		findBtn.setBackground(Color.LIGHT_GRAY);
		findBtn.setBounds(Global.frameWidth/4,
				Global.frameHeight/4+6*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(findBtn);
		findBtn.addActionListener(new ActionListener() {
			/*
			 * ����Ʒid������Ʒ
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String id=goodsIdText.getText();
				Control.findGoodsById(manager,id);
			}
		});
		
		JButton addBtn = new JButton("\u6DFB \u52A0");
		addBtn.setBackground(Color.LIGHT_GRAY);
		addBtn.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+6*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				goods g=new goods(goodsNameText.getText(),goodsIdText.getText(),Double.valueOf(weightText.getText()),
						Double.valueOf(priceText.getText()),goodsProductDateText.getText(),Integer.valueOf(numText.getText()));
				Control.addGoods(frame,g);
			}
		});
		
		JButton deleBtn = new JButton("\u5220 \u9664");
		deleBtn.setBackground(Color.LIGHT_GRAY);
		deleBtn.setBounds(Global.frameWidth/4+4*Global.block_size,
				Global.frameHeight/4+6*Global.block_size,2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(deleBtn);
        deleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Control.deleGoods(frame,goodsIdText.getText());
			}
		});
		
		frame.setBounds(manager.RecentX(),manager.RecentY(), Global.frameWidth,Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	
}
