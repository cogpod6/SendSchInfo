package com.cts.learning.SendSchInfo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.cts.learning.SendSchInfo.model.Appointment;

@Configuration
public class KafkaProducerConfiguration {
	
	@Value("${kafka.bootstrap-server}")
    public String bootstrapServerUrl;

    public Map<String,Object> configMap() {
        Map<String,Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerUrl);
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return configMap;
    }

    @Bean
    public ProducerFactory<String, Appointment> producerFactory(){
        return new DefaultKafkaProducerFactory<>(configMap());
    }

    @Bean
    public KafkaTemplate<String, Appointment> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

}
