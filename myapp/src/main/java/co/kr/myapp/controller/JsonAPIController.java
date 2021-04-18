package co.kr.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.myapp.dto.IDataMap;

@Controller
@RequestMapping(value="/json_format")
public class JsonAPIController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
//	@ResponseBody
//	public Map<String, String> initKeypad(@RequestBody Map<String, String> params,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//	
//	}
	
	@RequestMapping(value="/get_user")
	@ResponseBody
	public IDataMap getUser(@RequestBody IDataMap reqMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		logger.info("@@>>>called getUser");
		
		String name = reqMap.getString("Name");

		int age = reqMap.getInt("Age");
		
		logger.info(">>>name={}, age={}", name, age);
		
		IDataMap resMap = new IDataMap();
		resMap.put("name", name);
		resMap.put("age", age);
		
		
		return resMap;
		
	}
	

}
