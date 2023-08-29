package service_location_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_location_mgmt.model.dto.ServiceLocationMaster_DTO;

public interface I_ServiceLocationMasterPublicRead_Service
{
	public CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getAllPartyLocationServices();        
	public CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServices(CopyOnWriteArrayList<Long> ridss);
    public CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServicesByLocations( CopyOnWriteArrayList<Long> lidss);
    public CompletableFuture<CopyOnWriteArrayList<ServiceLocationMaster_DTO>> getSelectServicesByParties(CopyOnWriteArrayList<Long> pids);        
    public CompletableFuture<Float> getServiceDur(Long rid, Long pid, Long lid);
    
}