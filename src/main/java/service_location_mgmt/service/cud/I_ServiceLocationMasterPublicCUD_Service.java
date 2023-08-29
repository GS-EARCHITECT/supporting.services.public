package service_location_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;

public interface I_ServiceLocationMasterPublicCUD_Service
{
    public CompletableFuture<Void> addPartyLocationServiceDur(Long id, Float dur, Long loc, Long py);
    public CompletableFuture<Void> subPartyLocationServiceDur(Long id, Float dur, Long loc, Long py);
    public CompletableFuture<Void> setPartyLocationServiceDur(Long id, Float dur, Long loc, Long py);
    
}