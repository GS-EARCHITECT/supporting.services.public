package resource_class_structure_mgmt.service.cud;

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
import resource_class_structure_mgmt.model.dto.ResourceClassStructure_DTO;
import resource_class_structure_mgmt.model.master.ResourceClassStructure;
import resource_class_structure_mgmt.model.master.ResourceClassStructurePK;
import resource_class_structure_mgmt.model.repo.cud.ResourceClassStructurePublicCUD_Repo;

@Service("resourceClassStructurePublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourceClassStructurePublicCUD_Service implements I_ResourceClassStructurePublicCUD_Service 
{

	@Autowired
	private ResourceClassStructurePublicCUD_Repo resourceClassStructurePublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<ResourceClassStructure_DTO> newResourceClassStructure(ResourceClassStructure_DTO lStructure) 
	{

		CompletableFuture<ResourceClassStructure_DTO> future = CompletableFuture.supplyAsync(() -> 
 		{
		ResourceClassStructure_DTO resourceClassStructureDTO = null;
		ResourceClassStructurePK resourceClassStructurePK = new ResourceClassStructurePK();
		resourceClassStructurePK.setParResourceClassSeqNo(lStructure.getParResourceClassSeqNo());
		resourceClassStructurePK.setResourceClassSeqNo(lStructure.getResourceClassSeqNo());
		resourceClassStructurePK.setPartySeqNo(lStructure.getPartySeqNo());
		
		if (!resourceClassStructurePublicCUDRepo.existsById(resourceClassStructurePK)) 
		{			
			resourceClassStructureDTO = getResourceClassStructure_DTO(
			resourceClassStructurePublicCUDRepo.save(this.setResourceClassStructure(lStructure)));
		}
		return resourceClassStructureDTO;
		}, asyncExecutor);
 		return future;

	}
	
	public CompletableFuture<Void> updResourceClassStructure(ResourceClassStructure_DTO lStructure) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		ResourceClassStructurePK resourceClassStructurePK = new ResourceClassStructurePK();
		resourceClassStructurePK.setParResourceClassSeqNo(lStructure.getParResourceClassSeqNo());
		resourceClassStructurePK.setResourceClassSeqNo(lStructure.getResourceClassSeqNo());
		resourceClassStructurePK.setPartySeqNo(lStructure.getPartySeqNo());

		if (resourceClassStructurePublicCUDRepo.existsById(resourceClassStructurePK)) 
		{
			resourceClassStructurePublicCUDRepo.save(this.setResourceClassStructure(lStructure));
		}
		return;
		}, asyncExecutor);
 		return future;
	}
		
	public CompletableFuture<Void> delAllResourceClassStructures() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		resourceClassStructurePublicCUDRepo.deleteAll();
		return;
		}, asyncExecutor);
 		return future;
	}

	public CompletableFuture<Void> delSelectResourceClassStructures(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> pids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		if (ids != null && pids != null) {
			resourceClassStructurePublicCUDRepo.delSelectResourceClassStructures(ids, pids);
		}
		return;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectResourceClassStructuresByParties(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		if (ids != null)
		{
			resourceClassStructurePublicCUDRepo.delSelectResourceClassStructuresByParties(ids);
		}
		return;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectResourceClassStructuresByParents(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		if (ids != null)
		{
			resourceClassStructurePublicCUDRepo.delSelectResourceClassStructuresByParents(ids);
		}
		return;
	}, asyncExecutor);
 		return future;
	}

	private synchronized ResourceClassStructure_DTO getResourceClassStructure_DTO(ResourceClassStructure lStructure) {
		ResourceClassStructure_DTO lDTO = new ResourceClassStructure_DTO();
		lDTO.setParResourceClassSeqNo(lStructure.getId().getParResourceClassSeqNo());
		lDTO.setResourceClassSeqNo(lStructure.getId().getResourceClassSeqNo());
		lDTO.setPartySeqNo(lStructure.getId().getPartySeqNo());
		return lDTO;
	}

	private synchronized ResourceClassStructure setResourceClassStructure(ResourceClassStructure_DTO lDTO) {
		ResourceClassStructure lStructure = new ResourceClassStructure();
		ResourceClassStructurePK resourceClassStructurePK = new ResourceClassStructurePK();
		resourceClassStructurePK.setParResourceClassSeqNo(lDTO.getParResourceClassSeqNo());
		resourceClassStructurePK.setResourceClassSeqNo(lDTO.getResourceClassSeqNo());
		resourceClassStructurePK.setPartySeqNo(lDTO.getPartySeqNo());
		lStructure.setId(resourceClassStructurePK);
		return lStructure;
	}
}