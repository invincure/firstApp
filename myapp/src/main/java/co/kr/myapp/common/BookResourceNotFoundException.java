package co.kr.myapp.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookResourceNotFoundException extends RuntimeException {
	public BookResourceNotFoundException(String bookId) {
		super("Book is not found(bookId = " + bookId);
	}

}
