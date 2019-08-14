package com.learning.filemanager.repository;

import com.learning.filemanager.model.FileDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileManagerMongoRepository extends MongoRepository<FileDetails, String> {

}
