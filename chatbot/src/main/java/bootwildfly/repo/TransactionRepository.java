package bootwildfly.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bootwildfly.model.Transaction;


public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	List<Transaction> findByAccountNo(String accountNo);
}