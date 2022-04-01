package com.liam.javareactmysql.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.liam.javareactmysql.models.LoginUser;
import com.liam.javareactmysql.models.User;
import com.liam.javareactmysql.services.FileService;
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
	
//	@Autowired
//	private FileService fileServ;
//	
//	private String IMAGE_FOLDER="src/main/client/src/imgs/";
	
   // Create User Process
   @PostMapping("/registerUser")
   public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser, BindingResult result, HttpSession session) {

   	userServ.createUser(newUser, result);
   	session.setAttribute("user_id", newUser.getId());

    return new ResponseEntity<User>(newUser, HttpStatus.OK);
   }

	
	
	@GetMapping("/allPhotos")
	public List<Photo> getAll() {
		return photoServ.getAll();
	}
	
	@PostMapping("/createPhoto")
	public ResponseEntity<Photo> create(@Valid @RequestBody Photo photo, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		System.out.println(userId);
		photo.setOwner(userServ.getUser(userId));
	    photoServ.createOne(photo);
	    
		return new ResponseEntity<Photo>(photo, HttpStatus.OK);

}
		
	
//	@PostMapping("/createPhoto")
//	public Photo create(@Valid @ModelAttribute Photo photo, BindingResult result, @RequestParam("file") MultipartFile file) {
//		if(result.hasErrors()) {
//			throw new ResponseStatusException(
//	    			   HttpStatus.NOT_FOUND, "entity not found"
//	    			 );
//		}
//		else {
//			try {
//
//				try (FileOutputStream output = new FileOutputStream(IMAGE_FOLDER + file.getOriginalFilename())) {
//					output.write(file.getBytes());
//				}
//				photo.setImgURL("/imgs/" + file.getOriginalFilename());
//				
//			} catch (IOException e) {
//
//				e.printStackTrace();
//			}
//		
//			
//			return photoServ.createOne(photo);
//
//		}
//		
//	}
	
//	@GetMapping("/files/{filename:.+}")
//	  @ResponseBody
//	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//	    Resource file = fileServ.load(filename);
//	    return ResponseEntity.ok()
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	  }
	
	
	
	
	
	
	//////  NEW LOGIN/REG //////
	
//	   @GetMapping("/")
//	   public String loginPage(Model model) {
//	       model.addAttribute("newUser", new User());
//	       model.addAttribute("newLogin", new LoginUser());
//	       return "views/login.jsp";
//	   }
	   

	   
	   // Login User Process
	   @PostMapping("/loginUser")
	   public ResponseEntity<User> loginUser(@Valid @RequestBody LoginUser newLogin, BindingResult result, HttpSession session) {
	    User user = userServ.login(newLogin, result);

	   	session.setAttribute("user_id", user.getId());

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
