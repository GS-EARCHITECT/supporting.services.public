package resource_party_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource_party_mgmt.model.master.ResourcePartyMaster;

@Repository("resourcePartyMasterPublicCUDRepo")
public interface ResourcePartyMasterPublicCUD_Repo extends JpaRepository<ResourcePartyMaster, Long> 
{

	@Query(value = "update RESOURCE_DETAILS set qoh = qoh + :qty WHERE a.resource_seq_no = :id", nativeQuery = true)
	void setResourceQoh(@Param("id") Long id, @Param("qty") Float qty);
		
	@Query(value = "delete FROM RESOURCE_DETAILS a WHERE upper(trim(a.resource_id)) in :ids", nativeQuery = true)
	void delSelectResourcesByResourceIds(@Param("ids") CopyOnWriteArrayList<String> ids);
	
	@Query(value = "delete FROM RESOURCE_DETAILS a WHERE a.unit_price>=:fr and a.unit_price<=:to", nativeQuery = true)
	void delSelectResourcesByPriceRange(@Param("fr") Float fr, @Param("to") Float to);
	
	@Query(value = "DELETE FROM RESOURCE_DETAILS WHERE a.resource_type_seq_no in :ids", nativeQuery = true)
	void delSelectResources(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM RESOURCE_PARTY_MASTER a WHERE a.party_seq_no in :ids", nativeQuery = true)
	void delSelectResourcePartiesByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);

}
