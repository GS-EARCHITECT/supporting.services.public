package service_party_mgmt.controller.cud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_party_mgmt.model.dto.ServicePartyMaster_DTO;
import service_party_mgmt.service.read.I_ServicePartyMasterPublicRead_Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/servicePartyPublicReadManagement")
public class ServicePartyMasterPublicRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ServicePartyMaster_Controller.class);

	@Autowired
	private I_ServicePartyMasterPublicRead_Service servicePartyMasterPublicReadServ;
	
	@GetMapping(value = "/getAllServicePartyMasters", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getAllServicePartyMasters() 
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> completableFuture = servicePartyMasterPublicReadServ.getAllServicePartyMasters();
		CopyOnWriteArrayList<ServicePartyMaster_DTO> serviceDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectServices", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicePartyMasters(@RequestBody CopyOnWriteArrayList<Long> serviceSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> completableFuture = servicePartyMasterPublicReadServ.getSelectServices(serviceSeqNos);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> serviceDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/getSelectServicesByServiceIds", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByServiceIds(@RequestBody CopyOnWriteArrayList<String> serviceIds)
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> completableFuture = servicePartyMasterPublicReadServ.getSelectServicesByServiceIds(serviceIds);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> serviceDTOs = completableFuture.join();		
		return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/getSelectServicesByParties", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByParties(@RequestBody CopyOnWriteArrayList<Long> cos) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> completableFuture = servicePartyMasterPublicReadServ.getSelectServicesByParties(cos);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> serviceDTOs = completableFuture.join();	
		return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectServicesByMasters", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByMasters(@RequestBody CopyOnWriteArrayList<Long> cos) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> completableFuture = servicePartyMasterPublicReadServ.getSelectServicesByMasters(cos);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> serviceDTOs = completableFuture.join();	
		return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectServicesByPriceRange/{fr}/{to}", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicePartyMastersByPriceRange(@PathVariable Float fr, @PathVariable Float to) {
		
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> completableFuture = servicePartyMasterPublicReadServ.getSelectServicesByPriceRange(fr, to);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> serviceDTOs = completableFuture.join();		
		return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
	}
	

	}