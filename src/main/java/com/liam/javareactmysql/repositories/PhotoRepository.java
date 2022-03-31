package com.liam.javareactmysql.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liam.javareactmysql.controllers.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
	List<Photo> findAll();

}
