package service_structure_details_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_structure_details_mgmt.model.dto.ServiceStructureDetail_DTO;
import service_structure_details_mgmt.model.master.ServiceStructureDetailPK;
import service_structure_details_mgmt.service.read.I_ServiceStructureDetailsPublicRead_Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceStructureDetailsPublicReadManagement")
public class ServiceStructureDetailsPublicRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ServiceStructureDetail_Controller.class);

	@Autowired
	private I_ServiceStructureDetailsPublicRead_Service serviceStructureDetailsPublicReadServ;

	@GetMapping(value = "/getAllServiceStructureDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getAllServiceStructureDetails() {
		CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> completableFuture = serviceStructureDetailsPublicReadServ
				.getAllServiceStructureDetails();
		CopyOnWriteArrayList<ServiceStructureDetail_DTO> serviceStructureDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceStructureDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectServiceStructureDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetails(
			@RequestBody CopyOnWriteArrayList<ServiceStructureDetailPK> ids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> completableFuture = serviceStructureDetailsPublicReadServ
				.getSelectServiceStructureDetails(ids);
		CopyOnWriteArrayList<ServiceStructureDetail_DTO> serviceStructureDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceStructureDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectServiceStructureDetailsByParents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetailsByParents(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> completableFuture = serviceStructureDetailsPublicReadServ
				.getSelectServiceStructureDetailsByParents(ids);
		CopyOnWriteArrayList<ServiceStructureDetail_DTO> serviceStructureDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceStructureDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectServiceStructureDetailsByServices", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetailsByServices(
			@RequestBody CopyOnWriteArrayList<Long> ids, @RequestBody CopyOnWriteArrayList<Long> cids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> completableFuture = serviceStructureDetailsPublicReadServ
				.getSelectServiceStructureDetailsByServices(ids, cids);
		CopyOnWriteArrayList<ServiceStructureDetail_DTO> serviceStructureDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceStructureDTOs, HttpStatus.OK);
	}

}