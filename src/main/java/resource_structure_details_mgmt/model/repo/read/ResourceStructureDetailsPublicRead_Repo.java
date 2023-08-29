package resource_structure_details_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource_structure_details_mgmt.model.master.ResourceStructureDetail;
import resource_structure_details_mgmt.model.master.ResourceStructureDetailPK;

@Repository("resourceStructureDetailsPublicReadRepo")
public interface ResourceStructureDetailsPublicRead_Repo extends JpaRepository<ResourceStructureDetail, ResourceStructureDetailPK> 
{
	@Query(value = "SELECT * FROM RESOURCE_STRUCTURE_DETAILS a WHERE (a.resource_seq_no in :ids and a.resource_class_seq_no in :cids) order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceStructureDetail> getSelectResourceStructureDetailsByResources(@Param("ids") CopyOnWriteArrayList<Long> ids, @Param("cids") CopyOnWriteArrayList<Long> cids);

	@Query(value = "SELECT * FROM RESOURCE_STRUCTURE_DETAILS a WHERE a.par_resource_class_seq_no in :pids order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceStructureDetail> getSelectResourceClassesByParents(@Param("pids") CopyOnWriteArrayList<Long> pids);
}
