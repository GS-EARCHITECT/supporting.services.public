package service_class_details_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_class_details_mgmt.model.dto.ServiceClassDetail_DTO;
import service_class_details_mgmt.model.master.ServiceClassDetailPK;

public interface I_ServiceClassDetailsPublicRead_Service
{
	abstract public CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getAllServiceClassDetails();
    abstract public CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getSelectServiceClassDetails(CopyOnWriteArrayList<ServiceClassDetailPK> ids);
    abstract public CompletableFuture<CopyOnWriteArrayList<ServiceClassDetail_DTO>> getSelectServicesForClasses(CopyOnWriteArrayList<Long> ids); 
}