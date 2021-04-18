package co.kr.myapp.chap5.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.kr.myapp.chap5.resource.AccountCreateForm;
import co.kr.myapp.chap5.resource.CardForm;

@Controller
@RequestMapping("/account")
public class AccountManageController {
	@RequestMapping("/createForm")
	public String createForm(Model model) {
		
		String dateStr=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		AccountCreateForm accountCreateForm = new AccountCreateForm();
		accountCreateForm.setName("Kwshin");
		accountCreateForm.setTel("01068468");
		accountCreateForm.setEmail("test@na.com");
		accountCreateForm.setDateOfBirth(new Date(dateStr));
		
		CardForm card = new CardForm();
		card.setExpire("2201");
		card.setNo("1234678905");
		accountCreateForm.setCard(card);
		
//		List<String> roleList = new ArrayList<String>();
//		roleList.add("user");
//		roleList.add("authority");
//		roleList.add("admin");
//		accountCreateForm.setRoles(roleList);
		
		model.addAttribute("accountCreateForm", accountCreateForm);
		return "account/createForm";
	}
	
	@RequestMapping("/createUser")
	public String createUser(@Validated AccountCreateForm account, BindingResult result, Model model) {
		System.out.println(account.getName());
		System.out.println(account.getTel());
		//model.addAttribute(new AccountCreateForm());
		
		if(result.hasErrors()) {
			System.out.println("validate Error!!");
			System.out.println(result.getObjectName());
		}
		
		return "account/createUser";
	}
	
	@RequestMapping("menu")
	public String menu(Model model, RedirectAttributes redirectAttibutes) {
		redirectAttibutes.addAttribute("name", "shin");	//redirect 에 파라미터로 전달할 값 설정. ex) http://localhost:8080/account/redirectMenu?name=shin
		return "redirect:/account/redirectMenu";
	}
	
	@RequestMapping("redirectMenu")
	public String redirectMenu(Model model, HttpServletRequest request) {
		String name = request.getParameter("name");
		System.out.println("req name : "+ name);
		model.addAttribute("NAME", name );
		return "account/redirectMenu";
	}
	
	@RequestMapping("auth")
	public String auth(Model model) {
		return "forward:/account/forwardMenu";
	}
	
	@RequestMapping("forwardMenu")
	public String forwardMenu(Model model, HttpServletRequest request) {
		String name = request.getParameter("name");
		System.out.println("req name : "+ name);
		
		String dateStr=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		AccountCreateForm accountCreateForm = new AccountCreateForm();
		accountCreateForm.setName("Kwshin");
		accountCreateForm.setTel("01068468");
		accountCreateForm.setEmail("test@na.com");
		accountCreateForm.setDateOfBirth(new Date(dateStr));
		model.addAttribute("account", accountCreateForm );
		return "account/forwardMenu";
	}
}
