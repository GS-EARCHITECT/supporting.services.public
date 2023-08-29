package service_class_structure_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_class_structure_mgmt.model.dto.ServiceClassStructure_DTO;
import service_class_structure_mgmt.service.read.I_ServiceClassStructurePublicRead_Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceClassStructurePublicReadManagement")
public class ServiceClassStructurePublicRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ServiceClassStructure_Controller.class);

	@Autowired
	private I_ServiceClassStructurePublicRead_Service serviceClassStructurePublicReadServ;

	@GetMapping(value = "/getAllServiceClassStructures", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getAllServiceClassStructures() {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> completableFuture = serviceClassStructurePublicReadServ
				.getAllServiceClassStructures();
		CopyOnWriteArrayList<ServiceClassStructure_DTO> serviceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceClassDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectServiceClassStructures", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructures(
			@RequestBody CopyOnWriteArrayList<Long> ids, @RequestBody CopyOnWriteArrayList<Long> pids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> completableFuture = serviceClassStructurePublicReadServ
				.getSelectServiceClassStructures(ids, pids);
		CopyOnWriteArrayList<ServiceClassStructure_DTO> serviceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceClassDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectServiceClassStructuresByParents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructuresByParents(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> completableFuture = serviceClassStructurePublicReadServ
				.getSelectServiceClassStructuresByParents(ids);
		CopyOnWriteArrayList<ServiceClassStructure_DTO> serviceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceClassDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectServiceClassStructuresByParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructuresByParties(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> completableFuture = serviceClassStructurePublicReadServ
				.getSelectServiceClassStructuresByParties(ids);
		CopyOnWriteArrayList<ServiceClassStructure_DTO> serviceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(serviceClassDTOs, HttpStatus.OK);
	}
}