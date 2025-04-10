package com.deliveryboy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.deliveryboy.config.AppConstatnts;

@Service
public class KafkaService {
	@Autowired
  private KafkaTemplate<String,String> kafkaTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);
	public boolean updateLocation(String location) {
		
		this.kafkaTemplate.send(AppConstatnts.LOCATION_TOPIC_NAME,location);
		this.logger.info("Message Produce");
		return true;
	}
	
}
