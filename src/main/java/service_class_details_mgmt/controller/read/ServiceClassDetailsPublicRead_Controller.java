package service_class_details_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_class_details_mgmt.model.dto.ServiceClassDetail_DTO;
import service_class_details_mgmt.model.master.ServiceClassDetailPK;
import service_class_details_mgmt.service.read.I_ServiceClassDetailsPublicRead_Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceClassDetailsPublicReadManagement")
public class ServiceClassDetailsPublicRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ServiceClassDetails_Controller.class);

	@Autowired
	private I_ServiceClassDetailsPublicRead_Service serviceClassDetailsPublicReadServ;
	
	@GetMapping(value = "/getAllServiceClasses", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getAllServiceClassDetails() {
		
		CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> completableFuture = serviceClassDetailsPublicReadServ.getAllServiceClassDetails();
		CopyOnWriteArrayList<ServiceClassDetail_DTO> serviceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceClassDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectServiceClasses", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getSelectServiceClassDetails(@RequestBody CopyOnWriteArrayList<ServiceClassDetailPK> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> completableFuture = serviceClassDetailsPublicReadServ.getSelectServiceClassDetails(ids);		
		CopyOnWriteArrayList<ServiceClassDetail_DTO> serviceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceClassDTOs, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/getSelectServicesForClasses", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getSelectServicesForClasses(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> completableFuture = serviceClassDetailsPublicReadServ.getSelectServicesForClasses(ids);		
		CopyOnWriteArrayList<ServiceClassDetail_DTO> serviceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceClassDTOs, HttpStatus.OK);
	}	
	
}