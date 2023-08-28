package resource_party_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource_party_mgmt.model.master.ResourcePartyMaster;

@Repository("resourcePartyMasterPublicReadRepo")
public interface ResourcePartyMasterPublicRead_Repo extends JpaRepository<ResourcePartyMaster, Long> 
{
	@Query(value = "SELECT * FROM RESOURCE_PARTY_MASTER order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourcePartyMaster> getAllPartyResources();
	
	@Query(value = "SELECT * FROM RESOURCE_PARTY_MASTER a WHERE resource_seq_no in :ids order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourcePartyMaster> getSelectResources(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM RESOURCE_PARTY_MASTER a WHERE upper(trim(a.resource_id)) in :ids order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourcePartyMaster> getSelectResourcesByResourceIds(@Param("ids") CopyOnWriteArrayList<String> ids);

	@Query(value = "SELECT * FROM RESOURCE_PARTY_MASTER a WHERE a.party_seq_no in :ids order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourcePartyMaster> getSelectResourcePartiesByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM RESOURCE_PARTY_MASTER a WHERE master_resource_seq_no in :ids order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourcePartyMaster> getSelectResourcesbyMasters(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM RESOURCE_PARTY_MASTER a WHERE (a.unit_price >= :fr and a.unit_price <= :to) order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourcePartyMaster> getSelectResourcesbyPriceRange(@Param("fr") Float fr, @Param("to") Float to);

	@Query(value = "SELECT coalesce(qoh,0) FROM RESOURCE_PARTY_MASTER a WHERE a.resource_seq_no = :id", nativeQuery = true)
	Float getResourceQoh(@Param("id") Long id);

}
