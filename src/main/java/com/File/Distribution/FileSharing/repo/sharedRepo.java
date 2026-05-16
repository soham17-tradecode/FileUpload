package com.File.Distribution.FileSharing.repo;

import com.File.Distribution.FileSharing.model.sharedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface sharedRepo extends JpaRepository<sharedEntity,Integer> {
    Optional<sharedEntity> findByFieldIdAndSharedWith(int fieldId,String sharedWith);
    List<sharedEntity> findBySharedWith(String sharedWith);
}
