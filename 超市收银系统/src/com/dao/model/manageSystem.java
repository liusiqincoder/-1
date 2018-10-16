package com.dao.model;

import com.dao.controler.*;
import com.dao.controler.JFrameManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class manageSystem {

	private JFrame frame;
    private JFrameManager manager=null;
    private JTextField textField;
    private JTextField setColorText;
    private JTextField textField_1;
    private JTextField setStartsText;
    private JTextField textField_2;
    private JTextField setSizeText;
    
	public manageSystem(JFrameManager manager) {
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
		
		textField = new JTextField();
		textField.setText("\u8BBE\u7F6E\u80CC\u666F\u8272");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(Global.frameWidth/4,Global.frameHeight/4,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		setColorText = new JTextField();
		setColorText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(setColorText);
		setColorText.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setText("\u8BBE\u7F6E\u754C\u9762\u5927\u5C0F");
		textField_1.setBounds(Global.frameWidth/4,Global.frameHeight/4+Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		setStartsText = new JTextField();
		setStartsText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+2*Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(setStartsText);
		setStartsText.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setText("\u8BBE\u7F6E\u5F00\u59CB\u4F4D\u7F6E");
		textField_2.setBounds(Global.frameWidth/4,Global.frameHeight/4+2*Global.block_size,
				2*Global.block_size, Global.block_size/2);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		setSizeText = new JTextField();
		setSizeText.setBounds(Global.frameWidth/4+2*Global.block_size,
				Global.frameHeight/4+Global.block_size,Global.frameWidth/3, Global.block_size/2);
		frame.getContentPane().add(setSizeText);
		setSizeText.setColumns(10);

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
		
		JButton submit = new JButton("\u4FEE \u6539");
		submit.setBackground(Color.LIGHT_GRAY);
		submit.setBounds(Global.frameWidth/3+4*Global.block_size,
				Global.frameHeight/4+3*Global.block_size,Global.block_size*2,Global.block_size/2);
		frame.getContentPane().add(submit);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String color=setColorText.getText();
				if(color.equals("yellow"))
					Global.background=Color.yellow;
				else if(color.equals("black"))
					Global.background=Color.BLACK;
				else if(color.equals("blue"))
					Global.background=Color.BLUE;
				String[] s=setSizeText.getText().split(":");
				Global.frameWidth=Integer.valueOf(s[0]);
				Global.frameHeight=Integer.valueOf(s[1]);
				String[] str=setStartsText.getText().split(":");
				Global.StartX=Integer.valueOf(str[0]);
				Global.StartY=Integer.valueOf(str[1]);
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
