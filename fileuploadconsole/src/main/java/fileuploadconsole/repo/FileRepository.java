package fileuploadconsole.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import fileuploadconsole.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

	@Modifying
	@Transactional
	void deleteById(Long Id);
}