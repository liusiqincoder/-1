package com.dao.controler;

import java.awt.Color;
import java.io.*;

public class Global {
	/*
	 * @param:
	 * frameWidth frameHeight  �������
	 * (StartX,StartY)   �����ʼλ��
	 * block_size  �����һ����С��λ
	 * background  ���汳����ɫ
	 * userDataFile �˿������ļ�  (��ֵ�ԣ��û���=����)
	 * cashierDataFile ����Ա�����ļ� (��ֵ�ԣ��û���=����)
	 * adminDataFile ����Ա�����ļ� (��ֵ�ԣ��û���=����)
	 * goodsDataFile ��Ʒ�ļ�������洢ArrayList<goods>
	 * buyFile �û������¼  ��һ��ConcurrentHashMap��ɣ��û�id��ӦArrayList<goods>
	 * 
	 */
	public static int frameWidth=800;
	public static int frameHeight=600;
	public static int StartX=200;
	public static int StartY=200;
	public static int block_size=50;
	public static Color background=Color.LIGHT_GRAY;
	public static String userDataFile="D://dataFile//userPassWord.properties",
			cashierDataFile="D://dataFile//cashierPassWord.properties",
			adminDataFile="D://dataFile//adminPassWord.properties",
			goodsDataFile="D://dataFile//goodsDataFile.txt",
			goodsKindDataFile="D://dataFile//goodsKindDataFile.txt",
			buyFile="D://dataFile//buyHistory.txt";
	static{
		File dataFile=new File("D://dataFile");
		if(!dataFile.exists()){
			try {
				dataFile.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		File f1=new File(userDataFile);
		File f2=new File(cashierDataFile);
		File f3=new File(adminDataFile);
		File f4=new File(goodsDataFile);
		File f5=new File(goodsKindDataFile);
		File f6=new File(buyFile);
		if(!f1.exists()){
			try {
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		if(!f2.exists()){
			try {
				f2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		if(!f3.exists()){
			try {
				f3.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		if(!f4.exists()){
			try {
				f4.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		if(!f5.exists()){
			try {
				f5.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		if(!f6.exists()){
			try {
				f6.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

	}
}
