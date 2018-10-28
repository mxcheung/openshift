package bootwildfly.controller;

import java.util.concurrent.atomic.AtomicLong;

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
}