package com.andre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.andre.netty.NettyTestServer;

@RestController
@SpringBootApplication
public class ScorepioApplication {

	private static NettyTestServer nettyTestServer;
	
	@Autowired
	public void setDiscardServer(NettyTestServer nettyTestServer) {
		ScorepioApplication.nettyTestServer = nettyTestServer;
	}

	// @RequestMapping("/")
	// String home() {
	// return "Hello World!";
	// }

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ScorepioApplication.class);
		app.setWebEnvironment(false);
		app.run(args);

		try {
			nettyTestServer.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
