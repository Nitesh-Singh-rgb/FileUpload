package com.springapi.uploading.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springapi.uploading.model.Product;
import com.springapi.uploading.service.ProductService;
import com.springapi.uploading.service.ResponseClass;

@CrossOrigin
@RestController
@RequestMapping("/api/files")
public class ProductController {
	
	@Autowired
	private ProductService fileService;
	
	@PostMapping(value = "/single/base", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseClass uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
		
		Product attachment = null;
		String downloadURL = "";
		attachment = fileService.saveAttachment(file);
		downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/")
				.path(attachment.getId())
				.toUriString();
		
		return new ResponseClass(attachment.getFileName(),
				downloadURL,
				file.getContentType(),
				file.getSize());
	}
	
	@PostMapping(value = "/single/file")
	public ResponseEntity<ResponseClass> handleFileUpload(@RequestParam("file") MultipartFile file){
		String fileName = file.getOriginalFilename();
//		System.out.println(fileName);
		try {
			file.transferTo(new File("E:\\Folder\\" + fileName));
			String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/download/")
					.path(fileName)
					.toUriString();
			ResponseClass response = new ResponseClass(fileName, downloadUrl, file.getContentType(), file.getSize());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
