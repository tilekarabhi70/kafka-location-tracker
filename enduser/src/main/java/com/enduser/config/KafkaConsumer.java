package com.enduser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import com.enduser.AppConstatnt;

@Configuration
public class KafkaConsumer {

    @KafkaListener(topics = AppConstatnt.LOCATION_UPDATE_TOPIC, groupId = AppConstatnt.GROUP_ID)
    public void updatedLocation(String value) {
        System.out.println(value);
    }
}
