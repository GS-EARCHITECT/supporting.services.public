package resource_class_details_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import resource_class_details_mgmt.model.dto.ResourceClassDetail_DTO;
import resource_class_details_mgmt.model.master.ResourceClassDetailPK;

public interface I_ResourceClassDetailsPublicRead_Service
{
	abstract public CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getAllResourceClassDetails();
    abstract public CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getSelectResourceClassDetails(CopyOnWriteArrayList<ResourceClassDetailPK> ids);
    abstract public CompletableFuture<CopyOnWriteArrayList<ResourceClassDetail_DTO>> getSelectResourcesForClasses(CopyOnWriteArrayList<Long> ids); 
}