package service_structure_details_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_structure_details_mgmt.model.dto.ServiceStructureDetail_DTO;
import service_structure_details_mgmt.model.master.ServiceStructureDetailPK;

public interface I_ServiceStructureDetailsPublicCUD_Service
{
    public CompletableFuture<ServiceStructureDetail_DTO> newServiceStructureDetail(ServiceStructureDetail_DTO serviceStructureDetailDTO);
    public CompletableFuture<Void> updServiceStructureDetail(ServiceStructureDetail_DTO serviceStructureDetailDTO);
    public CompletableFuture<Void> delAllServiceStructureDetails();
    public CompletableFuture<Void> delSelectServiceStructureDetails(CopyOnWriteArrayList<ServiceStructureDetailPK> ids);    
    public CompletableFuture<Void> delSelectServiceStructureDetailsByParents(CopyOnWriteArrayList<Long> pids);    
    public CompletableFuture<Void> delSelectServiceStructureDetailsByServices(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> cids);    
}