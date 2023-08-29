package resource_class_details_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import resource_class_details_mgmt.model.dto.ResourceClassDetail_DTO;
import resource_class_details_mgmt.model.master.ResourceClassDetailPK;

public interface I_ResourceClassDetailsPublicCUD_Service
{
    public CompletableFuture<ResourceClassDetail_DTO> newResourceClassDetail(ResourceClassDetail_DTO resourceClassDetailsDTO);
    public CompletableFuture<Void> updResourceClassDetail(ResourceClassDetail_DTO resourceClassDetailsDTO);
    public CompletableFuture<Void> delAllResourceClassDetails();
    public CompletableFuture<Void> delSelectResourceClassDetails(CopyOnWriteArrayList<ResourceClassDetailPK> ids);
    public CompletableFuture<Void> delSelectResourcesForClasses(CopyOnWriteArrayList<Long> ids);    
}