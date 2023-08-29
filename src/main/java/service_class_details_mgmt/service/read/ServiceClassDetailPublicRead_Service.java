package service_class_details_mgmt.service.read;

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

import service_class_details_mgmt.model.dto.ServiceClassDetail_DTO;
import service_class_details_mgmt.model.master.ServiceClassDetail;
import service_class_details_mgmt.model.master.ServiceClassDetailPK;
import service_class_details_mgmt.model.repo.read.ServiceClassDetailsPublicRead_Repo;

@Service("serviceClassDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceClassDetailPublicRead_Service implements I_ServiceClassDetailsPublicRead_Service 
{

	@Autowired
	private ServiceClassDetailsPublicRead_Repo serviceClassDetailsPublicReadRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getAllServiceClassDetails() 
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceClassDetail> serviceList = (CopyOnWriteArrayList<ServiceClassDetail>) serviceClassDetailsPublicReadRepo.getAllServiceClassDetails();
		CopyOnWriteArrayList<ServiceClassDetail_DTO> lDetails  = serviceList != null ? this.getServiceClassDetail_DTOs(serviceList) : null;
		return lDetails;
		}, asyncExecutor);
 		return future;
	}

    
	public CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getSelectServicesForClasses(CopyOnWriteArrayList<Long> ids)
    {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceClassDetail> lDetails = serviceClassDetailsPublicReadRepo.getSelectServicesForClasses(ids);
		CopyOnWriteArrayList<ServiceClassDetail_DTO> serviceClassDetailsDTOs = lDetails != null ? this.getServiceClassDetail_DTOs(lDetails) : null;
		return serviceClassDetailsDTOs;
	}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getSelectServiceClassDetails(CopyOnWriteArrayList<ServiceClassDetailPK> ids)
    {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceClassDetail> lDetails = (CopyOnWriteArrayList<ServiceClassDetail>) serviceClassDetailsPublicReadRepo.findAllById(ids);
		CopyOnWriteArrayList<ServiceClassDetail_DTO> serviceClassDetailsDTOs = null;
		serviceClassDetailsDTOs = lDetails != null ? this.getServiceClassDetail_DTOs(lDetails) : null;
		return serviceClassDetailsDTOs;
 		}, asyncExecutor);
 		return future;
	}
	
	private synchronized CopyOnWriteArrayList<ServiceClassDetail_DTO> getServiceClassDetail_DTOs(CopyOnWriteArrayList<ServiceClassDetail> lDetails) {
		ServiceClassDetail_DTO lDTO = null;
		CopyOnWriteArrayList<ServiceClassDetail_DTO> lDetailsDTOs = new CopyOnWriteArrayList<ServiceClassDetail_DTO>();		
		for (int i = 0; i < lDetails.size(); i++) {
			lDTO = getServiceClassDetail_DTO(lDetails.get(i));			
			lDetailsDTOs.add(lDTO);
		}
		return lDetailsDTOs;
	}

	private synchronized ServiceClassDetail_DTO getServiceClassDetail_DTO(ServiceClassDetail lDetails) 
	{		
		ServiceClassDetail_DTO lDTO = new ServiceClassDetail_DTO();		
		lDTO.setServiceSeqNo(lDetails.getId().getServiceSeqNo());
		lDTO.setServiceClassSeqNo(lDetails.getId().getServiceClassSeqNo());
		return lDTO;
	}

	private synchronized ServiceClassDetail setServiceClassDetail(ServiceClassDetail_DTO lDTO) 
	{
		ServiceClassDetail lDetails = new ServiceClassDetail();
		ServiceClassDetailPK serviceClassDetailsPK = new ServiceClassDetailPK();
		serviceClassDetailsPK.setServiceSeqNo(lDTO.getServiceSeqNo());
		serviceClassDetailsPK.setServiceClassSeqNo(lDTO.getServiceClassSeqNo());
		lDetails.setId(serviceClassDetailsPK);
		return lDetails;
	}
}