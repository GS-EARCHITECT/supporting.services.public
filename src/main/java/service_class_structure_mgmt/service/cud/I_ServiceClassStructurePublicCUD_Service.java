package service_class_structure_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_class_structure_mgmt.model.dto.ServiceClassStructure_DTO;

public interface I_ServiceClassStructurePublicCUD_Service
{
    public CompletableFuture<ServiceClassStructure_DTO> newServiceClassStructure(ServiceClassStructure_DTO serviceClassStructureDTO);
    public CompletableFuture<Void> updServiceClassStructure(ServiceClassStructure_DTO serviceClassStructureDTO);
    public CompletableFuture<Void> delAllServiceClassStructures();
    public CompletableFuture<Void> delSelectServiceClassStructures(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> pids);    
    public CompletableFuture<Void> delSelectServiceClassStructuresByParents(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectServiceClassStructuresByParties( CopyOnWriteArrayList<Long> ids);
}