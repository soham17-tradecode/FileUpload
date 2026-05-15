package com.File.Distribution.FileSharing.repo;

import com.File.Distribution.FileSharing.model.fileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface fileDataRepo extends JpaRepository<fileData,Integer> {
    fileData findById(int id);
}
