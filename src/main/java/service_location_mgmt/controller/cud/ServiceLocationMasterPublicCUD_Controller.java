package service_location_mgmt.controller.cud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_location_mgmt.service.cud.I_ServiceLocationMasterPublicCUD_Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceLocationPublicCUDManagement")
public class ServiceLocationMasterPublicCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(ServiceLocationMaster_Controller.class);

	@Autowired
	private I_ServiceLocationMasterPublicCUD_Service serviceLocationMasterPublicCUDServ;
	
	
	@PostMapping("/addPartyLocationServiceDur/{id}/{dur}/{loc}/{py}")
	public void addPartyLocationServiceDur(@PathVariable Long id, @PathVariable Float dur, @PathVariable Long loc, @PathVariable Long py) 
	{
		serviceLocationMasterPublicCUDServ.addPartyLocationServiceDur(id, dur, loc, py);
		return; 
	}
	
	@PostMapping("/subPartyLocationServiceDur/{id}/{dur}/{loc}/{py}")
	public void subPartyLocationServiceDur(@PathVariable Long id, @PathVariable Float dur, @PathVariable Long loc, @PathVariable Long py) 
	{
		serviceLocationMasterPublicCUDServ.subPartyLocationServiceDur(id, dur, loc, py);
		return; 
	}
	
	@PostMapping("/setPartyLocationServiceDur/{id}/{dur}/{loc}/{py}")
	public void setPartyLocationServiceDur(@PathVariable Long id, @PathVariable Float dur, @PathVariable Long loc, @PathVariable Long py) 
	{
		serviceLocationMasterPublicCUDServ.setPartyLocationServiceDur(id, dur, loc, py);
		return; 
	}
	}