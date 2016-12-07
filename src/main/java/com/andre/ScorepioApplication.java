package com.andre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.andre.netty.DiscardServer;

@RestController
@SpringBootApplication
public class ScorepioApplication {

	private static DiscardServer discardServer;
	
	@Autowired
	public void setDiscardServer(DiscardServer discardServer) {
		ScorepioApplication.discardServer = discardServer;
	}
	
//	@RequestMapping("/")
//	String home() {
//		return "Hello World!";
//	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ScorepioApplication.class);
		app.setWebEnvironment(false);
		app.run(args);
		try {
			discardServer.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
