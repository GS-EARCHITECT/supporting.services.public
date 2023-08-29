package resource_class_details_mgmt.service.read;

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
import resource_class_details_mgmt.model.dto.ResourceClassDetail_DTO;
import resource_class_details_mgmt.model.master.ResourceClassDetail;
import resource_class_details_mgmt.model.master.ResourceClassDetailPK;
import resource_class_details_mgmt.model.repo.read.ResourceClassDetailsPublicRead_Repo;

@Service("resourceClassDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourceClassDetailPublicRead_Service implements I_ResourceClassDetailsPublicRead_Service 
{

	@Autowired
	private ResourceClassDetailsPublicRead_Repo resourceClassDetailsPublicReadRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getAllResourceClassDetails() 
	{
		CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourceClassDetail> resourceList = (CopyOnWriteArrayList<ResourceClassDetail>) resourceClassDetailsPublicReadRepo.getAllResourceClassDetails();
		CopyOnWriteArrayList<ResourceClassDetail_DTO> lDetails  = resourceList != null ? this.getResourceClassDetail_DTOs(resourceList) : null;
		return lDetails;
		}, asyncExecutor);
 		return future;
	}

    
	public CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getSelectResourcesForClasses(CopyOnWriteArrayList<Long> ids)
    {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourceClassDetail> lDetails = resourceClassDetailsPublicReadRepo.getSelectResourcesForClasses(ids);
		CopyOnWriteArrayList<ResourceClassDetail_DTO> resourceClassDetailsDTOs = lDetails != null ? this.getResourceClassDetail_DTOs(lDetails) : null;
		return resourceClassDetailsDTOs;
	}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getSelectResourceClassDetails(CopyOnWriteArrayList<ResourceClassDetailPK> ids)
    {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourceClassDetail> lDetails = (CopyOnWriteArrayList<ResourceClassDetail>) resourceClassDetailsPublicReadRepo.findAllById(ids);
		CopyOnWriteArrayList<ResourceClassDetail_DTO> resourceClassDetailsDTOs = null;
		resourceClassDetailsDTOs = lDetails != null ? this.getResourceClassDetail_DTOs(lDetails) : null;
		return resourceClassDetailsDTOs;
 		}, asyncExecutor);
 		return future;
	}
	
	private synchronized CopyOnWriteArrayList<ResourceClassDetail_DTO> getResourceClassDetail_DTOs(CopyOnWriteArrayList<ResourceClassDetail> lDetails) {
		ResourceClassDetail_DTO lDTO = null;
		CopyOnWriteArrayList<ResourceClassDetail_DTO> lDetailsDTOs = new CopyOnWriteArrayList<ResourceClassDetail_DTO>();		
		for (int i = 0; i < lDetails.size(); i++) {
			lDTO = getResourceClassDetail_DTO(lDetails.get(i));			
			lDetailsDTOs.add(lDTO);
		}
		return lDetailsDTOs;
	}

	private synchronized ResourceClassDetail_DTO getResourceClassDetail_DTO(ResourceClassDetail lDetails) 
	{		
		ResourceClassDetail_DTO lDTO = new ResourceClassDetail_DTO();		
		lDTO.setResourceSeqNo(lDetails.getId().getResourceSeqNo());
		lDTO.setResourceClassSeqNo(lDetails.getId().getResourceClassSeqNo());
		return lDTO;
	}

	private synchronized ResourceClassDetail setResourceClassDetail(ResourceClassDetail_DTO lDTO) 
	{
		ResourceClassDetail lDetails = new ResourceClassDetail();
		ResourceClassDetailPK resourceClassDetailsPK = new ResourceClassDetailPK();
		resourceClassDetailsPK.setResourceSeqNo(lDTO.getResourceSeqNo());
		resourceClassDetailsPK.setResourceClassSeqNo(lDTO.getResourceClassSeqNo());
		lDetails.setId(resourceClassDetailsPK);
		return lDetails;
	}
}