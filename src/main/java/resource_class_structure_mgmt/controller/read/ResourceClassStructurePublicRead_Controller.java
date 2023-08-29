package resource_class_structure_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource_class_structure_mgmt.model.dto.ResourceClassStructure_DTO;
import resource_class_structure_mgmt.service.read.I_ResourceClassStructurePublicRead_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/resourceClassStructurePublicReadManagement")
public class ResourceClassStructurePublicRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ResourceClassStructure_Controller.class);

	@Autowired
	private I_ResourceClassStructurePublicRead_Service resourceClassStructurePublicReadServ;

	@GetMapping(value = "/getAllResourceClassStructures", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getAllResourceClassStructures() {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> completableFuture = resourceClassStructurePublicReadServ
				.getAllResourceClassStructures();
		CopyOnWriteArrayList<ResourceClassStructure_DTO> resourceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceClassDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectResourceClassStructures", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructures(
			@RequestBody CopyOnWriteArrayList<Long> ids, @RequestBody CopyOnWriteArrayList<Long> pids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> completableFuture = resourceClassStructurePublicReadServ
				.getSelectResourceClassStructures(ids, pids);
		CopyOnWriteArrayList<ResourceClassStructure_DTO> resourceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceClassDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectResourceClassStructuresByParents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructuresByParents(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> completableFuture = resourceClassStructurePublicReadServ
				.getSelectResourceClassStructuresByParents(ids);
		CopyOnWriteArrayList<ResourceClassStructure_DTO> resourceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceClassDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectResourceClassStructuresByParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructuresByParties(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> completableFuture = resourceClassStructurePublicReadServ
				.getSelectResourceClassStructuresByParties(ids);
		CopyOnWriteArrayList<ResourceClassStructure_DTO> resourceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceClassDTOs, HttpStatus.OK);
	}
}