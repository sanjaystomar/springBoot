package com.learning.filemanager.service;

import com.learning.filemanager.model.FileDetails;
import com.learning.filemanager.repository.FileManagerMongoRepository;
import com.learning.filemanager.utils.FileManagerUtil;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryCrawler {

  @Autowired
  private FileManagerUtil fileManagerUtil;

  @Autowired
  FileManagerMongoRepository fileManagerMongoRepository;
  private Path providedPath;

  public void setFileManagerUtil(FileManagerUtil fileManagerUtil) {
    this.fileManagerUtil = fileManagerUtil;
  }

  public void setProvidedPath(Path providedPath) {
    this.providedPath = providedPath;
  }

  public void setFileManagerMongoRepository(
      FileManagerMongoRepository fileManagerMongoRepository) {
    this.fileManagerMongoRepository = fileManagerMongoRepository;
  }

  public Stream<Path> capturePaths() throws IOException {
    return Files.walk(Paths.get("C:\\Users\\santomar1"));
  }

  public long exploreFiles(String inPath) {
    Path givenPathToExplore = Paths.get(inPath);
    try {
      capturePaths(givenPathToExplore)
          .filter(path -> path.toFile().isFile() && Files.isReadable(path))
          .peek(System.out::println)
          .map(FileManagerUtil::mapFileDetails)
          .forEach(fileManagerMongoRepository::save);
      capturePaths().close();
      return fileManagerMongoRepository.count();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileManagerMongoRepository.count();
  }

  private Stream<Path> capturePaths(Path givenPathToExplore) throws IOException {
    System.out.println(givenPathToExplore + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    return Files.walk(givenPathToExplore);
  }

  public void exploreFiles() {
    try {
      capturePaths()
          .filter(path -> path.toFile().isFile() && Files.isReadable(path))
          .peek(System.out::println)
          .map(FileManagerUtil::mapFileDetails)
          .forEach(fileManagerMongoRepository::save);
      capturePaths().close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Map<String, List<FileDetails>> groupFiles() {
    return fileManagerMongoRepository.findAll()
        .stream()
        .collect(Collectors.groupingBy(FileDetails::getFileExtension));
  }

}
