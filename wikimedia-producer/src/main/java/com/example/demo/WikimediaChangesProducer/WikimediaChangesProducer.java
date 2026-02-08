package com.example.demo.WikimediaChangesProducer;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import okhttp3.Headers;


import com.example.demo.wikimediahandler.WikimediaChangesHandler;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

@Service
public class WikimediaChangesProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);
   
	private KafkaTemplate<String, String> kafkatemplate;

	public WikimediaChangesProducer(KafkaTemplate<String, String> kafkatemplate) {
		this.kafkatemplate = kafkatemplate;
	}
	
	public void sendMessage() throws InterruptedException {
		
		String topic = "wikimedia_recentchange";
		
		
	 
		
	   //to read real time string data from wikimedia we use event source
		
		
	   EventHandler eventhandler = new WikimediaChangesHandler(kafkatemplate,topic);
	   String url = "https://stream.wikimedia.org/v2/stream/recentchange";
	   
	   EventSource eventSource =
		        new EventSource.Builder(eventhandler, URI.create(url))
		        .headers(Headers.of(
		        	    "User-Agent", "Kafka-Demo-App (pravalikareddy1114@gmail.com)"
		        	))
		                .build();

	   eventSource.start();
	   
	   TimeUnit.MINUTES.sleep(10);
	   
 
	}
	
	
}
