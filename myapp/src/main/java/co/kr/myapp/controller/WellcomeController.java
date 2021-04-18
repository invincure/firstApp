package co.kr.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WellcomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(WellcomeController.class);
	
	@RequestMapping(value="/welcome")
	public String welcome() {
		logger.info("welcome call");
		return "index";
	}

}
