package resource_party_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import resource_party_mgmt.model.dto.ResourcePartyMaster_DTO;

public interface I_ResourcePartyMasterPublicRead_Service
{
  public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getAllResourcePartyMasters();    
  public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByResourceIds(CopyOnWriteArrayList<String> resourceCategorySeqNo);
  public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByParties(CopyOnWriteArrayList<Long> ids);
  public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByMasters(CopyOnWriteArrayList<Long> ids);
  public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResources(CopyOnWriteArrayList<Long> ids);
  public CompletableFuture<CopyOnWriteArrayList<ResourcePartyMaster_DTO>> getSelectResourcesByPriceRange( Float fr, Float to);
  public CompletableFuture<Float> getResourceQoh(Long id);

}