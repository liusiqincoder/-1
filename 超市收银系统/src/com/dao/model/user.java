package com.dao.model;

import com.dao.controler.Control;
import com.dao.controler.Global;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class user implements Serializable{
    private boolean isLogin=false,buyOk,ok;
    private String userID,passWord;
    private ArrayList<String> items=null;
    private double total=0;
    private cashier c=null;
	public static Queue<user> users=new LinkedList<>();

	public user() {

	}

	public user(String userID, String passWord){
    	this.userID=userID;
    	this.passWord=passWord;
    }

    public static void buy(user User){
//    	Control.users.add(this);
		users.add(User);
    }

	public static boolean hasUser(){
		return !users.isEmpty();
	}

	public static boolean exisitUser(user User, String name, String pwd) throws FileNotFoundException, IOException {
		/*
		 * ��userDataFile��ȡ�û���������
		 */
		Properties dataFile=new Properties();
		FileInputStream fis=null;
		if(User.getClass()==admin.class)
			fis = new FileInputStream(Global.adminDataFile);
		else if(User.getClass()==cashier.class)
			fis = new FileInputStream(Global.cashierDataFile);
		else
			fis = new FileInputStream(Global.userDataFile);
		dataFile.load(fis);
		if(pwd.equals(dataFile.get(name))){
			fis.close();
			return true;
		}
		fis.close();
		return false;
	}


	public static boolean isNameValid(user User,String name){
		for(int i=0;i<name.length();i++)
			if(name.charAt(i)==' ')
				return false;
		FileInputStream fis=null;
		try {
			if(User.getClass()==admin.class)
				fis = new FileInputStream(Global.adminDataFile);
			else if(User.getClass()==cashier.class)
				fis = new FileInputStream(Global.cashierDataFile);
			else
				fis = new FileInputStream(Global.userDataFile);
		} catch (FileNotFoundException e) {
			return false;
		}
		Properties dataFile=new Properties();

		try {
			dataFile.load(fis);
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}finally{
			try {
				fis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
		}

		try {
			fis.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		if(dataFile.containsKey(name))
			return false;
		return true;
	}

	public static boolean isPwdValid(String pwd){
		for(int i=0;i<pwd.length();i++)
			if(pwd.charAt(i)==' ')
				return false;
		return true;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isBuyOk() {
		return buyOk;
	}

	public void setBuyOk(boolean buyOk) {
		this.buyOk = buyOk;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(ArrayList<String> items) {
		this.items = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
		if(ok)
			items.clear();
	}

	public cashier getC() {
		return c;
	}

	public void setC(cashier c) {
		this.c = c;
	}

	public static ArrayList<String> getBuyInfo(user User) {
		ConcurrentHashMap<String,ArrayList> map=null;
		ArrayList<String> arr=new ArrayList<>();
		arr.add("商品名");
		arr.add("ID");
		arr.add("生产日期");
		arr.add("价格/元");
		arr.add("数量");
		arr.add("购买用户");
		arr.add("购买日期");
		map= FileOperating.read();

		ArrayList<goods> goodsArr=map.get(User.getUserID())==null?new ArrayList<goods>():map.get(User.getUserID());
		for(goods gg:goodsArr){
			arr.add(gg.getName());
			arr.add(gg.getID());
			arr.add(gg.getProduceDate());
			arr.add(String.valueOf(gg.getPrice()));
			arr.add(String.valueOf(gg.getNum()));
			arr.add(gg.getC().getUserID());
			arr.add(gg.getBuyDate());
		}
		return arr;
	}

	public static void register(JFrame frame,String name, String pwd,String prePwd ,user User) {
		if(!pwd.equals(prePwd)){
			JOptionPane.showMessageDialog(frame, "两次输入密码不一致");
			return;
		}
		if(!user.isPwdValid(pwd)){
			JOptionPane.showMessageDialog(frame, "密码不合法");
			return;
		}
		if(!user.isNameValid(User,name)){
			JOptionPane.showMessageDialog(frame, "姓名不合法");
			return;
		}

		Properties dataFile=new Properties();
		FileOutputStream fos = null;

		try {
			if(User.getClass()==admin.class)
				fos = new FileOutputStream(Global.adminDataFile,true);
			else if(User.getClass()==cashier.class)
				fos = new FileOutputStream(Global.cashierDataFile,true);
			else
				fos = new FileOutputStream(Global.userDataFile,true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}

		dataFile.setProperty(name, pwd);

		try {
			dataFile.store(fos, null);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}finally{
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				return;
			}
		}

		try {
			fos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		User.setLogin(true);
		User.setPassWord(pwd);
		User.setUserID(name);
	}

	public static boolean deleUser(String name) {
		user User=null;
		Stack<user> u=new Stack<>();
		while(!users.isEmpty()&&!users.peek().getUserID().equals(name)){
			u.add(users.poll());
		}
		if(!users.isEmpty())
			User=users.poll();
		while(!u.isEmpty())
			users.add(u.pop());
		if(User==null||User.getC()==null)
			return false;
		User.getC().setIdle(true);
		return true;
	}

	public static void modify(String name, String pwd) {
		user User=null;
		Stack<user> u=new Stack<>();
		while(!users.isEmpty()&&!users.peek().getUserID().equals(name)){
			u.add(users.poll());
		}
		if(!users.isEmpty())
			User=users.poll();
		while(!u.isEmpty())
			users.add(u.pop());
		User=new user(name,pwd);
		users.add(User);
	}

	public static void add(String name, String pwd) {
		user User=new user(name,pwd);
		users.add(User);
	}
}
