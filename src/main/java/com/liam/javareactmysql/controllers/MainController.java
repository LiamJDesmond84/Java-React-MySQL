package com.liam.javareactmysql.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.liam.javareactmysql.models.LoginUser;
import com.liam.javareactmysql.models.User;
import com.liam.javareactmysql.services.PhotoService;
import com.liam.javareactmysql.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PhotoService photoServ;

	
	
	@GetMapping("/allPhotos")
	public List<Photo> getAll() {
		return photoServ.getAll();
	}
	
	@PutMapping("/createPhoto")
	public Photo create(@RequestBody Photo photo) {
		return photoServ.createOne(photo);
	}
	
	
	
	
	
	
	//////  NEW LOGIN/REG //////
	
//	   @GetMapping("/")
//	   public String loginPage(Model model) {
//	       model.addAttribute("newUser", new User());
//	       model.addAttribute("newLogin", new LoginUser());
//	       return "views/login.jsp";
//	   }
	   
	   // Create User Process
	   @PostMapping("/registerUser")
	   public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser, BindingResult result, Model model, HttpSession session) {
	   	userServ.register(newUser, result);
	       if(result.hasErrors()) {
	    	   throw new ResponseStatusException(
	    			   HttpStatus.NOT_FOUND, "entity not found"
	    			 );
	       }
	       session.setAttribute("user_id", newUser.getId());
	       return new ResponseEntity<>(HttpStatus.CREATED);
	   }
	   
//	   // Login User Process
//	   @PostMapping("/loginUser")
//	   public User loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
//	       User user = userServ.login(newLogin, result);
//	       if(result.hasErrors()) {
//	           model.addAttribute("newUser", new User());
//	           return "views/login.jsp";
//	       }
//	       session.setAttribute("user_id", user.getId());
//	       return "redirect:/dashboard";
//	   }
	   
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
		public String logout(HttpSession session) {
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
