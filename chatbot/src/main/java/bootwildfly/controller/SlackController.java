package bootwildfly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.camel.component.slack.helper.SlackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bootwildfly.service.RouteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SlackController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	/**
	 * @ApiOperation annotation you can specify things like the default status
	 *               code and the response type being returned when the
	 *               operation completes successfully and also provide
	 *               documentation for other responses than the regular HTTP 200
	 *               OK.
	 * @param name
	 * @return {@link Greeting}
	 */
	
	@Autowired
	private RouteService routeService;

	@ApiOperation(value = "greeting", nickname = "greeting", response = Greeting.class)
	@ApiResponses({
			@ApiResponse(code = 404, message = "Not found", response = Exception.class),
			@ApiResponse(code = 200, message = "Request Successful", response = Greeting.class) })
	@RequestMapping(value = "/slackgreeting", method = RequestMethod.GET, produces = "application/json")
	public Greeting greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		routeService.sayHello();
		return new Greeting(counter.incrementAndGet(), String.format(template,
				name));
	}
	
	@ApiOperation(value = "greeting", nickname = "greeting", response = Greeting.class)
	@ApiResponses({
			@ApiResponse(code = 404, message = "Not found", response = Exception.class),
			@ApiResponse(code = 200, message = "Request Successful", response = Greeting.class) })
	@RequestMapping(value = "/slackbalance", method = RequestMethod.GET, produces = "application/json")
	public Greeting balance(
			@RequestParam(value = "name", defaultValue = "1.99") String balance) {
		routeService.displayBalance(balance);
		return new Greeting(counter.incrementAndGet(), String.format(template,
				balance));
	}
	
	

	
	@ApiOperation(value = "balance", nickname = "report bug", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 404, message = "Not found", response = Exception.class),
			@ApiResponse(code = 200, message = "Request Successful", response = String.class) })
	@RequestMapping(value = "/reportbug", method = RequestMethod.POST, produces = "application/text")
	public String reportbug(
			@RequestParam(value = "name", defaultValue = "bug reported") String balance) {
	
		return "Thank you bug reported";
	}
	
	@ApiOperation(value = "balance", nickname = "report bug", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 404, message = "Not found", response = Exception.class),
			@ApiResponse(code = 200, message = "Request Successful", response = String.class) })
	@RequestMapping(value = "/message", method = RequestMethod.POST, produces = "application/text")
	public SlackMessage slackmsg(
			@RequestParam(value = "name", defaultValue = "bug reported") String balance) {
		SlackMessage slackMessage = new SlackMessage();
		slackMessage.setText("Would you like to play a game?");
		List<Attachment> attachments = new  ArrayList<>();
		Attachment attachment = Attachment();
		attachments.add(attachment );
		slackMessage.setAttachments(attachments );
		return slackMessage;
	}
}