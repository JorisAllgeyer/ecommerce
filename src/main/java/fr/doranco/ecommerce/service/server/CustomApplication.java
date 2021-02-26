package fr.doranco.ecommerce.service.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import fr.doranco.ecommerce.service.provider.AnthenticationFilter;
import fr.doranco.ecommerce.service.provider.GsonMessageBodyHandler;

public class CustomApplication extends ResourceConfig {
	
	public CustomApplication() {
		packages("fr.doranco.ecommerce.service");
		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), 
			Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
		register(GsonMessageBodyHandler.class);
		register(AnthenticationFilter.class);
	}
}
