package resource_class_details_mgmt.service.cud;

import java.util.Optional;
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
import resource_class_details_mgmt.model.repo.cud.ResourceClassDetailsPublicCUD_Repo;

@Service("resourceClassDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourceClassDetailsPublicCUD_Service implements I_ResourceClassDetailsPublicCUD_Service 
{

	@Autowired
	private ResourceClassDetailsPublicCUD_Repo resourceClassDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<ResourceClassDetail_DTO> newResourceClassDetail(ResourceClassDetail_DTO lDetails) 
	{
		CompletableFuture<ResourceClassDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
 		{
		Optional<ResourceClassDetail> resourceClassDetails = null;
		ResourceClassDetail_DTO resourceClassDetailsDTO = null;
		ResourceClassDetailPK resourceClassDetailsPK = new ResourceClassDetailPK();
		resourceClassDetailsPK.setResourceSeqNo(lDetails.getResourceSeqNo());
		resourceClassDetailsPK.setResourceClassSeqNo(lDetails.getResourceClassSeqNo());
		resourceClassDetails = resourceClassDetailsPublicCUDRepo.findById(resourceClassDetailsPK);
		
		if(!resourceClassDetails.isPresent())
		{
		resourceClassDetails.get().setId(resourceClassDetailsPK);	
		resourceClassDetailsDTO = 	getResourceClassDetail_DTO(resourceClassDetailsPublicCUDRepo.save(resourceClassDetails.get()));
		}
		return resourceClassDetailsDTO;
		}, asyncExecutor);
 		return future;	
	}

	public CompletableFuture<Void> updResourceClassDetail(ResourceClassDetail_DTO lDetails) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		ResourceClassDetailPK resourceClassDetailsPK = new ResourceClassDetailPK();
		resourceClassDetailsPK.setResourceSeqNo(lDetails.getResourceSeqNo());
		resourceClassDetailsPK.setResourceClassSeqNo(lDetails.getResourceClassSeqNo());	
		
		if (resourceClassDetailsPublicCUDRepo.existsById(resourceClassDetailsPK)) 
			{			
			resourceClassDetailsPublicCUDRepo.save(this.setResourceClassDetail(lDetails));			
		}
		return ;
		}, asyncExecutor);
 		return future;
	}

	
	public CompletableFuture<Void> delAllResourceClassDetails() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
 		resourceClassDetailsPublicCUDRepo.deleteAll();
		return ;
		}, asyncExecutor);
 		return future;
	}

	public CompletableFuture<Void> delSelectResourceClassDetails(CopyOnWriteArrayList<ResourceClassDetailPK> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		if (ids != null) 
		{
		resourceClassDetailsPublicCUDRepo.deleteAllById(ids);
		}
		return ;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectResourcesForClasses(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		if (ids != null) 
		{
		resourceClassDetailsPublicCUDRepo.delSelectResourcesForClasses(ids);
		}
		return ;
		}, asyncExecutor);
 		return future;
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