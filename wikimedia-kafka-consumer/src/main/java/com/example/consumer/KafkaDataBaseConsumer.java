package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.consumer.Entity.WikimediaData;
import com.example.consumer.repository.WikimediaDataRepository;

@Service
public class KafkaDataBaseConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDataBaseConsumer.class);
	
	private WikimediaDataRepository dataRepository;
	
	public KafkaDataBaseConsumer(WikimediaDataRepository dataRepository) {
		super();
		this.dataRepository = dataRepository;
	}

	@KafkaListener(
			topics = "wikimedia_recentchange", 
			groupId = "wikimedia-group"
			
			
			)
	
	public void consumer(String eventMessage) {
		
		LOGGER.info(String.format("Message received ->", eventMessage));
		
		WikimediaData wikimedia = new WikimediaData();
		wikimedia.setWikiEventData(eventMessage);

	}
}
