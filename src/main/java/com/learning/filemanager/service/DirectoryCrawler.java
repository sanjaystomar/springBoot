package com.learning.filemanager.service;

import com.learning.filemanager.model.FileDetails;
import com.learning.filemanager.utils.FileManagerUtil;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryCrawler {

  @Autowired
  private FileManagerUtil fileManagerUtil;
  private Path providedPath;


  public void setFileManagerUtil(FileManagerUtil fileManagerUtil) {
    this.fileManagerUtil = fileManagerUtil;
  }

  public void setProvidedPath(Path providedPath) {
    this.providedPath = providedPath;
  }

  public Stream<Path> capturePaths() throws IOException {
    return Files.walk(Paths.get("C:\\Users\\santomar1\\Documents\\backUP"));
  }

  public void exploreFiles() {
    try {
      capturePaths()
          .filter(path -> path.toFile().isFile())
          .map(FileManagerUtil::mapFileDetails)
          .forEach(System.out::println);
      capturePaths().close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
