package co.kr.myapp.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserBean {
	
	private String name;
	private String age;
	private UserInfo userInfo= new UserInfo();
	
	//bean이 초기화 되기 전 처리
	@PostConstruct
	void populateCache() {
		System.out.println("@전처리 populateCache!!");
	}
	
	@PreDestroy
	void clearCache() {
		System.out.println("@후처리 clearCache!!");
	}
	
	@Value("${datasource.url}") 
	private String dataSrcUrl;
	public String getDataSrc() {
		return dataSrcUrl;
	}
	
	@Value("#{myappConfig['datasource.url']}")
	private String myappConfigDataSrc;
	public String getMyappConfigDataSrc() {
		return myappConfigDataSrc;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getUserInfo() {
		String msg = "Name is "+ this.name + ". And "+ this.age +"years old.";
		
		//userInfo = new UserInfo();
		userInfo.setAge(this.age);
		return msg;
	}
	
	public String getUserDetail() {
		//userInfo = new UserInfo();
		return "age is " + userInfo.getAge() + ".";
	}
	
	
	
	
//	private UserInfo userInfo = new UserInfo();
//	
//	public void setName(String name) {
//		userInfo.setName(name);
//	}
//	public void setAge(String age) {
//		userInfo.setAge(age);
//	}
//	
//	public String getUserInfo() {
//		String msg = "Name is "+ userInfo.getName() + ". And "+ userInfo.getAge() +"years old.";
//		return msg;
//	}
	

}
