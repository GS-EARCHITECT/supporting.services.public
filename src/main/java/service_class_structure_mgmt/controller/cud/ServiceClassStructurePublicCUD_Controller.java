package service_class_structure_mgmt.controller.cud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_class_structure_mgmt.model.dto.ServiceClassStructure_DTO;
import service_class_structure_mgmt.service.cud.I_ServiceClassStructurePublicCUD_Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceClassStructurePublicCUDManagement")
public class ServiceClassStructurePublicCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ServiceClassStructure_Controller.class);

	@Autowired
	private I_ServiceClassStructurePublicCUD_Service serviceClassStructurePublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<ServiceClassStructure_DTO> newServiceClass(@RequestBody ServiceClassStructure_DTO serviceClassDTO) {
		
		CompletableFuture<ServiceClassStructure_DTO> completableFuture = serviceClassStructurePublicCUDServ.newServiceClassStructure(serviceClassDTO);
		ServiceClassStructure_DTO serviceClassDTO2 = completableFuture.join();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(serviceClassDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updServiceClassStructure")
	public void updateServiceClassStructure(@RequestBody ServiceClassStructure_DTO serviceClassStructureDTO) 
	{
		serviceClassStructurePublicCUDServ.updServiceClassStructure(serviceClassStructureDTO);	
		return;
	}

	
	@DeleteMapping("/delSelectServiceClassStrucures")
	public void deleteSelectserviceClassStrucures(@RequestBody CopyOnWriteArrayList<Long> ids, @RequestBody CopyOnWriteArrayList<Long> pids)
	{
		serviceClassStructurePublicCUDServ.delSelectServiceClassStructures(ids, pids);
		return;
	}
	
	@DeleteMapping("/delSelectServiceClassStrucuresByParties")
	public void deleteSelectserviceClassStrucuresByParties(@RequestBody CopyOnWriteArrayList<Long> ids)
	{
		serviceClassStructurePublicCUDServ.delSelectServiceClassStructuresByParties(ids);
		return;
	}
	
	@DeleteMapping("/delSelectServiceClassStrucuresByParents")
	public void deleteSelectserviceClassStrucuresByParents(@RequestBody CopyOnWriteArrayList<Long> ids)
	{
		serviceClassStructurePublicCUDServ.delSelectServiceClassStructuresByParents(ids);
		return;
	}

	@DeleteMapping("/delAllserviceClass")
	public void deleteAllserviceClasss() {
		serviceClassStructurePublicCUDServ.delAllServiceClassStructures();;
		return;
	}
	}