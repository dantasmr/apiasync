package com.example.apiasync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

	private static Logger log = LoggerFactory.getLogger(AsyncController.class);

	@Autowired
	private ApiasyncService apiasyncService;

	@RequestMapping(value = "/orquestrado", method = RequestMethod.POST)
	public String Getorquestrado() throws InterruptedException, ExecutionException 
	{
		log.info("testAsynch Start");

		CompletableFuture<String> contratos = apiasyncService.getContratos();
		CompletableFuture<String> certificados = apiasyncService.getCertificados();
		CompletableFuture<String> clientes = apiasyncService.getClientes();

		// Wait until they are all done
		CompletableFuture.allOf(contratos, certificados, clientes).join();
		
		log.info("Contratos--> " + contratos);
		log.info("Certificados--> " + certificados);
		log.info("Clientes--> " + clientes);
		
		return contratos.get() + "-" + certificados.get() + "-" + clientes.get();
	}
}
