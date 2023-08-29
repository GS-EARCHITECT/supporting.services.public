package service_location_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service_location_mgmt.model.master.ServiceLocationMasterPK;
import service_location_mgmt.model.repo.cud.ServiceLocationMasterPublicCUD_Repo;

@Service("serviceLocationMasterPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceLocationMasterPublicCUD_Service implements I_ServiceLocationMasterPublicCUD_Service 
{

	@Autowired
	private ServiceLocationMasterPublicCUD_Repo serviceLocationMasterPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
     public	CompletableFuture<Void> addPartyLocationServiceDur(Long id, Float dur, Long loc, Long py)
     {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
    	ServiceLocationMasterPK serviceLocationMasterPK= new ServiceLocationMasterPK();
 		serviceLocationMasterPK.setLocationSeqNo(loc);
 		serviceLocationMasterPK.setServiceSeqNo(id);
 		serviceLocationMasterPK.setPartySeqNo(py);
 		
 		if(serviceLocationMasterPublicCUDRepo.existsById(serviceLocationMasterPK))
 		{
 		serviceLocationMasterPublicCUDRepo.addServiceDur(id, dur, loc, py);	
 		}
 		return;
		}, asyncExecutor);
 		return future;
     }
    
     public	CompletableFuture<Void> subPartyLocationServiceDur(Long id, Float dur, Long loc, Long py)
     {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
    	ServiceLocationMasterPK serviceLocationMasterPK= new ServiceLocationMasterPK();
 		serviceLocationMasterPK.setLocationSeqNo(loc);
 		serviceLocationMasterPK.setServiceSeqNo(id);
 		serviceLocationMasterPK.setPartySeqNo(py);
 		
 		if(serviceLocationMasterPublicCUDRepo.existsById(serviceLocationMasterPK))
 		{
 		serviceLocationMasterPublicCUDRepo.subServiceDur(id, dur, loc, py);	
 		}
 		return;
		}, asyncExecutor);
 		return future;
     }
     
     public	CompletableFuture<Void> setPartyLocationServiceDur(Long id, Float dur, Long loc, Long py)
     {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
    	ServiceLocationMasterPK serviceLocationMasterPK= new ServiceLocationMasterPK();
 		serviceLocationMasterPK.setLocationSeqNo(loc);
 		serviceLocationMasterPK.setServiceSeqNo(id);
 		serviceLocationMasterPK.setPartySeqNo(py);
 		
 		if(serviceLocationMasterPublicCUDRepo.existsById(serviceLocationMasterPK))
 		{
 		serviceLocationMasterPublicCUDRepo.setServiceDur(id, dur, loc, py);	
 		}
 		return;
		}, asyncExecutor);
 		return future;
     }

}