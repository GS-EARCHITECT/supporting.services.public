package resource_party_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import resource_party_mgmt.model.dto.ResourcePartyMaster_DTO;

public interface I_ResourcePartyMasterPublicCUD_Service
{
    public CompletableFuture<ResourcePartyMaster_DTO> newResourcePartyMaster(ResourcePartyMaster_DTO resourcePartyMaster_DTO);
    public CompletableFuture<Void> updResourcePartyMaster(ResourcePartyMaster_DTO resourcePartyMaster_DTO);
    public CompletableFuture<Void> setResourceQoh(Long id, Float qty);
    public CompletableFuture<Void> delAllResourcePartyMasters();    
    public CompletableFuture<Void> delSelectResources(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectResourcesByResourceIds( CopyOnWriteArrayList<String> ids);
    public CompletableFuture<Void> delSelectResourcesByPriceRange(Float fr, Float to);	
    public CompletableFuture<Void> delSelectResourcePartiesByParties( CopyOnWriteArrayList<Long> ids);
}