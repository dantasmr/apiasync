package com.example.apiasync;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ApiasyncService {

	@Autowired
	private WebTarget webTarget;

	@Async("asyncExecutor")
	public CompletableFuture<String> getContratos() {

		String url = "http://localhost:3000/api/contratos";
		String resu = webTarget.getURL(url);
		return CompletableFuture.completedFuture(resu);

	}

	@Async("asyncExecutor")
	public CompletableFuture<String> getCertificados() {

		String url = "http://localhost:3000/api/certificados";
		String resu = webTarget.getURL(url);
		return CompletableFuture.completedFuture(resu);

	}

	@Async("asyncExecutor")
	public CompletableFuture<String> getClientes() {

		String url = "http://localhost:3000/api/clientes";
		String resu = webTarget.getURL(url);
		return CompletableFuture.completedFuture(resu);
		
	}

}
