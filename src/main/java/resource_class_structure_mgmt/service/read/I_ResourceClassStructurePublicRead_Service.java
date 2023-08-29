package resource_class_structure_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import resource_class_structure_mgmt.model.dto.ResourceClassStructure_DTO;

public interface I_ResourceClassStructurePublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getAllResourceClassStructures();    
    public CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructures(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> pids);
    public CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructuresByParties( CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<ResourceClassStructure_DTO>> getSelectResourceClassStructuresByParents(CopyOnWriteArrayList<Long> ids);
}