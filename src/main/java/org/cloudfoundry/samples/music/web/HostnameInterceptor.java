package org.cloudfoundry.samples.music.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * The aim of this interceptor is to add a header to all responses that
 * will enable clients to know which server handled the request.
 */
@Component
public class HostnameInterceptor extends HandlerInterceptorAdapter {

 public static final String SERVER_HOSTNAME = HostnameInterceptor.getHostname();

 /**
  * Add a header containing the server hostname to all responses.
  */
 @Override
 public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object object) throws Exception {
	response.addHeader("Music-Backend-Server", SERVER_HOSTNAME);

	return true;
 }
 
 private static String getHostname(){
	String name = "undefined";
	try{
		InetAddress inetAddress = InetAddress.getLocalHost();
		name = inetAddress.getHostName();
	}
	catch(UnknownHostException uhex) { 
		// Return default value if we run into problems. 
	}

	return name;
 }
}