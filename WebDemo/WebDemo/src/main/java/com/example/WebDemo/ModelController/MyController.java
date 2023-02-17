package com.example.WebDemo.ModelController;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;
import com.example.WebDemo.Model.Data;
import com.example.WebDemo.Model.Message;
import com.example.WebDemo.Model.PassEncTech1;
import com.example.WebDemo.Model.UserData;
import com.example.WebDemo.Repository.DataRepo;
import com.example.WebDemo.Repository.JpaRepo;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/web")
public class MyController {
//	@Autowired
//	private JpaRepo jp;
	@Autowired
	private DataRepo jp1;
	private PassEncTech1 ps =new PassEncTech1();
//	@PostMapping("/post/{folderName1}/{folderName2}/{folderName3}")
//	private ResponseEntity<String> postdata(@PathVariable String folderName1,@PathVariable String folderName2,@PathVariable String folderName3 ){
//		System.out.println("hello");
//		UserData ud=new UserData(folderName1,folderName2,folderName3);
//		jp.save(ud);
//		return ResponseEntity.ok("created");
//	}
	@PostMapping("/post1/{folderName1}/{folderName2}/{folderName3}")
	private ResponseEntity<String> postdata1(@PathVariable String folderName1,@PathVariable String folderName2,@PathVariable String folderName3 ){
		System.out.println(ps);
		String password=ps.hasing(folderName2);
		List<Data> ud=jp1.findAll();
		for (Data ud1:ud) {
			if(ud1.getUsername().equals(folderName1)) {
				System.out.println("duplicate");
				return ResponseEntity.ok("change user name");
			}
		}
		
		Data ud2=new Data(folderName1,password,folderName3,false);
		jp1.save(ud2);
		return ResponseEntity.ok("created");
	}
//	@GetMapping("/get/{number}/{password}")
//	private boolean fetch(@PathVariable String number,@PathVariable String password) {
//		List<UserData> ud=jp.findAll();
//		for (UserData ud1:ud) {
//			if (ud1.getNumber().equals(number) && ud1.getPassword().equals(password)) {
//				return true;
//			}
//		}
//		return false;
//	}
	@GetMapping("/put/{number}/{password}")
	private boolean putting(@PathVariable String number,@PathVariable String password) {
		String password1=ps.hasing(password);
		List<Data> ud=jp1.findAll();
		for (Data ud1:ud) {
			if (ud1.getNumber().equals(number) && ud1.getPassword().equals(password1)) {
				if (ud1.isLogin()) {
					return false;
				}
				ud1.setLogin(true);
				jp1.save(ud1);
				return true;
			}
		}
		return false;
	}
	@PutMapping("/put/{number}/{password}")
	private void putting1(@PathVariable String number,@PathVariable String password) {
		String password1=ps.hasing(password);
		List<Data> ud=jp1.findAll();
		for (Data ud1:ud) {
			if (ud1.getNumber().equals(number) && ud1.getPassword().equals(password1)) {
				ud1.setLogin(false);
				jp1.save(ud1);
				
			}
		}
	}
	@GetMapping("/gets1/{number}/{password}")
	private String fetchs1(@PathVariable String number,@PathVariable String password) {
		String password1=ps.hasing(password);
		List<Data> ud=jp1.findAll();
		
		for (Data ud1:ud) {
			if (ud1.getNumber().equals(number) && ud1.getPassword().equals(password1)) {
				return ud1.getUsername();
			}
		}
		return "";
	}
	@GetMapping("/online")
	private ArrayList<String> onlinedetails(){
		ArrayList<String> cars = new ArrayList<String>();
		List<Data> ud=jp1.findAll();
		for (Data ud1:ud) {
			if(ud1.isLogin()) {
				cars.add(ud1.getUsername());
			}
		}
		return cars;
	}
	//for futher purpose
	@GetMapping("/onlines")
	private ArrayList<Message> onlinedetail(){
		ArrayList<Message> cars = new ArrayList<Message>();
		List<Data> ud=jp1.findAll();
		for(Data ud1:ud) {
			String username = ud1.getUsername();
	        boolean isLogin = ud1.isLogin();
	        Message message = new Message(username, isLogin);
	        cars.add(message);
		}
		return cars;
	}
//	@GetMapping("/gets/{number}/{password}")
//	private String fetchs(@PathVariable String number,@PathVariable String password) {
//		List<UserData> ud=jp.findAll();
//		
//		for (UserData ud1:ud) {
//			if (ud1.getNumber().equals(number) && ud1.getPassword().equals(password)) {
//				return ud1.getUsername();
//			}
//		}
//		return "";
//	}
}
