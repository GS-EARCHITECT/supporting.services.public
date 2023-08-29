package service_class_details_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_class_details_mgmt.model.dto.ServiceClassDetail_DTO;
import service_class_details_mgmt.model.master.ServiceClassDetailPK;

public interface I_ServiceClassDetailsPublicCUD_Service
{
    public CompletableFuture<ServiceClassDetail_DTO> newServiceClassDetail(ServiceClassDetail_DTO serviceClassDetailsDTO);
    public CompletableFuture<Void> updServiceClassDetail(ServiceClassDetail_DTO serviceClassDetailsDTO);
    public CompletableFuture<Void> delAllServiceClassDetails();
    public CompletableFuture<Void> delSelectServiceClassDetails(CopyOnWriteArrayList<ServiceClassDetailPK> ids);
    public CompletableFuture<Void> delSelectServicesForClasses(CopyOnWriteArrayList<Long> ids);    
}