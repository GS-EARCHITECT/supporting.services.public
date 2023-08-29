package service_location_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_location_mgmt.model.dto.ServiceLocationMaster_DTO;
import service_location_mgmt.service.read.I_ServiceLocationMasterPublicRead_Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceLocationPublicReadManagement")
public class ServiceLocationMasterPublicRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ServiceLocationMaster_Controller.class);

	@Autowired
	private I_ServiceLocationMasterPublicRead_Service serviceLocationMasterPublicReadService;

	@GetMapping(value = "/getAllPartyLocationServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getAllPartyLocationServices() 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = null;
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> serviceLocationMaster_DTOs = null;
		try {
			future = serviceLocationMasterPublicReadService.getAllPartyLocationServices();
			serviceLocationMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(serviceLocationMaster_DTOs, HttpStatus.OK);

	}

	
	@GetMapping(value = "/getSelectServicesPartyLocations", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServicesPartyLocations(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = null;
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> serviceLocationMaster_DTOs = null;
		try {
			future = serviceLocationMasterPublicReadService.getSelectServices(ids);
			serviceLocationMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(serviceLocationMaster_DTOs, HttpStatus.OK);

	}
	
	@GetMapping(value = "/getSelectServicesByLocations", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServicesByLocations(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = null;
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> serviceLocationMaster_DTOs = null;
		try {
			future = serviceLocationMasterPublicReadService.getSelectServicesByLocations(ids);
			serviceLocationMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(serviceLocationMaster_DTOs, HttpStatus.OK);

	}

	@GetMapping(value = "/getSelectServicesByParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServicesByParties(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = null;
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> serviceLocationMaster_DTOs = null;
		try {
			future = serviceLocationMasterPublicReadService.getSelectServicesByParties(ids);
			serviceLocationMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(serviceLocationMaster_DTOs, HttpStatus.OK);

	}

	@GetMapping(value = "/getSelectServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServices(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = null;
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> serviceLocationMaster_DTOs = null;
		try {
			future = serviceLocationMasterPublicReadService.getSelectServices(ids);
			serviceLocationMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(serviceLocationMaster_DTOs, HttpStatus.OK);

	}

	@GetMapping(value = "/getServiceDur/{cid}/{pid}/{lid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Float> getServiceDur(@PathVariable Long cid, @PathVariable Long pid, @PathVariable Long lid) 
	{
		CompletableFuture<Float> future = null;
		Float result = null;
		try {
			future = serviceLocationMasterPublicReadService.getServiceDur(cid, pid, lid);					
			result = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}