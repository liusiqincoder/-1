package com.dao.service;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import com.dao.controler.JFrameManager;
import com.dao.controler.Global;
import net.miginfocom.swing.MigLayout;

public class showInfoPane {

	private JFrame frame;
	private JTextField textField;
    private ArrayList<String> content=null;
    private int colNum;
    private JTextArea textArea;
    private String name;
	private JFrameManager manager=null;
	
	public showInfoPane(JFrameManager manager,ArrayList<String> con,String name,int colNum) {
		content=con;
		this.manager=manager;
		this.colNum=colNum;
		this.name=name;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		int width=frame.getWidth(),height=frame.getHeight();
		
		frame.getContentPane().setBackground(Global.background);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(Global.frameWidth/4,80,Global.frameWidth/2,Global.block_size/2);
		textField.setEditable(false);
		textField.setText(name);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(Global.background);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(Global.block_size, 160,Global.frameWidth-2*Global.block_size,500);
		frame.getContentPane().add(textArea);
		
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
		
		
		frame.setBounds(manager.RecentX(),manager.RecentY(),
				Global.frameWidth,Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showContent();
	}

	public void repaint(){
		showContent();
	}

	private String format(String str,int length){
		StringBuilder res=new StringBuilder(str);
		int len=str.length();
		while(len<length){
			res.append(" ");
			len++;
		}
		return res.toString();
	}
	
	private void showContent(){
		textArea.setText("");
		textArea.setEditable(false);
		for(int i=0;i<content.size();i++){
			if(i!=0&&i%colNum==0)
				textArea.append("\n");
			textArea.append(format(content.get(i),25));
		}
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
