package bootwildfly.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootwildfly.model.Transaction;
import bootwildfly.repo.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;

	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
		transactionRepository.save( getTransaction("123",BigDecimal.ONE));
		transactionRepository.save( getTransaction("124",BigDecimal.TEN));
	}

	private Transaction getTransaction(String accountNo, BigDecimal amount) {
		Transaction transaction = new Transaction();
		transaction.setAccountNo(accountNo);
		transaction.setAmount(amount );
		return transaction;
	}

	@Override
	public void deposit(Transaction transaction) {
		transactionRepository.save(transaction);
		
	}

	@Override
	public Transaction checkbalance(String accountNo) {
		List<Transaction> transactions = transactionRepository.findByAccountNo(accountNo);
		 BigDecimal amount = BigDecimal.ZERO;
		 for (Transaction transaction :transactions) {
			 amount = amount.add(transaction.getAmount());
		 }
		Transaction transaction = new Transaction();
		transaction.setAmount(amount );
		return transaction;
	}


	
	
}