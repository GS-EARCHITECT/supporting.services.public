package resource_structure_details_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource_structure_details_mgmt.model.dto.ResourceStructureDetail_DTO;
import resource_structure_details_mgmt.model.master.ResourceStructureDetailPK;
import resource_structure_details_mgmt.service.read.I_ResourceStructureDetailsPublicRead_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/resourceStructureDetailsPublicReadManagement")
public class ResourceStructureDetailsPublicRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ResourceStructureDetail_Controller.class);

	@Autowired
	private I_ResourceStructureDetailsPublicRead_Service resourceStructureDetailsPublicReadServ;

	@GetMapping(value = "/getAllResourceStructureDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getAllResourceStructureDetails() {
		CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> completableFuture = resourceStructureDetailsPublicReadServ
				.getAllResourceStructureDetails();
		CopyOnWriteArrayList<ResourceStructureDetail_DTO> resourceStructureDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceStructureDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectResourceStructureDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetails(
			@RequestBody CopyOnWriteArrayList<ResourceStructureDetailPK> ids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> completableFuture = resourceStructureDetailsPublicReadServ
				.getSelectResourceStructureDetails(ids);
		CopyOnWriteArrayList<ResourceStructureDetail_DTO> resourceStructureDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceStructureDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectResourceStructureDetailsByParents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetailsByParents(
			@RequestBody CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> completableFuture = resourceStructureDetailsPublicReadServ
				.getSelectResourceStructureDetailsByParents(ids);
		CopyOnWriteArrayList<ResourceStructureDetail_DTO> resourceStructureDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceStructureDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectResourceStructureDetailsByResources", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetailsByResources(
			@RequestBody CopyOnWriteArrayList<Long> ids, @RequestBody CopyOnWriteArrayList<Long> cids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> completableFuture = resourceStructureDetailsPublicReadServ
				.getSelectResourceStructureDetailsByResources(ids, cids);
		CopyOnWriteArrayList<ResourceStructureDetail_DTO> resourceStructureDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceStructureDTOs, HttpStatus.OK);
	}

}