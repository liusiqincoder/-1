package com.dao.controler;

import com.dao.controler.Global;

import javax.swing.JFrame;

public class JFrameManager {
	/*
	 * ����ҳ��ķ���(RollBack) �� ǰ��(Previous)
	 * @param:  root ����ͷ�ڵ�  
	 *          recent ��ǰҳ��
	 * method:  setRoot ����ͷ�ڵ�
	 *          RollBack ����
	 *          Previous  ǰ��
	 *          add  �ڵ�ǰҳ��ѡ������һ��ҳ��
	 *          .....
	 */
	private JFrameNode root=new JFrameNode(null),
			recent=null;
	
	public void setRoot(JFrame f){
		root.setNode(f);
		recent=root;
	}
	
	public JFrame RollBack(){
		if(recent.Pre()==null)
			return null;
		//����ҳ��
		int x=recent.getNode().getX();
		int y=recent.getNode().getY();
		
		
		JFrame res=recent.Pre();
		recent.getNode().setVisible(false);
		if(res!=null)
			recent=recent.getPre();
		
		recent.getNode().setBounds(x,y, Global.frameWidth,Global.frameHeight);
		recent.getNode().setVisible(true);
		return res;
	}
	
	public JFrame Previous(){
		if(recent.Next()==null)
			return null;
		//����ҳ��
		int x=recent.getNode().getX();
		int y=recent.getNode().getY();
		
		JFrame res=recent.Next();
		recent.getNode().setVisible(false);
		if(res!=null)
			recent=recent.getNext();
		
		recent.getNode().setBounds(x,y,Global.frameWidth,Global.frameHeight);
		recent.getNode().setVisible(true);
		return res;
	}
	
	public void add(JFrame f){
		/*
		 * ѡ��ҳ��󣬽�recent��Ľڵ�ȥ�������Ѵ˺�
		 * ���нڵ��JFrame ȥ��
		 * �����Ӻ��½ڵ���recent
		 */
		if(recent.hasNext()){
			JFrameNode node=recent.getNext();
			while(node!=null){
				node.getNode().dispose();
				node=node.getNext();
			}
		}
		JFrameNode node=new JFrameNode(f);
		node.setPre(recent);
		recent.setNext(node);
	}
	
	public int RecentX(){
		return recent.getNode().getX();
	}
	
	public int RecentY(){
		return recent.getNode().getY();
	}
	
	public boolean hasNext(){
		return recent.hasNext();
	}
	
	public boolean hasPre(){
		return recent.hasPre();
	}
	
}
