package com.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.services.EmailService;

@SpringBootTest
class ScmApplicationTests {

	@Autowired
	private EmailService emailService;

	@Test
	void contextLoads() {
	}

	@Test
	void sendEmailTest() {
		emailService.sendEmail("gauravkumar12feb2000@gmail.com", "Just testing my email service",
				"This is Smart-Contact-Manager working on email service");
	}

}
