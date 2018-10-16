package com.dao.service;

		import java.awt.Color;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;
		import java.io.IOException;
		import java.util.ArrayList;
		import java.util.Set;
		import java.util.concurrent.ConcurrentHashMap;

		import javax.swing.JButton;
		import javax.swing.JFrame;
		import javax.swing.JOptionPane;

		import com.dao.controler.Control;
		import com.dao.controler.JFrameManager;
		import com.dao.model.admin;
		import com.dao.model.goods;
		import com.dao.controler.Global;

public class adminPane {

	private JFrame frame;
	private admin ad=null;
	private JFrameManager manager=null;

	public adminPane(admin ad, JFrameManager manager) {
		this.ad=ad;
		this.manager=manager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(manager.RecentX(),manager.RecentY(), Global.frameWidth, Global.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton backBtn = new JButton("<");
		backBtn.setBackground(Color.LIGHT_GRAY);
		backBtn.setBounds(10, 10, Global.block_size, Global.block_size/2);
		frame.getContentPane().add(backBtn);
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				manager.RollBack();
			}
		});

		JButton preBtn = new JButton(">");
		preBtn.setBackground(Global.background);
		preBtn.setBounds(Global.frameWidth-2* Global.block_size,
				10, Global.block_size, Global.block_size/2);
		frame.getContentPane().add(preBtn);
		preBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.Previous();
			}
		});

		JButton saleHistoryBtn = new JButton("\u9500\u552E\u8BB0\u5F55");
		saleHistoryBtn.setBackground(Color.LIGHT_GRAY);
		saleHistoryBtn.setBounds(Global.frameWidth/4, Global.frameHeight/4,
				Global.frameWidth/4, Global.block_size);
		frame.getContentPane().add(saleHistoryBtn);
		saleHistoryBtn.addActionListener(new ActionListener() {
			/*
			 * 锟介看锟斤拷锟阶硷拷录
			 * 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> arr=admin.lookUserBuyHistory();
				showInfoPane sp=new showInfoPane(manager,arr, "用户购买记录",7);
				manager.add(sp.getFrame());
				manager.Previous();
			}
		});

		JButton managerGoodsBtn = new JButton("\u5546\u54C1\u7BA1\u7406");
		managerGoodsBtn.setBackground(Color.LIGHT_GRAY);
		managerGoodsBtn.setBounds(Global.frameWidth/2, Global.frameHeight/4,
				Global.frameWidth/4, Global.block_size);
		frame.getContentPane().add(managerGoodsBtn);
		managerGoodsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manageGoodsPane mgp=new manageGoodsPane(manager);
				manager.add(mgp.getFrame());
				manager.Previous();
			}
		});

		JButton manageCashierBtn = new JButton("\u6536\u94F6\u5458\u7BA1\u7406");
		manageCashierBtn.setBackground(Color.LIGHT_GRAY);
		manageCashierBtn.setBounds(Global.frameWidth/4, Global.frameHeight/4+ Global.block_size,
				Global.frameWidth/4, Global.block_size);
		frame.getContentPane().add(manageCashierBtn);
		manageCashierBtn.addActionListener(new ActionListener() {
			/*
			 * 锟斤拷锟斤拷员锟斤拷锟斤拷
			 * 只要锟斤拷Control锟斤拷锟絚ashier锟斤拷锟斤拷锟斤拷锟斤拷
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				manageCashierPane mcb=new manageCashierPane(manager);
				manager.add(mcb.getFrame());
				manager.Previous();
			}
		});

		JButton manageUserBtn = new JButton("\u987E\u5BA2\u7BA1\u7406");
		manageUserBtn.setBackground(Color.LIGHT_GRAY);
		manageUserBtn.setBounds(Global.frameWidth/2, Global.frameHeight/4+ Global.block_size,
				Global.frameWidth/4, Global.block_size);
		frame.getContentPane().add(manageUserBtn);
		manageUserBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manageUserPane mup=new manageUserPane(manager);
				manager.add(mup.getFrame());
				manager.Previous();
			}
		});

		JButton anaBtn = new JButton("\u7EDF\u8BA1\u5206\u6790");
		anaBtn.setBackground(Color.LIGHT_GRAY);
		anaBtn.setBounds(Global.frameWidth/4, Global.frameHeight/4+2* Global.block_size,
				Global.frameWidth/4, Global.block_size);
		frame.getContentPane().add(anaBtn);
		anaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				anaPane ap=new anaPane(manager);
				manager.add(ap.getFrame());
				manager.Previous();
			}
		});
		
		/*JButton manageSystemBtn = new JButton("\u7CFB\u7EDF\u7BA1\u7406");
		manageSystemBtn.setBackground(Color.LIGHT_GRAY);
		manageSystemBtn.setBounds(Global.frameWidth/4,Global.frameHeight/4+2*Global.block_size,
				Global.frameWidth/2, Global.block_size);
		frame.getContentPane().add(manageSystemBtn);
		manageSystemBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manageSystem ms=new manageSystem(manager);
				manager.add(ms.getFrame());
				manager.Previous();
			}
		});*/

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public admin getAd() {
		return ad;
	}

	public void setAd(admin ad) {
		this.ad = ad;
	}

	public JFrameManager getManager() {
		return manager;
	}

	public void setManager(JFrameManager manager) {
		this.manager = manager;
	}
}
