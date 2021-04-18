package co.kr.myapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.kr.myapp.bean.UserInfo;
import co.kr.myapp.controller.HomeController;

@Service
public class SampleService {
	private static final Logger logger = LoggerFactory.getLogger(SampleService.class);
	
	public UserInfo findOne(String arg) throws Exception{
		logger.info("findOne called arg{}", arg);
		
		
		UserInfo userInfo = new UserInfo();
		userInfo.setAge(arg);
		
		if(arg.equals("22")) {
			throw new Exception("22 are too young");
		}
		
		return userInfo;
	}
	
	public UserInfo update(String arg) {
		logger.info("update called@@");
		UserInfo userInfo = new UserInfo(); 
		userInfo.setName("Hong");
		return userInfo;
	}
	
}
