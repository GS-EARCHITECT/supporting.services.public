package service_class_structure_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_class_structure_mgmt.model.dto.ServiceClassStructure_DTO;

public interface I_ServiceClassStructurePublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getAllServiceClassStructures();    
    public CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructures(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> pids);
    public CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructuresByParties( CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructuresByParents(CopyOnWriteArrayList<Long> ids);
}