package bootwildfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bootwildfly.model.Transaction;
import bootwildfly.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	@RequestMapping(value = "/transaction", method = RequestMethod.POST, produces = "application/text")
	public String reportbug(@RequestBody Transaction transaction) {
		transactionService.deposit(transaction);
		return "Thank you transaction posted";
	}
	
	@ApiOperation(value = "balance", nickname = "check balance", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 404, message = "Not found", response = Exception.class),
			@ApiResponse(code = 200, message = "Request Successful", response = String.class) })
	@RequestMapping(value = "/slackbalance", method = RequestMethod.POST, produces = "application/text")
	public String postbalance(
			@RequestParam(value = "text", defaultValue = "123") String accountNo,
			@RequestParam(value = "user_name", defaultValue = "123") String user_name) {
		Transaction transaction = transactionService.checkbalance(accountNo);
		return user_name + " : your current balance is :" + transaction.getAmount();
	}
}