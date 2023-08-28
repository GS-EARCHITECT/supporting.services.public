package resource_location_mgmt_public.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import resource_location_mgmt_public.model.dto.ResourceLocationMaster_DTO;

public interface I_ResourceLocationMasterPublicRead_Service
{
	public CompletableFuture<CopyOnWriteArrayList<ResourceLocationMaster_DTO>> getAllPartyLocationResources();        
	public CompletableFuture<CopyOnWriteArrayList<ResourceLocationMaster_DTO>> getSelectResources(CopyOnWriteArrayList<Long> ridss);
    public CompletableFuture<CopyOnWriteArrayList<ResourceLocationMaster_DTO>> getSelectResourcesByLocations( CopyOnWriteArrayList<Long> lidss);
    public CompletableFuture<CopyOnWriteArrayList<ResourceLocationMaster_DTO>> getSelectResourcesByParties(CopyOnWriteArrayList<Long> pids);        
    public CompletableFuture<Float> getResourceQoh(Long rid, Long pid, Long lid);
    
}