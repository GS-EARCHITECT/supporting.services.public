package service_structure_details_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_structure_details_mgmt.model.dto.ServiceStructureDetail_DTO;
import service_structure_details_mgmt.model.master.ServiceStructureDetailPK;

public interface I_ServiceStructureDetailsPublicRead_Service
{
    public CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getAllServiceStructureDetails();    
    public CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetails(CopyOnWriteArrayList<ServiceStructureDetailPK> ids);
    public CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetailsByServices(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> cids);
    public CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetailsByParents(CopyOnWriteArrayList<Long> ids);
}