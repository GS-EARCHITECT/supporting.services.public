package resource_class_structure_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import resource_class_structure_mgmt.model.dto.ResourceClassStructure_DTO;

public interface I_ResourceClassStructurePublicCUD_Service
{
    public CompletableFuture<ResourceClassStructure_DTO> newResourceClassStructure(ResourceClassStructure_DTO resourceClassStructureDTO);
    public CompletableFuture<Void> updResourceClassStructure(ResourceClassStructure_DTO resourceClassStructureDTO);
    public CompletableFuture<Void> delAllResourceClassStructures();
    public CompletableFuture<Void> delSelectResourceClassStructures(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> pids);    
    public CompletableFuture<Void> delSelectResourceClassStructuresByParents(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectResourceClassStructuresByParties( CopyOnWriteArrayList<Long> ids);
}