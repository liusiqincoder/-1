package com.dao.model;

import com.dao.controler.Control;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class admin extends user {

	/*
	 * 锟疥长锟斤拷
	 */
	public admin(){
		super();
	}
	public admin(String userID, String passWord) {
		super(userID, passWord);
	}

	public static ArrayList<String> lookUserBuyHistory(){
		ArrayList<String> arr=new ArrayList<>();
		ConcurrentHashMap<String,ArrayList> map=null;
		map= FileOperating.read();
		arr.add("用户");
		arr.add("商品名");
		arr.add("商品ID");
		arr.add("商品价格/元");
		arr.add("商品数量");
		arr.add("用户ID");
		arr.add("购买日期");
		Set<String> users=map.keySet();
		for(String user:users){
			ArrayList<goods> g=map.get(user);
			for(goods gg:g){
				arr.add(user);
				arr.add(gg.getName());
				arr.add(gg.getID());
				arr.add(String.valueOf(gg.getPrice()));
				arr.add(String.valueOf(gg.getNum()));
				arr.add(gg.getC().getUserID());
				arr.add(gg.getBuyDate());
			}
		}
		return arr;
	}
}
