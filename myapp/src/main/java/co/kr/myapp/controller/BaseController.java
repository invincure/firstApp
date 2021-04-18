package co.kr.myapp.controller;

public class BaseController {
	
	protected String pid = "";
	
	protected void init(String pid) {
		this.pid = pid;
	}
	
	protected String getPid() {
		return pid;
	}
}
