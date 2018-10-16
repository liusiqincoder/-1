package com.dao.model;

import com.dao.controler.Control;
import com.dao.controler.Global;
import com.dao.service.cashierPane;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

public class cashier extends user{

	private boolean working=false,idle=true;
	private user User=null;
	private cashierPane pane=null;

	public static ArrayList<cashier> idleCashiers=new ArrayList<>(),
	         workingCashier=new ArrayList<>();
   public cashier(){
	   super();
   }
	public cashier(String userID, String passWord) {
		super(userID, passWord);
		working=true;
		idle=true;
	}

	public boolean sale(user User){
//		idle=false;
		System.out.println("in sale");
		if(!Control.buyItem(this, User)){
			free();
			return false;
		}
		free();
		User.setBuyOk(true);
		return true;
	}

	public static boolean hasCashierWork(){
		return !idleCashiers.isEmpty();
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

	public boolean isIdle() {
		return idle;
	}

	public void setIdle(boolean idle) {
		this.idle = idle;
	}

	public user getUser() {
		return User;
	}

	public void setUser(user user) {
		User = user;
	}

	public static void addCashier(String name, String pwd) {
		cashier c=new cashier(name,pwd);
		idleCashiers.add(c);
	}

	public static void modify(String name,String pwd){
		cashier c=null;
		for(int i = 0; i<idleCashiers.size(); i++)
			if(idleCashiers.get(i).getUserID().equals(name)){
				c= idleCashiers.get(i);
				break;
			}
		if(c!=null) {
			idleCashiers.remove(c);
			c = new cashier(name, pwd);
			idleCashiers.add(c);
			return;
		}
		for(int i = 0; i<workingCashier.size(); i++)
			if(workingCashier.get(i).getUserID().equals(name)){
				c= workingCashier.get(i);
				break;
			}
		if(c!=null) {
			workingCashier.remove(c);
			c = new cashier(name, pwd);
			workingCashier.add(c);
		}
	}

	public static void dele(String name){
		cashier c=null;
		for(int i = 0; i<idleCashiers.size(); i++)
			if(idleCashiers.get(i).getUserID().equals(name)){
				c= idleCashiers.get(i);
				break;
			}
		for(int i = 0; i<workingCashier.size(); i++)
			if(workingCashier.get(i).getUserID().equals(name)){
				c= workingCashier.get(i);
				break;
			}
		if(c==null)
			return;
		if(!c.isIdle()){
			/*user User=c.getUser();
			users.add(User);*/
			workingCashier.remove(c);
		}else{
			idleCashiers.remove(c);
		}
	}
	public void work(){
		User.setOk(false);
		User.setBuyOk(false);
		idle = false;
		workingCashier.add(this);
	}
	public void free(){
		idle=true;
		if(User!=null) {
		    User.setOk(true);
			this.User = null;
		}
		idleCashiers.add(this);
	}

	public cashierPane getPane() {
		return pane;
	}

	public void setPane(cashierPane pane) {
		this.pane = pane;
	}
}
