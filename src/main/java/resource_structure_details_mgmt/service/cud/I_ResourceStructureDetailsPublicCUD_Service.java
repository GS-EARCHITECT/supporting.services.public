package resource_structure_details_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import resource_structure_details_mgmt.model.dto.ResourceStructureDetail_DTO;
import resource_structure_details_mgmt.model.master.ResourceStructureDetailPK;

public interface I_ResourceStructureDetailsPublicCUD_Service
{
    public CompletableFuture<ResourceStructureDetail_DTO> newResourceStructureDetail(ResourceStructureDetail_DTO resourceStructureDetailDTO);
    public CompletableFuture<Void> updResourceStructureDetail(ResourceStructureDetail_DTO resourceStructureDetailDTO);
    public CompletableFuture<Void> delAllResourceStructureDetails();
    public CompletableFuture<Void> delSelectResourceStructureDetails(CopyOnWriteArrayList<ResourceStructureDetailPK> ids);    
    public CompletableFuture<Void> delSelectResourceStructureDetailsByParents(CopyOnWriteArrayList<Long> pids);    
    public CompletableFuture<Void> delSelectResourceStructureDetailsByResources(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> cids);    
}