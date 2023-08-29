package service_party_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_party_mgmt.model.dto.ServicePartyMaster_DTO;
import service_party_mgmt.service.cud.I_ServicePartyMasterPublicCUD_Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/servicePartyPublicCUDManagement")
public class ServicePartyMasterPublicCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ServicePartyMaster_Controller.class);

	@Autowired
	private I_ServicePartyMasterPublicCUD_Service servicePartyMasterPublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<ServicePartyMaster_DTO> newservice(@RequestBody ServicePartyMaster_DTO serviceDTO) 
	{
		CompletableFuture<ServicePartyMaster_DTO> completableFuture = servicePartyMasterPublicCUDServ.newServicePartyMaster(serviceDTO);
		ServicePartyMaster_DTO serviceDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(serviceDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updServicePartyMaster")
	public void updServicePartyMaster(@RequestBody ServicePartyMaster_DTO serviceDTO) 
	{
		servicePartyMasterPublicCUDServ.updServicePartyMaster(serviceDTO);	
		return;
	}
	
	@PutMapping("/setServiceDur/{id}/{dur}")
	public void setServiceDur(@PathVariable Long id, @PathVariable Float dur) 
	{
		servicePartyMasterPublicCUDServ.setServiceDur(id, dur);	
		return;
	}
	
	@DeleteMapping("/delAllServicePartyMasters")
	public void delAllServicePartyMasters() 
	{
		servicePartyMasterPublicCUDServ.delAllServicePartyMasters();	
		return;
	}
	
	@DeleteMapping("/delSelectServices")
	public void delSelectServices(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		servicePartyMasterPublicCUDServ.delSelectServices(ids);	
		return;
	}

	@DeleteMapping("/delSelectServicesByServiceIds")
	public void delSelectServicesByServiceIds(@RequestBody CopyOnWriteArrayList<String> ids) 
	{
		servicePartyMasterPublicCUDServ.delSelectServicesByServiceIds(ids);	
		return;
	}
	
	@DeleteMapping("/delSelectServicesByPriceRange/{fr}/{to}")
	public void delSelectServicesByPriceRange(@PathVariable Float fr, @PathVariable Float to) 
	{
		servicePartyMasterPublicCUDServ.delSelectServicesByPriceRange(fr,to);	
		return;
	}
	
	@DeleteMapping("/delSelectServicePartiesByParties")
	public void delSelectServicePartiesByParties(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		servicePartyMasterPublicCUDServ.delSelectServicePartiesByParties(ids);	
		return;
	}
	
	}