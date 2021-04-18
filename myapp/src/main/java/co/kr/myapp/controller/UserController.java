package co.kr.myapp.controller;

import java.util.Enumeration;
import java.util.Locale;

import javax.annotation.Generated;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kr.myapp.bean.UserBean;
import co.kr.myapp.bean.UserInfo;
import co.kr.myapp.service.SampleService;
import co.kr.myapp.sometest.Lightweight;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	//@Resource
	UserBean userBean;
	
	@Autowired
	SampleService sampleService;
	
	@Autowired
	MessageSource messageSource;

	
	@RequestMapping(value="/user_info")
	public String getUser(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		Enumeration enmer = request.getParameterNames();
		
		while(enmer.hasMoreElements()) {
			String key = (String)enmer.nextElement();
			logger.info("REQ {}={}" ,key, request.getParameter(key));
		}
		
		String name = request.getParameter("Name");
		String age = request.getParameter("Age");
		
		userBean.setName(name);
		userBean.setAge(age);
		
		//logger.info("RESP userMsg{}" , userBean.getName());
		//logger.info("RESP userMsg{}" , userBean.getAge());
		try {
			sampleService.findOne(age);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(">>UserController 에러 발생!!" + e.getMessage());
		}
		
		
		model.addAttribute("userMsg", userBean.getUserInfo());
		model.addAttribute("age", age);
		
		logger.info("RESP @userMsg [{}]" , userBean.getUserInfo());
		
		return "/user/userinfo";
	}
	
	@RequestMapping(value="/user_detail")
	public String getUserDetail(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Req getUserDetail");
		
		sampleService.update("update");
		//model.addAttribute("userMsg", userBean.getUserDetail());
		model.addAttribute("userMsg", getUserBean().getUserDetail());
		return "/user/userinfo";
	}
	
	/**
	 * Bean scope 관련.
	 * 'prototype'의 생명주기를 갖는 인스턴스를 Lookup 하여 주입하면,
	 * 'singleton' 인스턴스에서 주입받더라도, 매번 새로운 instance를 생성 할 수 있다.
	 * 참고) prototype은 thread-safe 한 bean에 설정하고.
	 *      DI에서 객체주입시, 매번 새로운 instance를 생성하여 반환 한다.
	 */
	@Lookup
	UserBean getUserBean() {
		return null;
	}
	
	@RequestMapping(value="/binding")
	public String binding(Model model, HttpServletRequest request, HttpServletResponse response) {
		UserInfo userInfo = new UserInfo();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(userInfo);
		binder.bind(request);
		
		logger.info("AGE : {}",userInfo.getAge());
		logger.info("NAME : {}",userInfo.getName());
		
		model.addAttribute("userMsg", "age="+userInfo.getAge()+", name="+userInfo.getName());
		
		//logger.info("RESP @userMsg [{}]" , userBean.getUserInfo());
		
		return "/user/userinfo";
	}
	
	@RequestMapping(value="/prop")
	public String propertiesTest(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		
		//testProp("");
		logger.info("getDataSrc : {}", userBean.getDataSrc());
		logger.info("getMyappConfigDataSrc : {}", userBean.getMyappConfigDataSrc());
//		logger.info("NAME : {}",userInfo.getName());
		
		model.addAttribute("userMsg",  userBean.getDataSrc());
		
		logger.info("RESP @userMsg [{}]" , userBean.getDataSrc());
		
		return "/user/userinfo";
	}
	
	@RequestMapping(value="message")
	public String message(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		//messageSource.getMessage("", new MessageSourceResolvable[] {})
		
		String messageKor = messageSource.getMessage("welcome.message", new String[] {"shin"}, Locale.KOREAN);
		String messageEng = messageSource.getMessage("welcome.message", new String[] {"shin"}, Locale.ENGLISH);
		
		model.addAttribute("userMsg", "Kor:"+messageKor+"Eng:"+messageEng);
		
		logger.info("RESP @userMsg [{}]" , "Kor:"+messageKor+"Eng:"+messageEng);
		return "user/userinfo";
	}
	
	
	
	
}
