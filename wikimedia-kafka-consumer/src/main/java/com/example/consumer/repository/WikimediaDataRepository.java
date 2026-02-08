package com.example.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.consumer.Entity.*;

public interface  WikimediaDataRepository  extends JpaRepository<WikimediaData,Long>{

}
