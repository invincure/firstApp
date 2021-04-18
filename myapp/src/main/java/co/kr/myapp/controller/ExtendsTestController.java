package co.kr.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/extends")
public class ExtendsTestController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(ExtendsTestController.class);
	
	@RequestMapping("/setpid")
	public String setpid(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Req setpid");
		
		String reqPid = request.getParameter("pid");
		//pid = reqPid;
		init(reqPid);
		
		logger.info("req pid {}, setpid {}", reqPid, pid);
		
		//pid ="";
		//model.addAttribute("userMsg", userBean.getUserDetail());
		//model.addAttribute("userMsg", getUserBean().getUserDetail());
		model.addAttribute("userMsg", pid);
		
		return "/user/userinfo";
	}
	
	@RequestMapping("/getpid")
	public String getpid(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("current pid {}", pid);
		
		//pid ="";
		//model.addAttribute("userMsg", userBean.getUserDetail());
		model.addAttribute("userMsg", pid);
		return "/user/userinfo";
	}
	
	

}
