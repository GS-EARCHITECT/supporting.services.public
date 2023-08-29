package service_class_details_mgmt.controller.cud;

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

import service_class_details_mgmt.model.dto.ServiceClassDetail_DTO;
import service_class_details_mgmt.model.master.ServiceClassDetailPK;
import service_class_details_mgmt.service.cud.I_ServiceClassDetailsPublicCUD_Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceClassDetailsPublicCUDManagement")
public class ServiceClassDetailsPublicCUD_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ServiceClassDetails_Controller.class);

	@Autowired
	private I_ServiceClassDetailsPublicCUD_Service serviceClassDetailsPublicCUDServ;

	@PostMapping("/new")
	public ResponseEntity<ServiceClassDetail_DTO> newserviceClass(
			@RequestBody ServiceClassDetail_DTO serviceClassDTO) {
		CompletableFuture<ServiceClassDetail_DTO> completableFuture = serviceClassDetailsPublicCUDServ
				.newServiceClassDetail(serviceClassDTO);
		ServiceClassDetail_DTO serviceClassDTO2 = completableFuture.join();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(serviceClassDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updServiceClassDetail")
	public void updateServiceClassDetail(@RequestBody ServiceClassDetail_DTO serviceClassDetailsDTO) {
		serviceClassDetailsPublicCUDServ.updServiceClassDetail(serviceClassDetailsDTO);
		return;
	}

	@DeleteMapping("/delSelectServiceClassDetails")
	public void deleteSelectserviceClassDetails(@RequestBody CopyOnWriteArrayList<ServiceClassDetailPK> ids) {
		serviceClassDetailsPublicCUDServ.delSelectServiceClassDetails(ids);
		return;
	}

	@DeleteMapping("/delAllServiceClassDetails")
	public void deleteAllserviceClassDetails() {
		serviceClassDetailsPublicCUDServ.delAllServiceClassDetails();
		;
		return;
	}
}