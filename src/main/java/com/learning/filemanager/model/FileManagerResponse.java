package com.learning.filemanager.model;

import java.util.List;

public class FileManagerResponse {
  private long fileProcessed;
  private List<FileDetails> fileDetailsList;

  public FileManagerResponse(long fileProcessed,
      List<FileDetails> fileDetailsList) {
    this.fileProcessed = fileProcessed;
    this.fileDetailsList = fileDetailsList;
  }

  public FileManagerResponse() {
  }

  public long getFileProcessed() {
    return fileProcessed;
  }

  public void setFileProcessed(long fileProcessed) {
    this.fileProcessed = fileProcessed;
  }

  public List<FileDetails> getFileDetailsList() {
    return fileDetailsList;
  }

  public void setFileDetailsList(
      List<FileDetails> fileDetailsList) {
    this.fileDetailsList = fileDetailsList;
  }

  @Override
  public String toString() {
    return "FileManagerResponse{" +
        "fileProcessed=" + fileProcessed +
        ", fileDetailsList=" + fileDetailsList +
        '}';
  }
}
