package co.kr.myapp.chap5.resource;

import java.io.Serializable;

public class CardForm implements Serializable{

	String no ; 
	String expire;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	
	
}
