package resource_party_mgmt.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource_party_mgmt.model.dto.ResourcePartyMaster_DTO;
import resource_party_mgmt.service.cud.I_ResourcePartyMasterPublicCUD_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/resourcePartyPublicCUDManagement")
public class ResourcePartyMasterPublicCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ResourcePartyMaster_Controller.class);

	@Autowired
	private I_ResourcePartyMasterPublicCUD_Service resourcePartyMasterPublicCUDServ;
	
	@PostMapping("/new")
	public ResponseEntity<ResourcePartyMaster_DTO> newresource(@RequestBody ResourcePartyMaster_DTO resourceDTO) 
	{
		CompletableFuture<ResourcePartyMaster_DTO> completableFuture = resourcePartyMasterPublicCUDServ.newResourcePartyMaster(resourceDTO);
		ResourcePartyMaster_DTO resourceDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(resourceDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updResourcePartyMaster")
	public void updResourcePartyMaster(@RequestBody ResourcePartyMaster_DTO resourceDTO) 
	{
		resourcePartyMasterPublicCUDServ.updResourcePartyMaster(resourceDTO);	
		return;
	}
	
	@PutMapping("/setResourceQoh/{id}/{qty}")
	public void setResourceQoh(@PathVariable Long id, @PathVariable Float qty) 
	{
		resourcePartyMasterPublicCUDServ.setResourceQoh(id, qty);	
		return;
	}
	
	@DeleteMapping("/delAllResourcePartyMasters")
	public void delAllResourcePartyMasters() 
	{
		resourcePartyMasterPublicCUDServ.delAllResourcePartyMasters();	
		return;
	}
	
	@DeleteMapping("/delSelectResources")
	public void delSelectResources(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		resourcePartyMasterPublicCUDServ.delSelectResources(ids);	
		return;
	}

	@DeleteMapping("/delSelectResourcesByResourceIds")
	public void delSelectResourcesByResourceIds(@RequestBody CopyOnWriteArrayList<String> ids) 
	{
		resourcePartyMasterPublicCUDServ.delSelectResourcesByResourceIds(ids);	
		return;
	}
	
	@DeleteMapping("/delSelectResourcesByPriceRange/{fr}/{to}")
	public void delSelectResourcesByPriceRange(@PathVariable Float fr, @PathVariable Float to) 
	{
		resourcePartyMasterPublicCUDServ.delSelectResourcesByPriceRange(fr,to);	
		return;
	}
	
	@DeleteMapping("/delSelectResourcePartiesByParties")
	public void delSelectResourcePartiesByParties(@RequestBody CopyOnWriteArrayList<Long> ids) 
	{
		resourcePartyMasterPublicCUDServ.delSelectResourcePartiesByParties(ids);	
		return;
	}
	
	}