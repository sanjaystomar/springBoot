package com.learning.filemanager.controller;

import com.learning.filemanager.model.FileDetails;
import com.learning.filemanager.model.FileManagerResponse;
import com.learning.filemanager.repository.FileManagerMongoRepository;
import com.learning.filemanager.service.DirectoryCrawler;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "fileManager")
public class FileManagerController {
  @Autowired
  DirectoryCrawler directoryCrawler;

  @Autowired
  FileManagerMongoRepository fileManagerMongoRepository;
//  @GetMapping ("explore")
  @RequestMapping(value = "explore", method = RequestMethod.GET)
  public void exploreFilesEndPoint(){
    directoryCrawler.exploreFiles();
  }

  @RequestMapping(value = "explore", method = RequestMethod.POST, consumes = "application/json")
  @PostMapping("explore")
  public ResponseEntity<FileManagerResponse> exploreFilesInDirectory(@RequestBody String path){
    FileManagerResponse fileManagerResponse = new FileManagerResponse();
    fileManagerResponse.setFileProcessed(directoryCrawler.exploreFiles(path));
    ResponseEntity responseEntity = new ResponseEntity(HttpStatus.CREATED);
    responseEntity.getStatusCodeValue();


    return responseEntity;
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping(value = "db-data", method = RequestMethod.GET)
  public List<FileDetails> listFilesDetail(){
    return fileManagerMongoRepository.findAll();
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @RequestMapping(value = "grouped-data", method = RequestMethod.GET)
  public Map<String,List<FileDetails>> groupDbData(){
    return directoryCrawler.groupFiles();
  }

}
