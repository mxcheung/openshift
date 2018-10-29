package bootwildfly.service;


import bootwildfly.model.Transaction;

public interface TransactionService {

	void deposit(Transaction transaction);
	

	Transaction checkbalance(String accountNo);
}