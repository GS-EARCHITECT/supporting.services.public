package service_party_mgmt.service.read;

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
import service_party_mgmt.model.repo.read.ServicePartyMasterPublicRead_Repo;

@Service("servicePartyMasterPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServicePartyMasterPublicRead_Service implements I_ServicePartyMasterPublicRead_Service 
{

	@Autowired
	private ServicePartyMasterPublicRead_Repo servicePartyMasterPublicReadRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getAllServicePartyMasters() 
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServicePartyMaster> serviceList = (CopyOnWriteArrayList<ServicePartyMaster>) servicePartyMasterPublicReadRepo.getAllPartyServices();
		CopyOnWriteArrayList<ServicePartyMaster_DTO> lMasterss = serviceList != null ? this.getServicePartyMaster_DTOs(serviceList) : null;
		return lMasterss;
	}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByServiceIds(CopyOnWriteArrayList<String> serviceIds)
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServicePartyMaster> lMasters = servicePartyMasterPublicReadRepo.getSelectServicesByServiceIds(serviceIds);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> lMasterss = lMasters != null ? this.getServicePartyMaster_DTOs(lMasters) : null;
		return lMasterss;
 		}, asyncExecutor);
 		return future;
 	}
	
	public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByParties(CopyOnWriteArrayList<Long> ids)
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServicePartyMaster> lMasters = servicePartyMasterPublicReadRepo.getSelectServicePartiesByParties(ids);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> lMasterss = lMasters != null ? this.getServicePartyMaster_DTOs(lMasters) : null;
		return lMasterss;
 		}, asyncExecutor);
 		return future;
 	}

	public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByMasters(CopyOnWriteArrayList<Long> ids)
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServicePartyMaster> lMasters = servicePartyMasterPublicReadRepo.getSelectServicesbyMasters(ids);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> lMasterss = lMasters != null ? this.getServicePartyMaster_DTOs(lMasters) : null;
		return lMasterss;
 		}, asyncExecutor);
 		return future;	
 	}
	
	public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServices(CopyOnWriteArrayList<Long> ids)
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServicePartyMaster> lMasters = servicePartyMasterPublicReadRepo.getSelectServices(ids);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> lMasterss = lMasters != null ? this.getServicePartyMaster_DTOs(lMasters) : null;
		return lMasterss;
 		}, asyncExecutor);
 		return future;
 	}
   
	public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>>  getSelectServicesByPriceRange(Float fr, Float to)
	{
		CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServicePartyMaster> lMasters = servicePartyMasterPublicReadRepo.getSelectServicesbyPriceRange(fr, to);
		CopyOnWriteArrayList<ServicePartyMaster_DTO> lMasterss = lMasters != null ? this.getServicePartyMaster_DTOs(lMasters) : null;
		return lMasterss; 	
 		}, asyncExecutor);
 		return future;
 	}
	
	public CompletableFuture<Float>  getServiceDur(Long id)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
 		{
		Float qoh = servicePartyMasterPublicReadRepo.getServiceDur(id);
		return qoh; 	
 		}, asyncExecutor);
 		return future;
 	}
	
	private synchronized CopyOnWriteArrayList<ServicePartyMaster_DTO> getServicePartyMaster_DTOs(CopyOnWriteArrayList<ServicePartyMaster> lMasters) {
		ServicePartyMaster_DTO lDTO = null;
		CopyOnWriteArrayList<ServicePartyMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<ServicePartyMaster_DTO>();		
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getServicePartyMaster_DTO(lMasters.get(i));			
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized ServicePartyMaster_DTO getServicePartyMaster_DTO(ServicePartyMaster lMaster) 
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
	
}