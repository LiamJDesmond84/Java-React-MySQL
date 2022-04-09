package com.liam.javareactmysql.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.liam.javareactmysql.VOs.ResponseTemplateVO;
import com.liam.javareactmysql.models.LoginUser;
import com.liam.javareactmysql.models.Photo;
import com.liam.javareactmysql.models.User;
import com.liam.javareactmysql.services.PhotoService;
import com.liam.javareactmysql.services.UserService;

//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@CrossOrigin(origins = "http://localhost:3000")
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


	
	// Get All
	@GetMapping("/allPhotos")
	public List<Photo> getAll() {
		return photoServ.getAll();
	}
	
	// Get One with creator details(for details page)
	@GetMapping("/getPhoto/{id}")
	public ResponseTemplateVO  getUsersPhotos(@PathVariable("id") Long id) {
		return photoServ.getUserPhotos(id);
	}
	
	
	// Get One(for update page)
	@GetMapping("/getPhotoDetails/{id}")
	public Photo getOne(@PathVariable("id") Long id) {
		return photoServ.getOne(id);
	}
	

	
	// Create One
	@PostMapping("/createPhoto")
	public ResponseEntity<Photo> create(@Valid @RequestBody Photo photo) {
		
		
		// Session resetting after each API request
		// Had to make a workaround instead
		
		photo.setOwner(userServ.getUser(sesh));
		Photo newPhoto = photoServ.createOne(photo);
	    
	    
		return new ResponseEntity<Photo>(newPhoto, HttpStatus.OK);

}
	
	// Update One
	@PutMapping("/updatePhoto/{id}")
	public Photo updateOne(@RequestBody Photo photo) {

		photo.setOwner(userServ.getUser(sesh));
		return photoServ.updateOne(photo);
	}
	
	// Delete One
	@DeleteMapping("/deletePhoto/{id}")
	public void deleteOne(@PathVariable("id") Long id) {
		System.out.println("made it this far");
		photoServ.deleteOne(id);
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
	   public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser) {
		   
			ResponseEntity<User> email = userServ.simpleEmailCheck(newUser);
			
			if(email != null) {
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
	
			User user = userServ.simpleCreateUser(newUser);
	
		   	session.setAttribute("user_id", newUser.getId());
		   	sesh = (Long) session.getAttribute("user_id");
	
		    return new ResponseEntity<User>(user, HttpStatus.OK);
	   }
	
	   // Login User Process
	   @PostMapping("/loginUser")
	   public ResponseEntity<String> loginUser(@Valid @RequestBody LoginUser newLogin, BindingResult result) {
		    User user = userServ.login(newLogin, result);
		    
		   	if(result.hasErrors()) {
		           return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Email or Password");
		    }
	
		   	session.setAttribute("user_id", user.getId());
		   	sesh = (Long) session.getAttribute("user_id");
		   	System.out.println("login");
		    System.out.println(user.getId());
		   	System.out.println(sesh);
	
		    return ResponseEntity.status(HttpStatus.OK).body("Okay!");
	   }

	   
	   // Old Way //
	   
//	   // Login User Process
//	   @PostMapping("/loginUser")
//	   public ResponseEntity<User> loginUser(@Valid @RequestBody LoginUser newLogin, BindingResult result) {
//	    User user = userServ.login(newLogin, result);
//	   	if(result.hasErrors()) {
//	           return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
//	       }
//	    System.out.println(user.getId());
//	    // prints out 3
//
//	   	session.setAttribute("user_id", user.getId());
//	   	sesh = (Long) session.getAttribute("user_id");
//	   	System.out.println("login");
//	   	System.out.println(sesh);
////		System.out.println(session.getAttribute("user_id"));
//
//		// User still in session
//
//
//	    return new ResponseEntity<User>(user, HttpStatus.OK);
//	   }
	   
	   // Verify sesh
	   @GetMapping("/userVerif")
	   public ResponseEntity<String> showUser() {
			if (sesh == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Logged in");
			}

			return ResponseEntity.status(HttpStatus.OK).body("Okay!");
	   }
	   
	   // Verify if sesh == owner
	   @GetMapping("/checkOwner/{id}")
	   public ResponseEntity<String> checkOwner(@PathVariable("id") Long id) {
			if(photoServ.getOne(id).getOwner().getId() != sesh) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not allowed to edit");
			}
			return ResponseEntity.status(HttpStatus.OK).body("Okay!");
	   }
	   

	   
	   // Logout User
		@GetMapping("/logout")
		public String logout() {
			
			session.invalidate();
			sesh = null;
		   	System.out.println("logout");
			System.out.println(sesh);
			return "redirect:/";
		}
		
		// Delete User
		@GetMapping("/delete/user/{id}")
		public String deleteSUser(@PathVariable("id") Long id) {
			userServ.deleteOne(id);


			
			System.out.println(sesh);
			return "redirect:/dashboard";
		}
	

}
