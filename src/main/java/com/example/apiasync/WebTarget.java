package com.example.apiasync;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WebTarget {

	@Autowired
	private ResteasyClient resteasyClient;

	@Bean
	public ResteasyClient resteasyClient() {

		ResteasyClientBuilder clientBuilder = (ResteasyClientBuilder) ResteasyClientBuilder.newBuilder();
		clientBuilder.connectTimeout(15, TimeUnit.SECONDS);
		clientBuilder.readTimeout(15, TimeUnit.SECONDS);
		return clientBuilder.build();

	}

	public String getURL(String url) {
		Response response;
		String resu = "";

		try {
			ResteasyWebTarget target = resteasyClient.target(url);
			response = target.request(MediaType.APPLICATION_JSON).get();
			System.out.println("status : " + response.getStatus());
			resu = response.readEntity(String.class);
		} catch (ProcessingException pr) {
			Throwable cause = pr.getCause();
			if (cause instanceof SocketTimeoutException) {
				System.out.println("timeout-ok");
				resu = "timeout";
			} else {
				resu = "erro ProcessingException";
			}
			pr.printStackTrace();
		} catch (Exception e) {
			if (e instanceof SocketTimeoutException) {
				System.out.println("timeout-ok");
			}
			e.printStackTrace();
			resu = "Exception";
		}

		return resu;
	}

}
