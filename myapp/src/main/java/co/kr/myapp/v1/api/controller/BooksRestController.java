package co.kr.myapp.v1.api.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.util.JSONPObject;

import co.kr.myapp.common.BookResourceNotFoundException;
import co.kr.myapp.v1.api.dto.Book;
import co.kr.myapp.v1.api.resource.BookResource;
import co.kr.myapp.v1.api.service.BookService;

@RestController
@RequestMapping("books")
public class BooksRestController {

	@Autowired
	BookService bookService;

	//@RequestMapping(path="{bookId}", method=RequestMethod.GET)	// 도서ID를 받기위해 {bookId}를 path 속성 설정.
	@GetMapping(path="{bookId}")
//	@ResponseBody @RestController 지정시, Default로 @ResponseBody가 셋팅되므로 주석.
	public BookResource getBook(@PathVariable String bookId) {	//경로변수 {bookId}에서 도서ID를 가져온다.
		Book book = bookService.find(bookId);
		
		if(book == null) {
			System.out.println("@@ bookId is not exists");
			throw new BookResourceNotFoundException(bookId);
		}
		
		BookResource resource = new BookResource();
		resource.setBookId(book.getBookId());
		resource.setName(book.getName());
		resource.setPublishedDate(book.getPublishedDate());
		
		return resource;
	}
	
	//@Valid 데이터를 바인딩 할 때, 데이터의 유효성을 검증한다. 바인딩할 객체에 @NotEmpty 와같은 속성이 지정된경우 검증한다.
	//@Validated -> 기본적으로 @Valid와 동일하나, 유효성검증을 그루핑하여 관리가능(검증이 필요한 일부를 그루핑)
	//호출예시 
	//curl -D - -H "Content-Type: application/json" -X POST -d "{\"name\":\"hong11\", \"publishedDate\":\"2021-01-09\"}" http://localhost:8080/books
	//@RequestMapping(method= RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Void> createBook(
			@Validated @RequestBody BookResource newResource,
			UriComponentsBuilder uriBuilder){
		System.out.println("@@@@@ reqData : "+newResource.getName() + ", " + newResource.getPublishedDate());
		Book newBook = new Book();
		newBook.setName(newResource.getName());
		newBook.setPublishedDate(newResource.getPublishedDate());
		
		Book createBook = bookService.createBook(newBook);
		
		
		/*Location헤더를 설정하고, 리소스가 정상적으로 만들어졌다는 의미로 HTTP 상태코드인 201Created를 응답한다.
		응답헤더를 설정해야할 때는 ResponseEntity를 반환하면된다.
		ResponseEntity의 created 메서드를 사용하면 인수에 지정한 URI가 Location 헤더에 들어가고, '201 Created'가 HTTP상태 코드에 설정된다.
		만약 응답본문이 필요없을때는 BodyBuilder의 build 메서드를 호출해서 ReponseEntity객체를 생성한다.
		*/
		//String resouceUri = "http://localhost:8080/books/" + createBook.getBookId();
		//return ResponseEntity.created(URI.create(resouceUri)).build();
		
		URI resouceUri = uriBuilder.path("books/{bookId}")
				.buildAndExpand(createBook.getBookId())
				.encode()
				.toUri();
		return ResponseEntity.created(resouceUri).build();
		
			
	}
	
	//curl -D - -H "Content-Type: application/json" -X PUT -d "{\"name\":\"newName1\", \"publishedDate\":\"2021-01-10\"}" http://localhost:8080/books/1234567890
	
	//@RequestMapping(path="{bookId}", method=RequestMethod.PUT)
	@PutMapping(path="{bookId}")
	/**
	 	응답할 HTTP상태 코드를 지정한다.
	 	@ResponseStatus를 붙여주면, 임의의 HTTP상태코드를 응답할 수 있다.
	 	HttpStatus.NO_CONTENT 반환할 컨텐츠가 없다는 의미로, '204 no Content'를 응답.
	*/
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void put(@PathVariable String bookId, 
			@Validated @RequestBody BookResource bookRsc) {
		System.out.println("REQ bookId : " + bookId);
		Book book = new Book();
		book.setBookId(bookId);
		book.setName(bookRsc.getName());
		book.setPublishedDate(bookRsc.getPublishedDate());
		
		bookService.update(book);
	}
	
	//curl -D - -X DELETE http://localhost:8080/books/1234567890
	//@RequestMapping(path="{bookId}", method=RequestMethod.DELETE)
	@DeleteMapping(path="{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String bookId) {
		bookService.delete(bookId);
	}
	
	//조회 기능은 Lambda를 사용하지 않는 형태로 추후 고민해볼것!
	
	//curl -D - -H "Content-Type: application/json" -X GET -d "{\"bookId\":\"\"}" http://localhost:8080/books
	//curl -D - -H "Content-Type: application/json" -X GET -d "{\"bookId\":\"1234567890\"}" http://localhost:8080/books
	//@RequestMapping(method=RequestMethod.GET)
	@GetMapping
	//public JSONArray searchBooks(@Validated @RequestBody BookResource bookRsc) {
	//public JSONArray searchBooks(@RequestBody Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
	public JSONArray searchBooks(@RequestBody Map<String, String> params) {
	//public JSONArray searchBooks(@RequestParam("bookId") String bookId) {
		
		//String bookId = bookRsc.getBookId();
		System.out.println("reqMap " + params);
		String bookId = params.get("bookId");
		System.out.println("@@Req bookId : " + bookId);
		List<Book> findData = bookService.findAllByBookId(bookId);
		

		
		JSONArray jsonArr = new JSONArray();
		Iterator<Book> iter = findData.iterator();
		while(iter.hasNext()) {
			Book book = iter.next();
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("bookId", book.getBookId());
			jsonObj.put("name", book.getName());
			jsonObj.put("publishedDate", book.getPublishedDate());
			
			jsonArr.add(jsonObj);
		}
		
		return jsonArr;
	}
	
	
}
