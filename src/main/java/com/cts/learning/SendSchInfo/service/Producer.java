package com.cts.learning.SendSchInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.cts.learning.SendSchInfo.model.Appointment;

@Service
public class Producer {
	
	@Value("${kafka.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Appointment> kafkaTemplate;
	
	public String sendMessage(Appointment appointment) {
		StringBuilder sb = new StringBuilder();
        final ListenableFuture<SendResult<String, Appointment>> producer = kafkaTemplate.send(topicName, appointment);
        producer.addCallback(new ListenableFutureCallback<SendResult<String, Appointment>>() {
            @Override
            public void onFailure(Throwable throwable) {
                sb.append(throwable.getMessage());
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, Appointment> result) {
                System.out.println("Data successfully registered with Kafka Topic..");
                System.out.println("Partition -> "+result.getRecordMetadata().partition());
                System.out.println("Offset -> "+result.getRecordMetadata().offset());
                sb.append("Success");
            }
        });
		
        return sb.toString();
	}

}
