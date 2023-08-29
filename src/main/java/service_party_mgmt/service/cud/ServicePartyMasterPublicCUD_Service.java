package service_party_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service_party_mgmt.model.dto.ServicePartyMaster_DTO;
import service_party_mgmt.model.master.ServicePartyMaster;
import service_party_mgmt.model.repo.cud.ServicePartyMasterPublicCUD_Repo;

@Service("serviceMasterPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServicePartyMasterPublicCUD_Service implements I_ServicePartyMasterPublicCUD_Service 
{

	@Autowired
	private ServicePartyMasterPublicCUD_Repo servicePartyMasterPublicCUDRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<ServicePartyMaster_DTO> newServicePartyMaster(ServicePartyMaster_DTO lMasters) 
	{
		CompletableFuture<ServicePartyMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
 		{
		ServicePartyMaster servicePartyMaster = servicePartyMasterPublicCUDRepo.save(this.setServicePartyMaster(lMasters));
		ServicePartyMaster_DTO lMasterss = getServicePartyMaster_DTO(servicePartyMaster);
		return lMasterss;
	}, asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> updServicePartyMaster(ServicePartyMaster_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		ServicePartyMaster serviceMaster = this.setServicePartyMaster(lMaster);
		if (servicePartyMasterPublicCUDRepo.existsById(lMaster.getServiceSeqNo())) 
				{		
			serviceMaster.setServiceSeqNo(lMaster.getServiceSeqNo());			
			servicePartyMasterPublicCUDRepo.save(serviceMaster);			
		}
		return;
 		}, asyncExecutor);
 			return future;
	}
	
	public CompletableFuture<Void> setServiceDur(Long id, Float qty) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		servicePartyMasterPublicCUDRepo.setServiceDur(id, qty);
		return;
 		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delAllServicePartyMasters() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		servicePartyMasterPublicCUDRepo.deleteAll();
		return;
 		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectServices(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		servicePartyMasterPublicCUDRepo.delSelectServices(ids);
		return;
 		}, asyncExecutor);
 		return future;
	}

	public CompletableFuture<Void> delSelectServicesByServiceIds(CopyOnWriteArrayList<String> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		servicePartyMasterPublicCUDRepo.delSelectServicesByServiceIds(ids);
		return;
 		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectServicesByPriceRange(Float fr, Float to) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		servicePartyMasterPublicCUDRepo.delSelectServicesByPriceRange(fr, to);
		return;
 		}, asyncExecutor);
 		return future;
	}

	public CompletableFuture<Void> delSelectServicePartiesByParties(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		servicePartyMasterPublicCUDRepo.delSelectServicePartiesByParties(ids);
		return;
 		}, asyncExecutor);
 		return future;
	}

	private ServicePartyMaster_DTO getServicePartyMaster_DTO(ServicePartyMaster lMaster) 
	{		
		ServicePartyMaster_DTO lDTO = new ServicePartyMaster_DTO();
		lDTO.setRemark(lMaster.getRemark());		
		lDTO.setUnitPrice(lMaster.getUnitPrice());
		lDTO.setUnitPriceSeqNo(lMaster.getUnitPriceSeqNo());
		lDTO.setDiscSeqNo(lMaster.getDiscSeqNo());
		lDTO.setDiscPer(lMaster.getDiscPer());
		lDTO.setDiscVal(lMaster.getDiscVal());
		lDTO.setTaxSeqNo(lMaster.getTaxSeqNo());
		lDTO.setTaxPer(lMaster.getTaxPer());
		lDTO.setTaxVal(lMaster.getTaxVal());
		lDTO.setDuration(lMaster.getDuration());
		lDTO.setSpecificationSeqNo(lMaster.getSpecificationSeqNo());		
		lDTO.setServiceId(lMaster.getServiceId());
		lDTO.setDurCodeSeqNo(lMaster.getDurCodeSeqNo());
		lDTO.setPartySeqNo(lMaster.getPartySeqNo());
		lDTO.setServiceSeqNo(lMaster.getServiceSeqNo());
		lDTO.setMasterServiceSeqNo(lMaster.getMasterServiceSeqNo());
		return lDTO;
	}

	private ServicePartyMaster setServicePartyMaster(ServicePartyMaster_DTO lDTO) 
	{
		ServicePartyMaster lMaster = new ServicePartyMaster();				
		lMaster.setRemark(lDTO.getRemark());		
		lMaster.setUnitPrice(lDTO.getUnitPrice());
		lMaster.setUnitPriceSeqNo(lDTO.getUnitPriceSeqNo());
		lMaster.setDiscSeqNo(lDTO.getDiscSeqNo());
		lMaster.setDiscPer(lDTO.getDiscPer());
		lMaster.setDiscVal(lDTO.getDiscVal());
		lMaster.setTaxSeqNo(lDTO.getTaxSeqNo());
		lMaster.setTaxPer(lDTO.getTaxPer());
		lMaster.setTaxVal(lDTO.getTaxVal());
		lMaster.setDuration(lDTO.getDuration());
		lMaster.setSpecificationSeqNo(lDTO.getSpecificationSeqNo());		
		lMaster.setServiceId(lDTO.getServiceId());
		lMaster.setDurCodeSeqNo(lDTO.getDurCodeSeqNo());
		lMaster.setPartySeqNo(lDTO.getPartySeqNo());
		lMaster.setMasterServiceSeqNo(lDTO.getMasterServiceSeqNo());
		return lMaster;
	}

}