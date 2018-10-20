package 火车票订票系统;

import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JFrame;

public class RollBack {
     /*
      * 
      */
	private static Stack<JFrame> history=new Stack<>();
	
	private RollBack(){
		
	}
	
	public static void Back(){
		if(history.isEmpty())
			return;
		history.pop().setVisible(true);
	}
	
	public static void add(JFrame f){
		f.setVisible(false);
		history.add(f);
	}
	
	public static JFrame recent(){
		if(history.isEmpty())
			return null;
		return history.peek();
	}
	
}
