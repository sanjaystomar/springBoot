package com.learning.filemanager.model;

import java.util.Objects;
import org.springframework.data.annotation.Id;

public class FileDetails {

  @Id
  private String fileName;
  private String filePath;
  private long fileSize;
  private String fileExtension;

  public FileDetails(String fileName, String filePath, long fileSize, String fileExtension) {
    this.fileName = fileName;
    this.filePath = filePath;
    this.fileSize = fileSize;
    this.fileExtension = fileExtension;
  }

  public FileDetails() {
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public String getFileExtension() {
    return fileExtension;
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileDetails that = (FileDetails) o;
    return fileSize == that.fileSize &&
        fileName.equals(that.fileName) &&
        filePath.equals(that.filePath) &&
        fileExtension.equals(that.fileExtension);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, filePath, fileSize, fileExtension);
  }

  @Override
  public String toString() {
    return "FileDetails{" +
        "fileName='" + fileName + '\'' +
        ", filePath='" + filePath + '\'' +
        ", fileSize=" + fileSize +
        ", fileExtension='" + fileExtension + '\'' +
        '}';
  }
}
