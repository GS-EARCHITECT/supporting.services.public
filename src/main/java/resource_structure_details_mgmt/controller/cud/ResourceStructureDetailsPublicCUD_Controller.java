package resource_structure_details_mgmt.controller.cud;

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
import resource_structure_details_mgmt.model.dto.ResourceStructureDetail_DTO;
import resource_structure_details_mgmt.model.master.ResourceStructureDetailPK;
import resource_structure_details_mgmt.service.cud.I_ResourceStructureDetailsPublicCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/resourceStructureDetailsPublicCUDManagement")
public class ResourceStructureDetailsPublicCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ResourceStructureDetail_Controller.class);

	@Autowired
	private I_ResourceStructureDetailsPublicCUD_Service resourceStructureDetailsPublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<ResourceStructureDetail_DTO> newResourceClass(@RequestBody ResourceStructureDetail_DTO resourceClassDTO) 
	{
		CompletableFuture<ResourceStructureDetail_DTO> completableFuture = resourceStructureDetailsPublicCUDServ.newResourceStructureDetail(resourceClassDTO);
		ResourceStructureDetail_DTO resourceClassDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(resourceClassDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updResourceStructureDetail")
	public void updateResourceStructureDetail(@RequestBody ResourceStructureDetail_DTO resourceStructureDetailDTO) 
	{
		resourceStructureDetailsPublicCUDServ.updResourceStructureDetail(resourceStructureDetailDTO);	
		return;
	}
	
	@DeleteMapping("/delSelectResourceStructureDetails")
	public void deleteSelectresourceStructureDetails(@RequestBody CopyOnWriteArrayList<ResourceStructureDetailPK> ids)
	{
		resourceStructureDetailsPublicCUDServ.delSelectResourceStructureDetails(ids);
		return;
	}
	
	@DeleteMapping("/delSelectResourceStructureDetailsByResources")
	public void deleteSelectresourceStructureDetailsByResources(@RequestBody CopyOnWriteArrayList<Long> ids, @RequestBody CopyOnWriteArrayList<Long> pids)
	{
		resourceStructureDetailsPublicCUDServ.delSelectResourceStructureDetailsByResources(ids, pids);
		return;
	}
	
	@DeleteMapping("/delSelectResourceStructureDetailsByParents")
	public void deleteSelectresourceStructureDetailsByParents(@RequestBody CopyOnWriteArrayList<Long> ids)
	{
		resourceStructureDetailsPublicCUDServ.delSelectResourceStructureDetailsByParents(ids);
		return;
	}

	@DeleteMapping("/delAllresourceClass")
	public void deleteAllresourceClasss() {
		resourceStructureDetailsPublicCUDServ.delAllResourceStructureDetails();;
		return;
	}
	}