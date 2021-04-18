package co.kr.myapp.v1.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import co.kr.myapp.v1.api.dto.Book;

@Service
public class BookService {

	private final Map<String, Book> bookRepository = new ConcurrentHashMap<>();
	
	@PostConstruct	//의존성 주입 후 초기화를 수행하는 메서다.
	public void loadDummyData() {
		Book book = new Book();
		book.setBookId("1234567890");
		book.setName("@work with slack!");
		book.setPublishedDate(LocalDate.of(2021, 1, 4));
		bookRepository.put(book.getBookId(), book);
	}
	
	public Book find(String bookId) {
		Book book = bookRepository.get(bookId);
		return book;
	}
	
	public Book createBook(Book book) {
		String bookId = UUID.randomUUID().toString();
		System.out.println("createBookID : " + bookId);
		book.setBookId(bookId);
		bookRepository.put(bookId, book);
		
		return book;
	}
	
	public Book update(Book book) {
		return bookRepository.put(book.getBookId(), book);
	}
	
	public Book delete(String bookId) {
		return bookRepository.remove(bookId);
	}
	
	public List<Book> findAllByBookId(String bookId){
		
		ArrayList<Book> findList = new ArrayList<Book>();
		
		//bookRepository.values().stream().filter(book ->)
		Collection<Book> bookList = bookRepository.values();
		
		Iterator iter = bookList.iterator();
		while(iter.hasNext()) {
			String thisBookId = ((Book)iter.next()).getBookId();
			if(StringUtils.isNotBlank(bookId) ) {
				if(bookRepository.get(thisBookId).getBookId().equals(bookId)) {
					findList.add(bookRepository.get(thisBookId));
				}
			}else {
				findList.add(bookRepository.get(thisBookId));
			}
			
		}
		
		return findList;
	}
	
}
