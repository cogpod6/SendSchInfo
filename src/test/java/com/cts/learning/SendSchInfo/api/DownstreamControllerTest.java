package com.cts.learning.SendSchInfo.api;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.util.ReflectionTestUtils;

import com.cts.learning.SendSchInfo.model.Appointment;
import com.cts.learning.SendSchInfo.service.Producer;

@RunWith(EasyMockRunner.class)
public class DownstreamControllerTest {
	
	private DownstreamController downstreamController;
	Producer producerService;
	
	@Before
	public void setUp () {
		downstreamController = new DownstreamController();
		producerService = EasyMock.createMock(Producer.class);
				
		ReflectionTestUtils.setField(downstreamController, "producerService", producerService);
	}
	
	@Test
	public void sendMessageTest () {
		EasyMock.expect(producerService.sendMessage(EasyMock.anyObject(Appointment.class)) ).andReturn("Success").times(1);
		EasyMock.replay(producerService);
		
		downstreamController.sendMessage(getAppointment());
		EasyMock.verify(producerService);
	}
	
	public Appointment getAppointment () {
		Appointment appointment = new Appointment();
		List<String> pos = new ArrayList<>();
		pos.add("1111111111");
		
		appointment.setAppointmentDate("2020-11-04");
		appointment.setAppointmentId(137);
		appointment.setDcNumber("7030");
		appointment.setTimeSlot("07:00 - 08:00");
		appointment.setTruckNumber("1234");
		appointment.setPos(pos);
		
		return appointment;
	}

}
