package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.WikimediaChangesProducer.WikimediaChangesProducer;

@SpringBootApplication
public class WikimediaProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WikimediaProducerApplication.class, args);
	}
	
	@Autowired
	private WikimediaChangesProducer wikimediachang; 
	
	@Override
	public void run(String...args) throws Exception{
		
		wikimediachang.sendMessage();
	}
	

}
