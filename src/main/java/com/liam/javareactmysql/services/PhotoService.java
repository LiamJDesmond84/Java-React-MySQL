package com.liam.javareactmysql.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liam.javareactmysql.VOs.ResponseTemplateVO;
import com.liam.javareactmysql.models.Photo;
import com.liam.javareactmysql.models.User;
import com.liam.javareactmysql.repositories.PhotoRepository;
import com.liam.javareactmysql.repositories.UserRepository;

@Service
public class PhotoService {
	
	@Autowired
	public PhotoRepository photoRepo;
	
	@Autowired
	public UserRepository userRepo;
	
	// Get All
		public List<Photo> getAll() {
			return photoRepo.findAll();
		}
		
		
//		public List<Photo> findAllByOrderByTitleAsc() {
//			return photoRepo.findAllByOrderByTitleAsc();
//		}
		
		
		public ResponseTemplateVO getUserPhotos(Long id) {
			ResponseTemplateVO vo = new ResponseTemplateVO();
			Photo photo = photoRepo.findById(id).orElse(null);
			
			User user = userRepo.findById(photo.getOwner().getId()).orElse(null);
			
			vo.setPhoto(photo);
			vo.setUser(user);
			return vo;
		}
		
		
		// Get One
		public Photo getOne(Long id) {
			return photoRepo.findById(id).orElse(null);
		}
		
		// Create
		public Photo createOne(Photo Photo) {
			return photoRepo.save(Photo);
		}
		
		
		// Update
		public Photo updateOne(Photo Photo) {
			return photoRepo.save(Photo);
		}
		
		// Delete
		public void deleteOne(Long id) {
			photoRepo.deleteById(id);
		}

}
