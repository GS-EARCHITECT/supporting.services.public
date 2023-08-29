package service_party_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import service_party_mgmt.model.dto.ServicePartyMaster_DTO;

public interface I_ServicePartyMasterPublicRead_Service
{
  public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getAllServicePartyMasters();    
  public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByServiceIds(CopyOnWriteArrayList<String> serviceCategorySeqNo);
  public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByParties(CopyOnWriteArrayList<Long> ids);
  public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByMasters(CopyOnWriteArrayList<Long> ids);
  public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServices(CopyOnWriteArrayList<Long> ids);
  public CompletableFuture<CopyOnWriteArrayList<ServicePartyMaster_DTO>> getSelectServicesByPriceRange( Float fr, Float to);
  public CompletableFuture<Float> getServiceDur(Long id);

}