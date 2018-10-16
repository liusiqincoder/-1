package com.dao.controler;

import javax.swing.JFrame;

public class JFrameNode {
	/*
	 * ˫������ڵ�  �洢JFrame
	 * 
	 */
	private JFrame node=null;
	private JFrameNode next=null,pre=null;
	
	public JFrameNode(JFrame f){
		node=f;
	}
	
	public JFrame Next(){
		return next==null?null:next.getNode();
	}

	public JFrame Pre(){
		return pre==null?null:pre.getNode();
	}
	
	public boolean hasNext(){
		return next!=null;
	}
	
	public boolean hasPre(){
		return pre!=null;
	}
	
	public JFrame getNode() {
		return node;
	}

	public void setNode(JFrame node) {
		this.node = node;
	}

	public JFrameNode getNext() {
		return next;
	}

	public void setNext(JFrameNode next) {
		this.next = next;
	}

	public JFrameNode getPre() {
		return pre;
	}

	public void setPre(JFrameNode pre) {
		this.pre = pre;
	}
	
}
