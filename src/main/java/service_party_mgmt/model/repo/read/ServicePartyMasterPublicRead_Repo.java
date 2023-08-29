package service_party_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_party_mgmt.model.master.ServicePartyMaster;

@Repository("servicePartyMasterPublicReadRepo")
public interface ServicePartyMasterPublicRead_Repo extends JpaRepository<ServicePartyMaster, Long> 
{
	@Query(value = "SELECT * FROM SERVICE_PARTY_MASTER order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServicePartyMaster> getAllPartyServices();
	
	@Query(value = "SELECT * FROM SERVICE_PARTY_MASTER a WHERE service_seq_no in :ids order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServicePartyMaster> getSelectServices(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM SERVICE_PARTY_MASTER a WHERE upper(trim(a.service_id)) in :ids order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServicePartyMaster> getSelectServicesByServiceIds(@Param("ids") CopyOnWriteArrayList<String> ids);

	@Query(value = "SELECT * FROM SERVICE_PARTY_MASTER a WHERE a.party_seq_no in :ids order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServicePartyMaster> getSelectServicePartiesByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM SERVICE_PARTY_MASTER a WHERE master_service_seq_no in :ids order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServicePartyMaster> getSelectServicesbyMasters(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM SERVICE_PARTY_MASTER a WHERE (a.unit_price >= :fr and a.unit_price <= :to) order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServicePartyMaster> getSelectServicesbyPriceRange(@Param("fr") Float fr, @Param("to") Float to);

	@Query(value = "SELECT coalesce(duration,0) FROM SERVICE_PARTY_MASTER a WHERE a.service_seq_no = :id", nativeQuery = true)
	Float getServiceDur(@Param("id") Long id);

}
