package com.dao.model;

import com.dao.controler.Control;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class goods implements Serializable{
    private String name,ID,produceDate;
    private double weight,price;
    private int num=0;
    private cashier c=null;
    private String buyDate=null;
    /*
     * @param: name ��Ʒ��
     *         ID ��Ʒ���
     *         weight ����
     *         price �۸�
     *         produceDate ��������
     *         num ��Ʒ����
     */
	public goods(){

	}
    public goods(String name,String ID,double weight,double price,String produceDate,int num){
    	this.name=name;this.ID=ID;
    	this.weight=weight;this.price=price;
    	this.produceDate=produceDate;
    	this.num=num;
    }
    
    public String[] getDecription(){
    	String[] res=new String[5];
    	res[0]=name;
    	res[1]=ID;
    	res[2]=String.valueOf(weight);
    	res[3]=String.valueOf(price);
    	res[4]=String.valueOf(num);
    	return res;
    }

	public static double[] getTodaySale(){
		double[] res=new double[2];
		ConcurrentHashMap<String, ArrayList> maps=null;
		Date day=new Date();
		SimpleDateFormat df=new SimpleDateFormat("YYYY-MM-DD");
		String today=df.format(day);
		maps= FileOperating.read();
		double money=0;
		int num=0;
		Set<String> users=maps.keySet();
		for(String user:users){
			ArrayList<goods> gs=maps.get(user);
			for(goods g:gs){
				String[] days=g.getBuyDate().split(" ");
				if(today.equals(days[0])){
					num+=Integer.valueOf(g.getNum());
					money+=Double.valueOf(g.getPrice()*((double)(g.getNum())));
				}
			}
		}
		res[0]=num;res[1]=money;
		return res;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public cashier getC() {
		return c;
	}

	public void setC(cashier c) {
		this.c = c;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public static ArrayList<goods> findGoodsByName(String name) {
		ArrayList<goods> res=new ArrayList<>();
		ArrayList<goods> gs= null;
		gs = FileOperating.readKind();
		for(goods g:gs){
			if(g.getName().equals(name))
				res.add(g);
		}
		return res;
	}

	public static ArrayList<String> queryKind(JFrame frame,String name) {

		ArrayList<String> arr=new ArrayList<>();
		arr.add("商品名");arr.add("ID");arr.add("重量/kg");
		arr.add("价格/元");arr.add("数量");

		if(name.length()==0){
			JOptionPane.showMessageDialog(frame, "商品名不能为空");
			return arr;
		}

		ArrayList<goods> res=null;
		res=findGoodsByName(name);

		if(res==null||res.size()==0){
			JOptionPane.showMessageDialog(frame, "查无该商品");
			return arr;
		}

		for(int i=0;i<res.size();i++){
			String[] str=res.get(i).getDecription();
			arr.add(str[0]);arr.add(str[1]);arr.add(str[2]);
			arr.add(str[3]);arr.add(str[4]);
		}
		return arr;
	}

	public static boolean add(goods g) {
		ArrayList<goods> gs=null;

		gs = FileOperating.readKind();

		gs.add(g);

		FileOperating.writeKind(gs);
		return true;
	}

	public static boolean dele(String id) {
		ArrayList<goods> gs=null;

		gs = FileOperating.readKind();
		goods g=null;
		for(goods gg:gs){
			if(id.equals(gg.getID())){
				g=gg;
				break;
			}
		}
		if(g==null){
			System.out.println("该商品不存在");
			return false;
		}
		gs.remove(g);
		FileOperating.writeKind(gs);
		return true;
	}

	public static ArrayList<String> findById(String id) {
		ArrayList<String> res=new ArrayList<>();
		res.add("商品名");
		res.add("ID");
		res.add("重量");
		res.add("价格");
		res.add("数量");
		res.add("生产日期");
		ArrayList<goods> gs=null;

		gs= FileOperating.readKind();

		for(goods gg:gs){
			if(!gg.getID().equals(id))
				continue;
			res.add(gg.getName());
			res.add(gg.getID());
			res.add(String.valueOf(gg.getWeight()));
			res.add(String.valueOf(gg.getPrice()));
			res.add(String.valueOf(gg.getNum()));
			res.add(gg.getProduceDate());
		}
		return res;
	}
}
