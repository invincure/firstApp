package co.kr.myapp.form;

import java.io.Serializable;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class EchoForm implements Serializable {
	
	@NotEmpty
	@Size(max =100)
	private String text;
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}

