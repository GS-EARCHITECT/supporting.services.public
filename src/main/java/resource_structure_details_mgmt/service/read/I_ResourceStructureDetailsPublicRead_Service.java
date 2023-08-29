package resource_structure_details_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import resource_structure_details_mgmt.model.dto.ResourceStructureDetail_DTO;
import resource_structure_details_mgmt.model.master.ResourceStructureDetailPK;

public interface I_ResourceStructureDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getAllResourceStructureDetails();    
    public CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetails(CopyOnWriteArrayList<ResourceStructureDetailPK> ids);
    public CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetailsByResources(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> cids);
    public CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetailsByParents(CopyOnWriteArrayList<Long> ids);
}