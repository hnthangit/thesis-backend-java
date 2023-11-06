package com.shopping.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.service.IFileService;

@Service
@Transactional
public class FileServiceImpl implements IFileService {
	
	private final Path rootLocation = Paths.get("C://Users//Blindfold Gang//Desktop//images/");

	@Override
	public String save(MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = UUID.randomUUID().toString() +"."+ extension;
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize());
		Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
		return fileName;
	}

}
