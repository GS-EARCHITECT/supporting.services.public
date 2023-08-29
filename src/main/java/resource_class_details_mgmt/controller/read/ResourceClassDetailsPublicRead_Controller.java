package resource_class_details_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource_class_details_mgmt.model.dto.ResourceClassDetail_DTO;
import resource_class_details_mgmt.model.master.ResourceClassDetailPK;
import resource_class_details_mgmt.service.read.I_ResourceClassDetailsPublicRead_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/resourceClassDetailsPublicReadManagement")
public class ResourceClassDetailsPublicRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ResourceClassDetails_Controller.class);

	@Autowired
	private I_ResourceClassDetailsPublicRead_Service resourceClassDetailsPublicReadServ;
	
	@GetMapping(value = "/getAllResourceClasses", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getAllResourceClassDetails() {
		
		CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> completableFuture = resourceClassDetailsPublicReadServ.getAllResourceClassDetails();
		CopyOnWriteArrayList<ResourceClassDetail_DTO> resourceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceClassDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectResourceClasses", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getSelectResourceClassDetails(@RequestBody CopyOnWriteArrayList<ResourceClassDetailPK> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> completableFuture = resourceClassDetailsPublicReadServ.getSelectResourceClassDetails(ids);		
		CopyOnWriteArrayList<ResourceClassDetail_DTO> resourceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceClassDTOs, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/getSelectResourcesForClasses", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getSelectResourcesForClasses(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> completableFuture = resourceClassDetailsPublicReadServ.getSelectResourcesForClasses(ids);		
		CopyOnWriteArrayList<ResourceClassDetail_DTO> resourceClassDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceClassDTOs, HttpStatus.OK);
	}	
	
}