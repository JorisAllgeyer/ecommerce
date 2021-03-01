package fr.doranco.ecommerce.service.provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AnthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {
	
	@Context
	private ResourceInfo resourceInfo;

	private final static String AUTHORIZATION_PROPERTY = "Authorization";
	private final static String AUTHORIZATION_SCHEME = "Basic";
	
	private Response getAccessDeniedResponse() {
		return Response.status(Response.Status.UNAUTHORIZED)
			.entity("Vous n'avez pas le droit d'accès à cette ressource.").build();
	}
	
	private Response getAccessForbiddenResponse() {
		return Response.status(Response.Status.FORBIDDEN)
			.entity("Accçs bloqué à tous les utilisateurs.").build();
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Method method = resourceInfo.getResourceMethod();
		
		// FOR DEVELOPMENT getWadl
		if (!method.getName().equals("getWadl")) {
			if (!method.isAnnotationPresent(PermitAll.class)) {
				if (method.isAnnotationPresent(DenyAll.class)) {
					requestContext.abortWith(getAccessForbiddenResponse());
					return;
				}
				
				final MultivaluedMap<String, String> headers = requestContext.getHeaders();
				
				final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
				
				if (authorization == null || authorization.isEmpty()) {
					requestContext.abortWith(getAccessDeniedResponse());
					return;
				}
				
				final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHORIZATION_SCHEME + " ", " ");
				
				String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword.getBytes()));
				
				final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
				final String username = tokenizer.nextToken();
				final String password = tokenizer.nextToken();
				
				System.out.println("username: " + username);
				System.out.println("password: " + password);
				
				if (method.isAnnotationPresent(RolesAllowed.class)) {
					RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
					Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	
					if (!isUserAllowed(username, password, rolesSet)) {
						requestContext.abortWith(getAccessDeniedResponse());
					}
				}
			}
		}
	}
	
	private boolean isUserAllowed(
		final String username, 
		final String password, 
		final Set<String> rolesSet) {
		
		boolean isAllowed = false;
		
		ResourceBundle rb = ResourceBundle.getBundle("fr.doranco.ecommerce.service.dbfile");
		
		if (username.equals(rb.getString("WS_USER")) && password.equals(rb.getString("WS_PASSWORD"))) {
			String userRole = "WS";
			
			if (rolesSet.contains(userRole)) isAllowed = true;
		}
		
		return isAllowed;
	}

}
