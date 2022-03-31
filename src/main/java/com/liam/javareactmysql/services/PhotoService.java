package com.liam.javareactmysql.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liam.javareactmysql.controllers.Photo;
import com.liam.javareactmysql.repositories.PhotoRepository;

@Service
public class PhotoService {
	
	@Autowired
	public PhotoRepository photoRepo;
	
	// Get All
		public List<Photo> getAll() {
			return photoRepo.findAll();
		}
		
		
//		public List<Photo> findAllByOrderByTitleAsc() {
//			return photoRepo.findAllByOrderByTitleAsc();
//		}
		
		
		
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
