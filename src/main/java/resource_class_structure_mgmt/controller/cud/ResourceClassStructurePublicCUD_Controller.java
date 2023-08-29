package resource_class_structure_mgmt.controller.cud;

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
import resource_class_structure_mgmt.model.dto.ResourceClassStructure_DTO;
import resource_class_structure_mgmt.service.cud.I_ResourceClassStructurePublicCUD_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/resourceClassStructurePublicCUDManagement")
public class ResourceClassStructurePublicCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ResourceClassStructure_Controller.class);

	@Autowired
	private I_ResourceClassStructurePublicCUD_Service resourceClassStructurePublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<ResourceClassStructure_DTO> newResourceClass(@RequestBody ResourceClassStructure_DTO resourceClassDTO) {
		
		CompletableFuture<ResourceClassStructure_DTO> completableFuture = resourceClassStructurePublicCUDServ.newResourceClassStructure(resourceClassDTO);
		ResourceClassStructure_DTO resourceClassDTO2 = completableFuture.join();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(resourceClassDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updResourceClassStructure")
	public void updateResourceClassStructure(@RequestBody ResourceClassStructure_DTO resourceClassStructureDTO) 
	{
		resourceClassStructurePublicCUDServ.updResourceClassStructure(resourceClassStructureDTO);	
		return;
	}

	
	@DeleteMapping("/delSelectResourceClassStrucures")
	public void deleteSelectresourceClassStrucures(@RequestBody CopyOnWriteArrayList<Long> ids, @RequestBody CopyOnWriteArrayList<Long> pids)
	{
		resourceClassStructurePublicCUDServ.delSelectResourceClassStructures(ids, pids);
		return;
	}
	
	@DeleteMapping("/delSelectResourceClassStrucuresByParties")
	public void deleteSelectresourceClassStrucuresByParties(@RequestBody CopyOnWriteArrayList<Long> ids)
	{
		resourceClassStructurePublicCUDServ.delSelectResourceClassStructuresByParties(ids);
		return;
	}
	
	@DeleteMapping("/delSelectResourceClassStrucuresByParents")
	public void deleteSelectresourceClassStrucuresByParents(@RequestBody CopyOnWriteArrayList<Long> ids)
	{
		resourceClassStructurePublicCUDServ.delSelectResourceClassStructuresByParents(ids);
		return;
	}

	@DeleteMapping("/delAllresourceClass")
	public void deleteAllresourceClasss() {
		resourceClassStructurePublicCUDServ.delAllResourceClassStructures();;
		return;
	}
	}