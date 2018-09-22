package fileuploadconsole.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fileuploadconsole.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {


}