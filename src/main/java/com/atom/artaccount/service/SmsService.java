package com.atom.artaccount.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsService {


	private static final String ID = "";
	private static final String AUTH_TOKEN = "";


	public  void sendSms(String content, String number) {


			Twilio.init(ID, AUTH_TOKEN);
			Message message = Message.creator(
					new com.twilio.type.PhoneNumber("+221" + number),
					"MG689f9716cf2c332da439e7b247ab2f09",
							content)

							.create();

	        System.out.println(message.getSid());
	    }

	public void sendSms(String content, List<String> numbers) {
		Twilio.init(ID, AUTH_TOKEN);

		numbers.forEach(number -> {
			Message message = Message.creator(
					new PhoneNumber("+221" + number),
					"MG8aae77348b50a53e48a65090323b67a1", // The 'From' number must be a valid Twilio number
					content
			).create();

			System.out.println("Sent message to " + number + " with SID: " + message.getSid());
		});
	}




}