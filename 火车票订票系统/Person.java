package 火车票订票系统;

import java.util.Date;

public class Person extends Thread{
	/*
	 * @param  isLogin �û��Ƿ��Ѿ���½
	 * 
	 */
	private boolean isLogin;
	
	private	String User_id,User_pw,Phone;

	public Person(String id,String pw){
		User_id=id;User_pw=pw;
		isLogin=false;
	}
	
	/*
	 *���貹��һЩ���� 
	 *
	 */
	
	
	@Override
	public String toString() {
		return "Person [isLogin=" + isLogin + ", User_id=" + User_id + ", User_pw=" + User_pw + ", Phone=" + Phone
				+ "]";
	}

	public void run(){
		
	}
	
	public void Login(){
		
	}
	
	public String getUser_id() {
		return User_id;
	}

	public void setUser_id(String user_id) {
		User_id = user_id;
	}

	public String getUser_pw() {
		return User_pw;
	}

	public void setUser_pw(String user_pw) {
		User_pw = user_pw;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

}
