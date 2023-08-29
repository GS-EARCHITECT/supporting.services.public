package resource_structure_details_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource_structure_details_mgmt.model.master.ResourceStructureDetail;
import resource_structure_details_mgmt.model.master.ResourceStructureDetailPK;

@Repository("resourceStructureDetailsPublicCUDRepo")
public interface ResourceStructureDetailsPublicCUD_Repo extends JpaRepository<ResourceStructureDetail, ResourceStructureDetailPK> 
{
	@Query(value = "delete FROM RESOURCE_STRUCTURE_DETAILS a WHERE (a.resource_seq_no in :ids and a.resource_class_seq_no in :cids)", nativeQuery = true)
	void delSelectResourceStructureDetailsByResources(@Param("ids") CopyOnWriteArrayList<Long> ids, @Param("cids") CopyOnWriteArrayList<Long> cids);

	@Query(value = "delete FROM RESOURCE_STRUCTURE_DETAILS a WHERE a.par_resource_class_seq_no in :pids", nativeQuery = true)
	void delSelectResourceStructureDetailsByParents(@Param("pids") CopyOnWriteArrayList<Long> pids);
}
