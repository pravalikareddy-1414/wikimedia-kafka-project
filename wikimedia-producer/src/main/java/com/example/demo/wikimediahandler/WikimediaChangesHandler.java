package com.example.demo.wikimediahandler;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.demo.WikimediaChangesProducer.WikimediaChangesProducer;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

public class WikimediaChangesHandler implements EventHandler {

	
	private KafkaTemplate<String, String> kafkaTemplate;
	private String topic;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);
	   
	
    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
		
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	}

	@Override
    public void onOpen() throws Exception {
        
    }

    @Override
    public void onClosed() throws Exception {
        System.out.println("Connection closed");
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("event data ->s", messageEvent.getData()));
        
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String comment) throws Exception {
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }
}

