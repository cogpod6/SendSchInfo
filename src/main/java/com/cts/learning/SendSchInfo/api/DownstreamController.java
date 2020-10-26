package com.cts.learning.SendSchInfo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.learning.SendSchInfo.model.Appointment;
import com.cts.learning.SendSchInfo.service.Producer;

@RestController
@RequestMapping("publish")
public class DownstreamController {
	
	@Autowired
    Producer producerService;
	
    @PostMapping
    public String sendMessage(@RequestBody Appointment appointment) {
        return producerService.sendMessage(appointment);
    }

}
