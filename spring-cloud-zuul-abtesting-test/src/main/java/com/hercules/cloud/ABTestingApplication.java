package com.hercules.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableZuulProxy
@SpringCloudApplication
public class ABTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ABTestingApplication.class, args);
	}


	@RestController
	@RequestMapping("/services")
	public class ServiceController {

		public static final String RESPONSE_BODY = "ResponseBody";

		@GetMapping("/serviceA")
		public ResponseEntity<String> serviceA() {
			return ResponseEntity.ok("SERVICEA");
		}

		@GetMapping("/serviceB")
		public ResponseEntity<String> serviceB() {
			return ResponseEntity.ok("SERVICEB");
		}

	}
}
