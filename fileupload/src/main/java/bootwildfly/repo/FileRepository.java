package bootwildfly.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bootwildfly.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findByBatchId(Long batchId);

    List<FileEntity> findByBatchIdIn(List<Long> batchIds);

}