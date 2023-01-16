package com.example.controller;

import java.io.File;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@Resource(name = "uploadPath")
	private String path;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping("/api/display")
	@ResponseBody
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		ResponseEntity<byte[]> image = null;
		File file = new File(fileName);
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", Files.probeContentType(file.toPath()));
		image = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		return image;
	}

}
