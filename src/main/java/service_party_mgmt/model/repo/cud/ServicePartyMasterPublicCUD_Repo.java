package service_party_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_party_mgmt.model.master.ServicePartyMaster;

@Repository("servicePartyMasterPublicCUDRepo")
public interface ServicePartyMasterPublicCUD_Repo extends JpaRepository<ServicePartyMaster, Long> 
{

	@Query(value = "update SERVICE_DETAILS set duration = duration + :dur WHERE a.service_seq_no = :id", nativeQuery = true)
	void setServiceDur(@Param("id") Long id, @Param("dur") Float dur);
		
	@Query(value = "delete FROM SERVICE_DETAILS a WHERE upper(trim(a.service_id)) in :ids", nativeQuery = true)
	void delSelectServicesByServiceIds(@Param("ids") CopyOnWriteArrayList<String> ids);
	
	@Query(value = "delete FROM SERVICE_DETAILS a WHERE a.unit_price>=:fr and a.unit_price<=:to", nativeQuery = true)
	void delSelectServicesByPriceRange(@Param("fr") Float fr, @Param("to") Float to);
	
	@Query(value = "DELETE FROM SERVICE_DETAILS WHERE a.service_seq_no in :ids", nativeQuery = true)
	void delSelectServices(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM SERVICE_PARTY_MASTER a WHERE a.party_seq_no in :ids", nativeQuery = true)
	void delSelectServicePartiesByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);

}
