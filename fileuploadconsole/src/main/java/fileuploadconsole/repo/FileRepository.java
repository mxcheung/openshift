package fileuploadconsole.repo;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import fileuploadconsole.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

	
	List<FileEntity> findByValueDate(LocalDate valueDate);
	
	@Modifying
	@Transactional
	void deleteById(Long Id);
}