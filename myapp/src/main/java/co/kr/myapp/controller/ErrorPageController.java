package co.kr.myapp.controller;

import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorPageController {
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Enumeration en = request.getAttributeNames();
		while(en.hasMoreElements()) {
			String key = (String)en.nextElement();
			Object value = (Object)request.getAttribute(key);
			//System.out.println("Att["+key + "="+value+"]");
		}
		
		Enumeration enHeader =request.getHeaderNames();
		while(enHeader.hasMoreElements()) {
			String key = (String)enHeader.nextElement();
			String value = request.getHeader(key);
			System.out.println("HeaderAtt["+key + "="+value+"]");
		}
		
		String contentType = request.getHeader("Content-Type");
		System.out.println("Content-Type=" +request.getHeader("Content-Type"));
		
		if(contentType != null && (contentType.indexOf("application/json") > -1)) {
			return "forward:/error/err_api";
		}else {
			return "redirect:/error/err_page";
		}
		
	}
	
	@RequestMapping("/error/err_page")
	public String handleErrorPage(HttpServletRequest request) {
		System.out.println("@@ handleErrorPage");
		return "/error/err_page";
	}
	
	@RequestMapping("/error/err_api")
	@ResponseBody
	public String handleErrorAPI(HttpServletRequest request) {
		System.out.println("@!! handleErrorAPI");
		//Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
		Integer statusCode = (Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		JSONObject json = new JSONObject();
		json.put("code", statusCode);
		json.put("message", "Unauthorized Access");
		
		System.out.println("statusCode="+statusCode);
		System.out.println("handleErrorAPI RESP > " + json.toJSONString());
		
		return json.toString();
	}

}
