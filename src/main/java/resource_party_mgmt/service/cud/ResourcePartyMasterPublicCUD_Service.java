package resource_party_mgmt.service.cud;

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
import resource_party_mgmt.model.repo.cud.ResourcePartyMasterPublicCUD_Repo;

@Service("resourceMasterPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourcePartyMasterPublicCUD_Service implements I_ResourcePartyMasterPublicCUD_Service 
{

	@Autowired
	private ResourcePartyMasterPublicCUD_Repo resourcePartyMasterPublicCUDRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<ResourcePartyMaster_DTO> newResourcePartyMaster(ResourcePartyMaster_DTO lMasters) 
	{
		CompletableFuture<ResourcePartyMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
 		{
		ResourcePartyMaster resourcePartyMaster = resourcePartyMasterPublicCUDRepo.save(this.setResourcePartyMaster(lMasters));
		ResourcePartyMaster_DTO lMasterss = getResourcePartyMaster_DTO(resourcePartyMaster);
		return lMasterss;
	}, asyncExecutor);
		return future;

	}

	public CompletableFuture<Void> updResourcePartyMaster(ResourcePartyMaster_DTO lMaster) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		ResourcePartyMaster resourceMaster = this.setResourcePartyMaster(lMaster);
		if (resourcePartyMasterPublicCUDRepo.existsById(lMaster.getResourceSeqNo())) 
				{		
			resourceMaster.setResourceSeqNo(lMaster.getResourceSeqNo());			
			resourcePartyMasterPublicCUDRepo.save(resourceMaster);			
		}
		return;
 		}, asyncExecutor);
 			return future;
	}
	
	public CompletableFuture<Void> setResourceQoh(Long id, Float qty) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		resourcePartyMasterPublicCUDRepo.setResourceQoh(id, qty);
		return;
 		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delAllResourcePartyMasters() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		resourcePartyMasterPublicCUDRepo.deleteAll();
		return;
 		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectResources(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		resourcePartyMasterPublicCUDRepo.delSelectResources(ids);
		return;
 		}, asyncExecutor);
 		return future;
	}

	public CompletableFuture<Void> delSelectResourcesByResourceIds(CopyOnWriteArrayList<String> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		resourcePartyMasterPublicCUDRepo.delSelectResourcesByResourceIds(ids);
		return;
 		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectResourcesByPriceRange(Float fr, Float to) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		resourcePartyMasterPublicCUDRepo.delSelectResourcesByPriceRange(fr, to);
		return;
 		}, asyncExecutor);
 		return future;
	}

	public CompletableFuture<Void> delSelectResourcePartiesByParties(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		resourcePartyMasterPublicCUDRepo.delSelectResourcePartiesByParties(ids);
		return;
 		}, asyncExecutor);
 		return future;
	}

	private ResourcePartyMaster_DTO getResourcePartyMaster_DTO(ResourcePartyMaster lMaster) 
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

	private ResourcePartyMaster setResourcePartyMaster(ResourcePartyMaster_DTO lDTO) 
	{
		ResourcePartyMaster lMaster = new ResourcePartyMaster();				
		lMaster.setRemark(lDTO.getRemark());		
		lMaster.setUnitPrice(lDTO.getUnitPrice());
		lMaster.setUnitPriceSeqNo(lDTO.getUnitPriceSeqNo());
		lMaster.setDiscSeqNo(lDTO.getDiscSeqNo());
		lMaster.setDiscPer(lDTO.getDiscPer());
		lMaster.setDiscVal(lDTO.getDiscVal());
		lMaster.setTaxSeqNo(lDTO.getTaxSeqNo());
		lMaster.setTaxPer(lDTO.getTaxPer());
		lMaster.setTaxVal(lDTO.getTaxVal());
		lMaster.setQoh(lDTO.getQoh());
		lMaster.setSpecificationSeqNo(lDTO.getSpecificationSeqNo());		
		lMaster.setResourceId(lDTO.getResourceId());
		lMaster.setQtyCodeSeqNo(lDTO.getQtyCodeSeqNo());
		lMaster.setPartySeqNo(lDTO.getPartySeqNo());
		lMaster.setReorderLevel(lDTO.getReorderLevel());
		lMaster.setReorderQty(lDTO.getReorderQty());
		lMaster.setMaxQty(lDTO.getMaxQty());
		lMaster.setMasterResourceSeqNo(lDTO.getMasterResourceSeqNo());
		return lMaster;
	}

}