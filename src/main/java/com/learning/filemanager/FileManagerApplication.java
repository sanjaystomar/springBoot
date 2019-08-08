package com.learning.filemanager;

import com.learning.filemanager.service.DirectoryCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FileManagerApplication {

	@Autowired
	private DirectoryCrawler directoryCrawler;

	public static void main(String[] args) {
		ApplicationContext springContext = SpringApplication.run(FileManagerApplication.class, args);
		DirectoryCrawler directoryCrawler = springContext.getBean("directoryCrawler", DirectoryCrawler.class);
		directoryCrawler.exploreFiles();
	}

}
