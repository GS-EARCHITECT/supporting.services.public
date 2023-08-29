package resource_class_structure_mgmt.service.read;

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
import resource_class_structure_mgmt.model.repo.read.ResourceClassStructurePublicRead_Repo;

@Service("resourceClassStructurePublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourceClassStructurePublicRead_Service implements I_ResourceClassStructurePublicRead_Service 
{

	@Autowired
	private ResourceClassStructurePublicRead_Repo resourceClassStructurePublicReadRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructures(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> pids)
    {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourceClassStructure> lDetails = resourceClassStructurePublicReadRepo.getSelectResourceClassStructures(ids, pids);
		CopyOnWriteArrayList<ResourceClassStructure_DTO> resourceClassDetailsDTOs = lDetails != null ? this.getResourceClassStructure_DTOs(lDetails) : null;
		return resourceClassDetailsDTOs;
		}, asyncExecutor);
 		return future;

	}
	
	public CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getAllResourceClassStructures()
    {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourceClassStructure> lDetails = (CopyOnWriteArrayList<ResourceClassStructure>) resourceClassStructurePublicReadRepo.getAllResourceClassStructures();
		CopyOnWriteArrayList<ResourceClassStructure_DTO> resourceClassDetailsDTOs = lDetails != null ? this.getResourceClassStructure_DTOs(lDetails) : null;
		return resourceClassDetailsDTOs;
		}, asyncExecutor);
 		return future;
	}

	
	public CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructuresByParties( CopyOnWriteArrayList<Long> ids)
    {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourceClassStructure> lDetails = resourceClassStructurePublicReadRepo.getSelectResourceClassStructuresByParties(ids);
		CopyOnWriteArrayList<ResourceClassStructure_DTO> resourceClassDetailsDTOs = lDetails != null ? this.getResourceClassStructure_DTOs(lDetails) : null;
		return resourceClassDetailsDTOs;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructuresByParents(CopyOnWriteArrayList<Long> ids)
    {
		CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourceClassStructure> lDetails = resourceClassStructurePublicReadRepo.getSelectResourceClassesByParents(ids);
		CopyOnWriteArrayList<ResourceClassStructure_DTO> resourceClassDetailsDTOs = lDetails != null ? this.getResourceClassStructure_DTOs(lDetails) : null;
		return resourceClassDetailsDTOs;
		}, asyncExecutor);
 		return future;
	}
	
	private synchronized CopyOnWriteArrayList<ResourceClassStructure_DTO> getResourceClassStructure_DTOs(
			CopyOnWriteArrayList<ResourceClassStructure> lStructures) {
		ResourceClassStructure_DTO lDTO = null;
		CopyOnWriteArrayList<ResourceClassStructure_DTO> lStructureDTOs = new CopyOnWriteArrayList<ResourceClassStructure_DTO>();
		for (int i = 0; i < lStructures.size(); i++) {
			lDTO = getResourceClassStructure_DTO(lStructures.get(i));
			lStructureDTOs.add(lDTO);
		}
		return lStructureDTOs;
	}

	private synchronized ResourceClassStructure_DTO getResourceClassStructure_DTO(ResourceClassStructure lStructure) {
		ResourceClassStructure_DTO lDTO = new ResourceClassStructure_DTO();
		lDTO.setParResourceClassSeqNo(lStructure.getId().getParResourceClassSeqNo());
		lDTO.setResourceClassSeqNo(lStructure.getId().getResourceClassSeqNo());
		lDTO.setPartySeqNo(lStructure.getId().getPartySeqNo());
		return lDTO;
	}
}