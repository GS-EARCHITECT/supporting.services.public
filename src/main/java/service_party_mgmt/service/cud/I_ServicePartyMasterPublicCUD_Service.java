package service_party_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_party_mgmt.model.dto.ServicePartyMaster_DTO;

public interface I_ServicePartyMasterPublicCUD_Service
{
    public CompletableFuture<ServicePartyMaster_DTO> newServicePartyMaster(ServicePartyMaster_DTO servicePartyMaster_DTO);
    public CompletableFuture<Void> updServicePartyMaster(ServicePartyMaster_DTO servicePartyMaster_DTO);
    public CompletableFuture<Void> setServiceDur(Long id, Float dur);
    public CompletableFuture<Void> delAllServicePartyMasters();    
    public CompletableFuture<Void> delSelectServices(CopyOnWriteArrayList<Long> ids);
    public CompletableFuture<Void> delSelectServicesByServiceIds( CopyOnWriteArrayList<String> ids);
    public CompletableFuture<Void> delSelectServicesByPriceRange(Float fr, Float to);	
    public CompletableFuture<Void> delSelectServicePartiesByParties( CopyOnWriteArrayList<Long> ids);
}