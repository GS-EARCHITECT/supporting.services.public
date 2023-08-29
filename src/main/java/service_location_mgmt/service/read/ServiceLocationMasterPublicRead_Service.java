package service_location_mgmt.service.read;

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

import service_location_mgmt.model.dto.ServiceLocationMaster_DTO;
import service_location_mgmt.model.master.ServiceLocationMaster;
import service_location_mgmt.model.repo.read.ServiceLocationMasterPublicRead_Repo;

@Service("serviceLocationMasterPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceLocationMasterPublicRead_Service implements I_ServiceLocationMasterPublicRead_Service 
{

	@Autowired
	private ServiceLocationMasterPublicRead_Repo serviceLocationMasterPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getAllPartyLocationServices() 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceLocationMaster> serviceList = (CopyOnWriteArrayList<ServiceLocationMaster>) serviceLocationMasterPublicReadRepo.getAllServicePartyLocations();
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> lMasters = new CopyOnWriteArrayList<ServiceLocationMaster_DTO>();
		lMasters = serviceList != null ? this.getServiceLocationMaster_DTOs(serviceList) : null;
		return lMasters;
		}, asyncExecutor);
 		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServices(CopyOnWriteArrayList<Long> resSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceLocationMaster> lMasters = (CopyOnWriteArrayList<ServiceLocationMaster>) serviceLocationMasterPublicReadRepo.getSelectServices(resSeqNos);
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> serviceLocationMaster_DTOs = new CopyOnWriteArrayList<ServiceLocationMaster_DTO>();
		serviceLocationMaster_DTOs = serviceLocationMaster_DTOs != null ? this.getServiceLocationMaster_DTOs(lMasters) : null;
		return serviceLocationMaster_DTOs;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServicesByLocations( CopyOnWriteArrayList<Long> lidss)
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceLocationMaster> lMasters = (CopyOnWriteArrayList<ServiceLocationMaster>) serviceLocationMasterPublicReadRepo.getSelectServicesByLocations(lidss);
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> serviceLocationMaster_DTOs = new CopyOnWriteArrayList<ServiceLocationMaster_DTO>();
		serviceLocationMaster_DTOs = serviceLocationMaster_DTOs != null ? this.getServiceLocationMaster_DTOs(lMasters) : null;
		return serviceLocationMaster_DTOs;
		}, asyncExecutor);
 		return future;
	}
   
	public CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServicesByParties(CopyOnWriteArrayList<Long> pids)
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceLocationMaster> lMasters = (CopyOnWriteArrayList<ServiceLocationMaster>) serviceLocationMasterPublicReadRepo.getSelectServicesByParties(pids);
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> serviceLocationMaster_DTOs = new CopyOnWriteArrayList<ServiceLocationMaster_DTO>();
		serviceLocationMaster_DTOs = serviceLocationMaster_DTOs != null ? this.getServiceLocationMaster_DTOs(lMasters) : null;
		return serviceLocationMaster_DTOs;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Float> getServiceDur(Long rid, Long pid, Long lid)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
 		{
 		Float result = 	serviceLocationMasterPublicReadRepo.getServiceDur(rid, pid, lid);
		return result;
		}, asyncExecutor);
 		return future;
	}
     
	
	private synchronized CopyOnWriteArrayList<ServiceLocationMaster_DTO> getServiceLocationMaster_DTOs(CopyOnWriteArrayList<ServiceLocationMaster> lMaster) {
		ServiceLocationMaster_DTO lDTO = null;
		CopyOnWriteArrayList<ServiceLocationMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<ServiceLocationMaster_DTO>();		
		for (int i = 0; i < lMaster.size(); i++) {
			lDTO = getServiceLocationMaster_DTO(lMaster.get(i));			
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized ServiceLocationMaster_DTO getServiceLocationMaster_DTO(ServiceLocationMaster lMaster) 
	{		
		ServiceLocationMaster_DTO lDTO = new ServiceLocationMaster_DTO();
		lDTO.setLocationSeqNo(lMaster.getId().getLocationSeqNo());		
		lDTO.setServiceSeqNo(lMaster.getId().getServiceSeqNo());
		lDTO.setPartySeqNo(lMaster.getId().getPartySeqNo());
		lDTO.setDurSeqNo(lMaster.getDurSeqNo());
		lDTO.setDuration(lMaster.getDuration());
		return lDTO;
	}
	
}