package service_structure_details_mgmt.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
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

import service_structure_details_mgmt.model.dto.ServiceStructureDetail_DTO;
import service_structure_details_mgmt.model.master.ServiceStructureDetailPK;
import service_structure_details_mgmt.service.cud.I_ServiceStructureDetailsPublicCUD_Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceStructureDetailsPublicCUDManagement")
public class ServiceStructureDetailsPublicCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ServiceStructureDetail_Controller.class);

	@Autowired
	private I_ServiceStructureDetailsPublicCUD_Service serviceStructureDetailsPublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<ServiceStructureDetail_DTO> newServiceClass(@RequestBody ServiceStructureDetail_DTO serviceClassDTO) 
	{
		CompletableFuture<ServiceStructureDetail_DTO> completableFuture = serviceStructureDetailsPublicCUDServ.newServiceStructureDetail(serviceClassDTO);
		ServiceStructureDetail_DTO serviceClassDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(serviceClassDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updServiceStructureDetail")
	public void updateServiceStructureDetail(@RequestBody ServiceStructureDetail_DTO serviceStructureDetailDTO) 
	{
		serviceStructureDetailsPublicCUDServ.updServiceStructureDetail(serviceStructureDetailDTO);	
		return;
	}
	
	@DeleteMapping("/delSelectServiceStructureDetails")
	public void deleteSelectserviceStructureDetails(@RequestBody CopyOnWriteArrayList<ServiceStructureDetailPK> ids)
	{
		serviceStructureDetailsPublicCUDServ.delSelectServiceStructureDetails(ids);
		return;
	}
	
	@DeleteMapping("/delSelectServiceStructureDetailsByServices")
	public void deleteSelectserviceStructureDetailsByServices(@RequestBody CopyOnWriteArrayList<Long> ids, @RequestBody CopyOnWriteArrayList<Long> pids)
	{
		serviceStructureDetailsPublicCUDServ.delSelectServiceStructureDetailsByServices(ids, pids);
		return;
	}
	
	@DeleteMapping("/delSelectServiceStructureDetailsByParents")
	public void deleteSelectserviceStructureDetailsByParents(@RequestBody CopyOnWriteArrayList<Long> ids)
	{
		serviceStructureDetailsPublicCUDServ.delSelectServiceStructureDetailsByParents(ids);
		return;
	}

	@DeleteMapping("/delAllserviceClass")
	public void deleteAllserviceClasss() {
		serviceStructureDetailsPublicCUDServ.delAllServiceStructureDetails();;
		return;
	}
	}