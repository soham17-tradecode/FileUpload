package com.File.Distribution.FileSharing.repo;

import com.File.Distribution.FileSharing.model.fileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface fileDataRepo extends JpaRepository<fileData,Integer> {
    Optional<fileData> findById(int id);
    List<fileData> findByuploadBy(String uploadBy);
}
