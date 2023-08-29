package resource_structure_details_mgmt.service.cud;

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
import resource_structure_details_mgmt.model.dto.ResourceStructureDetail_DTO;
import resource_structure_details_mgmt.model.master.ResourceStructureDetail;
import resource_structure_details_mgmt.model.master.ResourceStructureDetailPK;
import resource_structure_details_mgmt.model.repo.cud.ResourceStructureDetailsPublicCUD_Repo;

@Service("resourceStructureDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourceStructureDetailsPublicCUD_Service implements I_ResourceStructureDetailsPublicCUD_Service 
{

	@Autowired
	private ResourceStructureDetailsPublicCUD_Repo resourceStructureDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<ResourceStructureDetail_DTO> newResourceStructureDetail(ResourceStructureDetail_DTO lStructure) 
	{
		CompletableFuture<ResourceStructureDetail_DTO> future = CompletableFuture.supplyAsync(() -> {
		ResourceStructureDetail_DTO resourceStructureDetailDTO = null;
		ResourceStructureDetailPK ResourceStructureDetailPK = new ResourceStructureDetailPK();
		ResourceStructureDetailPK.setParResourceClassSeqNo(lStructure.getParResourceClassSeqNo());
		ResourceStructureDetailPK.setResourceClassSeqNo(lStructure.getResourceClassSeqNo());
		ResourceStructureDetailPK.setParResourceSeqNo(lStructure.getParResourceSeqNo());
		ResourceStructureDetailPK.setResourceSeqNo(lStructure.getResourceSeqNo());
		
		if (!resourceStructureDetailsPublicCUDRepo.existsById(ResourceStructureDetailPK)) 
		{			
			resourceStructureDetailDTO = getResourceStructureDetail_DTO(
			resourceStructureDetailsPublicCUDRepo.save(this.setResourceStructureDetail(lStructure)));
		}
		return resourceStructureDetailDTO;
		}, asyncExecutor);
return future;
	}
	
	public CompletableFuture<Void> updResourceStructureDetail(ResourceStructureDetail_DTO lStructure) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		ResourceStructureDetailPK ResourceStructureDetailPK = new ResourceStructureDetailPK();
		ResourceStructureDetailPK.setParResourceClassSeqNo(lStructure.getParResourceClassSeqNo());
		ResourceStructureDetailPK.setResourceClassSeqNo(lStructure.getResourceClassSeqNo());
		ResourceStructureDetailPK.setParResourceSeqNo(lStructure.getParResourceSeqNo());
		ResourceStructureDetailPK.setResourceSeqNo(lStructure.getResourceSeqNo());

		if (resourceStructureDetailsPublicCUDRepo.existsById(ResourceStructureDetailPK)) 
		{
			resourceStructureDetailsPublicCUDRepo.save(this.setResourceStructureDetail(lStructure));
		}
		return;
		}, asyncExecutor);
return future;
	}

		
	public CompletableFuture<Void> delAllResourceStructureDetails() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		resourceStructureDetailsPublicCUDRepo.deleteAll();
		return;
		}, asyncExecutor);
		return future;		
	}

	public CompletableFuture<Void> delSelectResourceStructureDetails(CopyOnWriteArrayList<ResourceStructureDetailPK> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		if (ids != null) 
		{
			resourceStructureDetailsPublicCUDRepo.deleteAllById(ids);
		}
		return;
		}, asyncExecutor);
		return future;		
	}
	
	
	public CompletableFuture<Void> delSelectResourceStructureDetailsByResources(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> cids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		if (ids != null)
		{
			resourceStructureDetailsPublicCUDRepo.delSelectResourceStructureDetailsByResources(ids, cids);
		}
		return;
		}, asyncExecutor);
		return future;		
	}
	
	public CompletableFuture<Void> delSelectResourceStructureDetailsByParents(CopyOnWriteArrayList<Long> pids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {			
		if (pids != null)
		{
			resourceStructureDetailsPublicCUDRepo.delSelectResourceStructureDetailsByParents(pids);
		}
		return;
		}, asyncExecutor);
		return future;		
	}
	
	private synchronized ResourceStructureDetail_DTO getResourceStructureDetail_DTO(ResourceStructureDetail lStructure) {
		ResourceStructureDetail_DTO lDTO = new ResourceStructureDetail_DTO();
		lDTO.setParResourceClassSeqNo(lStructure.getId().getParResourceClassSeqNo());
		lDTO.setResourceClassSeqNo(lStructure.getId().getResourceClassSeqNo());
		lDTO.setParResourceSeqNo(lStructure.getId().getParResourceSeqNo());
		lDTO.setResourceSeqNo(lStructure.getId().getResourceSeqNo());
		return lDTO;
	}

	private synchronized ResourceStructureDetail setResourceStructureDetail(ResourceStructureDetail_DTO lDTO) 
	{
		ResourceStructureDetail lStructure = new ResourceStructureDetail();
		ResourceStructureDetailPK ResourceStructureDetailPK = new ResourceStructureDetailPK();
		ResourceStructureDetailPK.setParResourceClassSeqNo(lDTO.getParResourceClassSeqNo());
		ResourceStructureDetailPK.setResourceClassSeqNo(lDTO.getResourceClassSeqNo());
		ResourceStructureDetailPK.setParResourceSeqNo(lDTO.getParResourceSeqNo());
		ResourceStructureDetailPK.setResourceSeqNo(lDTO.getResourceSeqNo());
		lStructure.setId(ResourceStructureDetailPK);
		return lStructure;
	}
}