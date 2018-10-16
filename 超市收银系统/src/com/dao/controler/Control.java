package com.dao.controler;

import com.dao.model.*;
import com.dao.service.*;
import com.dao.controler.Global;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.*;

public class Control{
	/*
	 * userBuy[] һ���˿��Ŷӹ��������
	 *      userBuy[0] �ǹ˿�id
	 *      userBuy[i] ��Ʒid  userBuy[i+1] ��Ʒ����
	 * cashier �洢����Ա������
	 */
	public static Thread userAndCashier=new Thread(new Runnable(){

		@Override
		public void run() {
			while(true) {
//				System.out.println("runrunrunrurnurrrhueahuew");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (cashier.hasCashierWork()&&user.hasUser()) {
					System.out.print("in run......");
					cashier c=cashier.idleCashiers.remove(0);
					user User = user.users.poll();
					c.setUser(User);
					c.work();
					c.getPane().run();
				}
			}
		}

	});

	public Control(){

	}
	
	
	public static void main(String[] args) {
		
		mainPane aUser=new mainPane();
		Thread t1=new Thread(aUser);
		t1.start();
		
		mainPane aCashier=new mainPane();
		Thread t2=new Thread(aCashier);
		t2.start();
		
		mainPane aUser1=new mainPane();
		Thread t3=new Thread(aUser1);
		t3.start();
		
		mainPane aCashier1=new mainPane();
		Thread t4=new Thread(aCashier1);
		t4.start();
		
	}
	/*
	public*//* synchronized *//*static boolean buy(user User,ArrayList<String> items){
		System.out.println("in buy");
		*//*
		 * items[0] �˿�id
		 * item[i] ��Ʒid  item[i+1] ��Ʒ���� ��iΪ������
		 *//*
	    user.users.add(User);
	    return true;
	}*/
/*

	public synchronized static void consume(){
		if(user.users.isEmpty())
			return;
//		System.out.println("consume");
		cashier c=null;
		for(cashier cc:cashier.cashiers){
			if(cc.isWorking()&&cc.isIdle()){
				c=cc;
				break;
			}
		}
		if(c==null)
			return;
		user User=user.users.poll();
//		System.out.println(User.getUserID());
		c.setUser(User);
		User.setC(c);
	}
*/

	public static void login(user User,String name,String passWord,JFrameManager manager,JFrame frame){
		try{
			if(!user.exisitUser(User,name, passWord)){
				JOptionPane.showMessageDialog(frame, "账户或密码错误，请重新输入");
				return;
			}
		}catch(FileNotFoundException fne){
			fne.printStackTrace();
			return;
		}catch(IOException ioe){
			ioe.printStackTrace();
			return;
		}
		User.setLogin(true);
		User.setUserID(name);
		User.setPassWord(passWord);
		JOptionPane.showMessageDialog(frame, "登陆成功");
		manager.add(frame);
		if(User.getClass()==user.class){
			System.out.println("users:"+user.users.size());
			userPane up=new userPane(User,manager);
			up.setManager(manager);
			manager.add(up.getFrame());
			manager.Previous();
		}else if(User.getClass()==admin.class){
			adminPane ap=new adminPane((admin) User,manager);
			ap.setManager(manager);
			manager.add(ap.getFrame());
			manager.Previous();
		}else{
			cashier.idleCashiers.add((cashier) User);
			System.out.println("cashier:"+cashier.idleCashiers.size());
			cashierPane cp=new cashierPane((cashier) User,manager);
			cp.setManager(manager);
			manager.add(cp.getFrame());
			manager.Previous();
		}
	}

	public static void preLogin(JFrame frame,user User,JFrameManager manager) {
		Object[] obj2 ={ "顾客", "店长", "收银员" };
		String s = (String) JOptionPane.showInputDialog(frame,"请选择你的登陆身份:\n", "登陆",
				JOptionPane.PLAIN_MESSAGE, null, obj2, obj2[0]);
		if("顾客".equals(s))
			User=new user("","");
		else if("收银员".equals(s))
			User=new cashier("", "");
		else if("店长".equals(s))
			User=new admin("","");
		else
			return;
		loginPane jp=new loginPane(User,manager);
		jp.setManager(manager);
		manager.add(jp.getFrame());
		manager.Previous();
	}

	public static void preRegister(JFrame frame, user User,JFrameManager manager) {
		Object[] obj2 ={ "顾客", "店长", "收银员" };
		String s = (String) JOptionPane.showInputDialog(frame,"请选择你的注册身份:\n", "注册", JOptionPane.PLAIN_MESSAGE, null, obj2, obj2[0]);
		if("顾客".equals(s))
			User=new user("","");
		else if("收银员".equals(s))
			User=new cashier("", "");
		else if("店长".equals(s))
			User=new admin("","");
		else
			return;
		registerPane rp=new registerPane(User,manager);
		manager.add(rp.getFrame());
		manager.Previous();
	}

	public static void setContent(JTextField profiText,JTextField saleNumText) {
		double[] sale=goods.getTodaySale();
		int num=(int)sale[0];
		double money=sale[1];
		profiText.setText(String.valueOf(money));
		saleNumText.setText(String.valueOf(num));
	}

	public static boolean buyItem(cashier c,user User){
		/*
		 * �û�������Ʒ
		 * items[i] ��Ʒid   items[i+1] ��Ʒ����
		 * �㷨  ��ȡgoodsDataFile��ArrayList<goods>
		 *    ����items�����Ʒ
		 *    д��buyFile
		 */
		//�˿͹�����
		System.out.println("in buyItem");
		double total=0;
//        ConcurrentHashMap<String,ArrayList> maps=null;
		String UserId=User.getUserID();
		ArrayList<String> items=User.getItems();
		//商品种类
		ArrayList<goods> gk=null;
		
		Date day=new Date();
		SimpleDateFormat df=new SimpleDateFormat("YYYY-MM-dd");

		gk=FileOperating.readKind();

		ArrayList<goods> goodsItem=new ArrayList<>();

		int count=0;//购买成功商品
		for(int i=0;i<items.size();i+=2){
			String goodsId=items.get(i);
			String goodsNum=items.get(i+1);
			goods g=null;
			for(goods gg:gk){
				if(gg.getID().equals(goodsId)){
					g=gg;
					break;
				}
			}
			if(g!=null&&g.getNum()>=Integer.valueOf(goodsNum)){
//				System.out.println(g.getPrice()+":"+g.getNum());
				total+=g.getPrice()*((double)Integer.valueOf(goodsNum));
				goods gg=new goods(g.getName(),goodsId,g.getWeight(),
						g.getPrice(),g.getProduceDate(),Integer.valueOf(goodsNum));
				gg.setC(c);
				gg.setBuyDate(df.format(day));
				goodsItem.add(gg);
				g.setNum(g.getNum()-Integer.valueOf(goodsNum));
				count++;
			}
		}

		JOptionPane.showMessageDialog(null,"购买成功的商品数："+count+",其他商品不存在或数量不够");

		FileOperating.writeKind(gk);

		FileOperating.write(User.getUserID(),goodsItem);
		User.setTotal(total);
		return true;
	}

	public static void addCashier(JFrame frame,String name, String pwd) {
		Properties dataFile=new Properties();
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(Global.cashierDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"添加失败");
			return;
		}
		try {
			dataFile.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"添加失败");
			return;
		}
		if(dataFile.get(name)!=null){
			JOptionPane.showMessageDialog(frame,"该收银员已存在");
			return;
		}

		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"添加失败");
			return;
		}

		dataFile.setProperty(name, pwd);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(Global.cashierDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"添加失败");
			return;
		}
		try {
			dataFile.store(fos, null);
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(frame,"添加失败");
			return;
		}finally{
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frame,"添加失败");
				return;
			}
		}
		cashier.addCashier(name,pwd);
		JOptionPane.showMessageDialog(frame,"添加成功");
	}

	public static void findGoodsById(JFrameManager manager,String id) {
		ArrayList<String> res=goods.findById(id);
		showInfoPane sp=new showInfoPane(manager, res, "商品"+id+"信息如下", 6);
		manager.add(sp.getFrame());
		manager.Previous();
	}

	public static void addGoods(JFrame frame,goods g) {
		if(goods.add(g))
    		JOptionPane.showMessageDialog(frame,"添加成功");
		else
			JOptionPane.showMessageDialog(frame,"添加失败");
	}

	public static void deleGoods(JFrame frame,String id) {
		if(goods.dele(id))
	    	JOptionPane.showMessageDialog(frame, "删除成功");
		else
			JOptionPane.showMessageDialog(frame, "删除失败");
	}

	public static void addUser(JFrame frame, String name, String pwd) {
		Properties dataFile=new Properties();
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(Global.userDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		try {
			dataFile.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		if(dataFile.get(name)!=null){
			JOptionPane.showMessageDialog(frame, "该用户已存在");
			return;
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		dataFile.setProperty(name, pwd);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(Global.userDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
		JOptionPane.showMessageDialog(frame, "添加成功");

		user.add(name,pwd);
	}

	public static boolean deleteUser(String name){
		Properties dataFile=new Properties();
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(Global.userDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		try {
			dataFile.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		if(dataFile.get(name)==null){
			System.out.println("不存在该用户");
			return false;
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		dataFile.remove(name);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(Global.userDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		try {
			dataFile.store(fos, null);
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		user.deleUser(name);
		return true;
	}

	public static void modifyUser(JFrame frame, String name, String pwd) {
		Properties dataFile=new Properties();
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(Global.userDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "修改失败");
		}
		try {
			dataFile.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "修改失败");
		}
		if(dataFile.get(name)==null){
			JOptionPane.showMessageDialog(frame, "该用户不存在");
			return;
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "修改失败");
		}

		dataFile.setProperty(name, pwd);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(Global.userDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			dataFile.store(fos, null);
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(frame, "修改失败");
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		JOptionPane.showMessageDialog(frame, "修改成功");
		user.modify(name,pwd);
	}

	public static void queryGoodsKind(JFrameManager manager,JFrame frame, String name) {
		ArrayList<String> arr=goods.queryKind(frame,name);

		showInfoPane sp=new showInfoPane(manager,arr, "商品信息",5);
		sp.setManager(manager);
		manager.add(sp.getFrame());
		manager.Previous();
	}

	private static ArrayList<goods> findGoodsByName(String name) {
		return goods.findGoodsByName(name);
	}

	public static void register(JFrame frame, JFrameManager manager,String name,String prePwd,String pwd,user User) {
		user.register(frame,name,pwd,prePwd,User);
		JOptionPane.showMessageDialog(frame, "注册成功");
		manager.RollBack();
	}

	public static void showBuyHistory(JFrameManager manager, user User, JFrame frame) {
		ArrayList<String> arr=user.getBuyInfo(User);
		showInfoPane sp=new showInfoPane(manager,arr, User.getUserID()+"购买记录如下",7);
		manager.add(sp.getFrame());
		manager.Previous();
	}

	public static void deleCashier(JFrame frame, String name) {
		Properties dataFile=new Properties();
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(Global.cashierDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"删除失败");
			return;
		}
		try {
			dataFile.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"删除失败");
			return;
		}
		if(dataFile.get(name)==null){
			JOptionPane.showMessageDialog(frame, "不存在该员工");
			JOptionPane.showMessageDialog(frame,"删除失败");
			return;
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"删除失败");
			return;
		}

		dataFile.remove(name);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(Global.cashierDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"删除失败");
			return;
		}
		try {
			dataFile.store(fos, null);
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(frame,"删除失败");
			return;
		}finally{
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frame,"删除失败");
				return;
			}
		}


		JOptionPane.showMessageDialog(frame,"删除成功");
		cashier.dele(name);
	}

	public static void modifyCashier(JFrame frame, String name, String pwd) {
		Properties dataFile=new Properties();
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(Global.cashierDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"修改失败");
			return;
		}
		try {
			dataFile.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"修改失败");
			return;
		}
		if(dataFile.get(name)==null){
			JOptionPane.showMessageDialog(frame, "该员工不存在");
			JOptionPane.showMessageDialog(frame,"修改失败");
			return;
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,"修改失败");
			return;
		}

		dataFile.setProperty(name, pwd);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(Global.cashierDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			dataFile.store(fos, null);
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(frame,"修改失败");
			return;
		}finally{
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frame,"修改失败");
				return;
			}
		}

		JOptionPane.showMessageDialog(frame,"修改成功");
	}

	public static void buy(user User,String[] items) {
		ConcurrentHashMap<String,ArrayList> maps=null;
		ArrayList<String> userBuy=new ArrayList<>();
		for(int i=0;i<items.length;i++){
			String[] s=items[i].split(":");
			userBuy.add(s[0]);
			userBuy.add(s[1]);
		}
		User.setItems(userBuy);
		user.buy(User);
	}
}
