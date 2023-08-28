package resource_party_mgmt.service.read;

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
import resource_party_mgmt.model.dto.ResourcePartyMaster_DTO;
import resource_party_mgmt.model.master.ResourcePartyMaster;
import resource_party_mgmt.model.repo.read.ResourcePartyMasterPublicRead_Repo;

@Service("resourcePartyMasterPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourcePartyMasterPublicRead_Service implements I_ResourcePartyMasterPublicRead_Service 
{

	@Autowired
	private ResourcePartyMasterPublicRead_Repo resourcePartyMasterPublicReadRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getAllResourcePartyMasters() 
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourcePartyMaster> resourceList = (CopyOnWriteArrayList<ResourcePartyMaster>) resourcePartyMasterPublicReadRepo.getAllPartyResources();
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> lMasterss = resourceList != null ? this.getResourcePartyMaster_DTOs(resourceList) : null;
		return lMasterss;
	}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByResourceIds(CopyOnWriteArrayList<String> resourceIds)
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourcePartyMaster> lMasters = resourcePartyMasterPublicReadRepo.getSelectResourcesByResourceIds(resourceIds);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> lMasterss = lMasters != null ? this.getResourcePartyMaster_DTOs(lMasters) : null;
		return lMasterss;
 		}, asyncExecutor);
 		return future;
 	}
	
	public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByParties(CopyOnWriteArrayList<Long> ids)
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourcePartyMaster> lMasters = resourcePartyMasterPublicReadRepo.getSelectResourcePartiesByParties(ids);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> lMasterss = lMasters != null ? this.getResourcePartyMaster_DTOs(lMasters) : null;
		return lMasterss;
 		}, asyncExecutor);
 		return future;
 	}

	public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByMasters(CopyOnWriteArrayList<Long> ids)
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourcePartyMaster> lMasters = resourcePartyMasterPublicReadRepo.getSelectResourcesbyMasters(ids);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> lMasterss = lMasters != null ? this.getResourcePartyMaster_DTOs(lMasters) : null;
		return lMasterss;
 		}, asyncExecutor);
 		return future;	
 	}
	
	public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResources(CopyOnWriteArrayList<Long> ids)
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourcePartyMaster> lMasters = resourcePartyMasterPublicReadRepo.getSelectResources(ids);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> lMasterss = lMasters != null ? this.getResourcePartyMaster_DTOs(lMasters) : null;
		return lMasterss;
 		}, asyncExecutor);
 		return future;
 	}
   
	public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>>  getSelectResourcesByPriceRange(Float fr, Float to)
	{
		CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ResourcePartyMaster> lMasters = resourcePartyMasterPublicReadRepo.getSelectResourcesbyPriceRange(fr, to);
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> lMasterss = lMasters != null ? this.getResourcePartyMaster_DTOs(lMasters) : null;
		return lMasterss; 	
 		}, asyncExecutor);
 		return future;
 	}
	
	public CompletableFuture<Float>  getResourceQoh(Long id)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
 		{
		Float qoh = resourcePartyMasterPublicReadRepo.getResourceQoh(id);
		return qoh; 	
 		}, asyncExecutor);
 		return future;
 	}
	
	private synchronized CopyOnWriteArrayList<ResourcePartyMaster_DTO> getResourcePartyMaster_DTOs(CopyOnWriteArrayList<ResourcePartyMaster> lMasters) {
		ResourcePartyMaster_DTO lDTO = null;
		CopyOnWriteArrayList<ResourcePartyMaster_DTO> lMasterDTOs = new CopyOnWriteArrayList<ResourcePartyMaster_DTO>();		
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getResourcePartyMaster_DTO(lMasters.get(i));			
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized ResourcePartyMaster_DTO getResourcePartyMaster_DTO(ResourcePartyMaster lMaster) 
	{		
		ResourcePartyMaster_DTO lDTO = new ResourcePartyMaster_DTO();
		lDTO.setRemark(lMaster.getRemark());		
		lDTO.setUnitPrice(lMaster.getUnitPrice());
		lDTO.setUnitPriceSeqNo(lMaster.getUnitPriceSeqNo());
		lDTO.setDiscSeqNo(lMaster.getDiscSeqNo());
		lDTO.setDiscPer(lMaster.getDiscPer());
		lDTO.setDiscVal(lMaster.getDiscVal());
		lDTO.setTaxSeqNo(lMaster.getTaxSeqNo());
		lDTO.setTaxPer(lMaster.getTaxPer());
		lDTO.setTaxVal(lMaster.getTaxVal());
		lDTO.setQoh(lMaster.getQoh());
		lDTO.setSpecificationSeqNo(lMaster.getSpecificationSeqNo());		
		lDTO.setResourceId(lMaster.getResourceId());
		lDTO.setQtyCodeSeqNo(lMaster.getQtyCodeSeqNo());
		lDTO.setPartySeqNo(lMaster.getPartySeqNo());
		lDTO.setReorderLevel(lMaster.getReorderLevel());
		lDTO.setReorderQty(lMaster.getReorderQty());
		lDTO.setMaxQty(lMaster.getMaxQty());
		lDTO.setResourceSeqNo(lMaster.getResourceSeqNo());
		lDTO.setMasterResourceSeqNo(lMaster.getMasterResourceSeqNo());
		return lDTO;
	}
	
}