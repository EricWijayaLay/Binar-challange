package com.binar.Batch7;

import com.binar.Batch7.Controller.fileupload.FileStorageProperties;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})

public class Batch7Application {
	private static final Logger logger = LoggerFactory.getLogger(Batch7Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Batch7Application.class, args);
		logger.info("info logging level");
		logger.error("eror logging level");
		logger.warn("warning logging level");
		logger.debug("debug logging level");
		logger.trace("trace logging level");
		logger.error("Error processing request");
	}

}
