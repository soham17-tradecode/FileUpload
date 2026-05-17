package com.File.Distribution.FileSharing.repo;

import com.File.Distribution.FileSharing.model.fileMetaData;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface fileMetaRepo extends JpaRepository<fileMetaData, Integer> {
      fileMetaData findByFileId(String fileId);
}
