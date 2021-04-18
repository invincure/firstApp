package co.kr.myapp.chap5.resource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class AccountCreateForm implements Serializable{
	//private static final long serialVersionUID =

	private CardForm card;
	
	//private List<String> roles;

//	public List<String> getRoles() {
//		return roles;
//	}
//	public void setRoles(List<String> roles) {
//		this.roles = roles;
//	}

	@NotNull
	@Size(min=1, max=50)
	private String name;

	@NotNull
	@Size(min=9, max=11)
	private String tel;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dateOfBirth;
	
	@NotNull
	@Size(min=9, max=256)
	private String email;
	
	public CardForm getCard() {
		return card;
	}
	public void setCard(CardForm card) {
		this.card = card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
