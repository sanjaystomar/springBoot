package com.learning.filemanager.utils;

import com.learning.filemanager.model.FileDetails;
import java.nio.file.Path;
import org.springframework.stereotype.Component;

@Component
public class FileManagerUtil {

  public static FileDetails mapFileDetails(Path inFilePath) {
    String rawfile = inFilePath.toFile().getName();
    String fileName = rawfile.substring(0,rawfile.lastIndexOf('.'));
    String filePath = inFilePath.toAbsolutePath().getParent().toString();
    long fileSize = inFilePath.toFile().length() / 1024;
    String extnPart = rawfile.substring(rawfile.lastIndexOf('.') + 1);
    String extension = extnPart;
    return new FileDetails(fileName, filePath, fileSize, extension);
  }
}
