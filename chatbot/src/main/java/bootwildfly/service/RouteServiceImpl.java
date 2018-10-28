package bootwildfly.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bootwildfly.model.RouteDef;
import bootwildfly.processor.NoopProcessor;
import bootwildfly.route.DefaultRouteBuilder;
import bootwildfly.route.SlackRouteBuilder;

@Service
public class RouteServiceImpl implements RouteService {

	private static final String CAMELSIMPLE_ROUTE_PATH = "camelsimple.route.path";

	private static final Logger LOG = LoggerFactory.getLogger(RouteServiceImpl.class);

	private final CamelContext camelContext;
	private final Environment env;
	private List<RouteDef> routeDefs;
	private ObjectMapper mapper = new ObjectMapper();
	private ProducerTemplate producerTemplate;
	
	
	@Autowired
	public RouteServiceImpl(Environment env, CamelContext camelContext) {
		this.env = env;
		this.camelContext = camelContext;
	//	this.camelContext = new DefaultCamelContext();
		loadRoutes();
//		sayHello();
	}


	@Override
	public void sayHello() {
		this.producerTemplate = camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:test", "<hello>world!</hello>");
	}


	public void loadRoutes() {
		try {
			this.routeDefs = initRoute();
		} catch (Exception e) {
			LOG.error("Exception ocurred loading routes {}", e);
		}
	}


	@Override
	public List<RouteDef> getRouteDefs() {
		return routeDefs;
	}

	@Override
	public List<String> getCamelRoutes() {
		List<String> routes = new ArrayList<String>();
		List<RouteDefinition> camelDefs = camelContext.getRouteDefinitions();
		for (RouteDefinition routeDefinition : camelDefs) {
			routes.add(routeDefinition.toString());
		}
		return routes;
	}

	@Override
	public List<File> getFiles(String locationPattern) throws IOException {
		List<File> filesInFolder = Files.walk(Paths.get(locationPattern))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
		return filesInFolder;
	}
	
	private RoutesBuilder getRouteBuilder(RouteDef routeOptions) {
		RoutesBuilder routesBuilder;
		if ("SLACK".equalsIgnoreCase(routeOptions.getRouteType())) {
			routesBuilder = new SlackRouteBuilder(camelContext, new NoopProcessor(), routeOptions);
		} else {
			routesBuilder = new DefaultRouteBuilder(camelContext, new NoopProcessor(), routeOptions);
		}
		return routesBuilder;
	}

	Resource[] loadResources(String pattern) throws IOException {
		return ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader()).getResources(pattern);
	}

	public List<RouteDef> initRoute() throws Exception {
		List<RouteDef> routes = new ArrayList<RouteDef>();
		RouteDef routeDef = new  RouteDef();
		routeDef.setRouteId("slack-in2-route");
		routeDef.setRouteType("SLACK");
		routeDef.setFrom("direct:test");
		String[] toUris = {"slack:#interceptor"};
		routeDef.setToUris(toUris);
		camelContext.addRoutes(getRouteBuilder(routeDef));
		routes.add(routeDef);

		return routes;
	}

}