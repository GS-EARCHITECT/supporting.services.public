package resource_party_mgmt.controller.cud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource_party_mgmt.model.dto.ResourcePartyMaster_DTO;
import resource_party_mgmt.service.read.I_ResourcePartyMasterPublicRead_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/resourcePartyPublicReadManagement")
public class ResourcePartyMasterPublicRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ResourcePartyMaster_Controller.class);

	@Autowired
	private I_ResourcePartyMasterPublicRead_Service resourcePartyMasterPublicReadServ;
	
	@GetMapping(value = "/getAllResourcePartyMasters", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getAllResourcePartyMasters() 
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> completableFuture = resourcePartyMasterPublicReadServ.getAllResourcePartyMasters();
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> resourceDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectResources", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcePartyMasters(@RequestBody CopyOnWriteArrayList<Long> resourceSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> completableFuture = resourcePartyMasterPublicReadServ.getSelectResources(resourceSeqNos);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> resourceDTOs = completableFuture.join();
		return new ResponseEntity<>(resourceDTOs, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/getSelectResourcesByResourceIds", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByResourceIds(@RequestBody CopyOnWriteArrayList<String> resourceIds)
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> completableFuture = resourcePartyMasterPublicReadServ.getSelectResourcesByResourceIds(resourceIds);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> resourceDTOs = completableFuture.join();		
		return new ResponseEntity<>(resourceDTOs, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/getSelectResourcesByParties", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByParties(@RequestBody CopyOnWriteArrayList<Long> cos) 
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> completableFuture = resourcePartyMasterPublicReadServ.getSelectResourcesByParties(cos);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> resourceDTOs = completableFuture.join();	
		return new ResponseEntity<>(resourceDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectResourcesByMasters", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByMasters(@RequestBody CopyOnWriteArrayList<Long> cos) 
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> completableFuture = resourcePartyMasterPublicReadServ.getSelectResourcesByMasters(cos);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> resourceDTOs = completableFuture.join();	
		return new ResponseEntity<>(resourceDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectResourcesByPriceRange/{fr}/{to}", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcePartyMastersByPriceRange(@PathVariable Float fr, @PathVariable Float to) {
		
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> completableFuture = resourcePartyMasterPublicReadServ.getSelectResourcesByPriceRange(fr, to);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> resourceDTOs = completableFuture.join();		
		return new ResponseEntity<>(resourceDTOs, HttpStatus.OK);
	}
	

	}