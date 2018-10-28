package bootwildfly.service;


import java.io.File;
import java.io.IOException;
import java.util.List;

import bootwildfly.model.RouteDef;

public interface RouteService {

	List<RouteDef> getRouteDefs();

	List<String> getCamelRoutes();

	List<File> getFiles(String locationPattern) throws IOException;

	void sayHello();
	
}