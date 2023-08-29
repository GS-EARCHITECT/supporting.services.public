package resource_class_details_mgmt.controller.cud;

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
import resource_class_details_mgmt.model.dto.ResourceClassDetail_DTO;
import resource_class_details_mgmt.model.master.ResourceClassDetailPK;
import resource_class_details_mgmt.service.cud.I_ResourceClassDetailsPublicCUD_Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/resourceClassDetailsPublicCUDManagement")
public class ResourceClassDetailsPublicCUD_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ResourceClassDetails_Controller.class);

	@Autowired
	private I_ResourceClassDetailsPublicCUD_Service resourceClassDetailsPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<ResourceClassDetail_DTO> newresourceClass(
			@RequestBody ResourceClassDetail_DTO resourceClassDTO) {
		CompletableFuture<ResourceClassDetail_DTO> completableFuture = resourceClassDetailsPublicCUDServ
				.newResourceClassDetail(resourceClassDTO);
		ResourceClassDetail_DTO resourceClassDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(resourceClassDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updResourceClassDetail")
	public void updateResourceClassDetail(@RequestBody ResourceClassDetail_DTO resourceClassDetailsDTO) {
		resourceClassDetailsPublicCUDServ.updResourceClassDetail(resourceClassDetailsDTO);
		return;
	}

	@DeleteMapping("/delSelectResourceClassDetails")
	public void deleteSelectresourceClassDetails(@RequestBody CopyOnWriteArrayList<ResourceClassDetailPK> ids) {
		resourceClassDetailsPublicCUDServ.delSelectResourceClassDetails(ids);
		return;
	}

	@DeleteMapping("/delAllResourceClassDetails")
	public void deleteAllresourceClassDetails() {
		resourceClassDetailsPublicCUDServ.delAllResourceClassDetails();
		;
		return;
	}
}