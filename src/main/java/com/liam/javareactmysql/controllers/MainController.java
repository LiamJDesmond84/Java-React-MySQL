package com.liam.javareactmysql.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.liam.javareactmysql.models.LoginUser;
import com.liam.javareactmysql.models.User;

import com.liam.javareactmysql.services.PhotoService;
import com.liam.javareactmysql.services.UserService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PhotoService photoServ;
	
	@Autowired
	private HttpSession session;
	


	Long sesh;


	
	
	@GetMapping("/allPhotos")
	public List<Photo> getAll() {
		return photoServ.getAll();
	}
	
	
	@GetMapping("/getPhoto/{id}")
	public Photo getOne(@PathVariable("id") Long id) {
		return photoServ.getOne(id);
	}
	
	@GetMapping("/deletePhoto/{id}")
	public void deleteOne(@PathVariable("id") Long id) {
		photoServ.deleteOne(id);
	}
	
//	@PutMapping("/updatePhoto/{id}")
	@PutMapping("/updatePhoto/{id}")
	public Photo updateOne(@RequestBody Photo photo) {
//		Photo photo =  photoServ.getOne(id);
		return photoServ.updateOne(photo);
	}
	
	
	@PostMapping("/createPhoto")
	public ResponseEntity<Photo> create(@Valid @RequestBody Photo photo, BindingResult result) {
//		Long test = (Long) session.getAttribute("user_id");
//	   	System.out.println("create");
//		System.out.println(session.getAttribute("user_id"));
//		if (session.getAttribute("user_id") == null) {
//			System.out.println("User NOT in Session");
//		}
//		
//		// Prints "User NOT in Session"
//		Long userId = (Long) session.getAttribute("user_id");
//		System.out.println(userId);
		photo.setOwner(userServ.getUser(sesh));
	    photoServ.createOne(photo);
	    
		return new ResponseEntity<Photo>(photo, HttpStatus.OK);

}
		
	

	
	
	
//	 ____ ___                    
//	|    |   \______ ___________ 
//	|    |   /  ___// __ \_  __ \
//	|    |  /\___ \\  ___/|  | \/
//	|______//____  >\___  >__|   
//	             \/     \/       

	
	
	//////  NEW LOGIN/REG //////
	
	
	   // Create User Process
	   @PostMapping("/registerUser")
	   public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser, BindingResult result) {

	   	userServ.createUser(newUser, result);
	   	if(result.hasErrors()) {
	           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	       }
	   	
	   	session.setAttribute("user_id", newUser.getId());

	    return new ResponseEntity<User>(newUser, HttpStatus.OK);
	   }
	

	   

	   
	   // Login User Process
	   @PostMapping("/loginUser")
	   public ResponseEntity<User> loginUser(@Valid @RequestBody LoginUser newLogin, BindingResult result) {
	    User user = userServ.login(newLogin, result);
	    System.out.println(user.getId());
	    // prints out 3

	   	session.setAttribute("user_id", user.getId());
	   	sesh = (Long) session.getAttribute("user_id");
	   	System.out.println("login");
		System.out.println(session.getAttribute("user_id"));
//	   	session.setAttribute("friday", "yippee its friday!!");
//		if (session.getAttribute("friday") == null) {
//			System.out.println("User NOT in Session");
//		}
		
		// User still in session


	    return new ResponseEntity<User>(user, HttpStatus.OK);
	   }
	   
//	   // Show One User
//	   @GetMapping("/user/show/{id}")
//	   public String showUser(@PathVariable("id") Long id, Model model, HttpSession session) {
//			if (session.getAttribute("user_id") == null) {
//				return "redirect:/";
//			}
//			
//			Long userId = (Long) session.getAttribute("user_id");
//			model.addAttribute("userLog", userServ.getUser(userId));
//	   	model.addAttribute("user", userServ.getUser(id));
//	       return "views/showUser.jsp";
//	   }
	   
	   // Logout User
		@GetMapping("/logout")
		public String logout() {
			session.invalidate();
			return "redirect:/";
		}
		
		// Delete User
		@GetMapping("/delete/user/{id}")
		public String deleteSUser(@PathVariable("id") Long id) {
			userServ.deleteOne(id);
			return "redirect:/dashboard";
		}
	

}
