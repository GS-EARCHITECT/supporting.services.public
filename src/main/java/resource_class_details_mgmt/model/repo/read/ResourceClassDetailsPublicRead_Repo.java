package resource_class_details_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource_class_details_mgmt.model.master.ResourceClassDetail;
import resource_class_details_mgmt.model.master.ResourceClassDetailPK;

@Repository("resourceClassDetailsPublicReadRepo")
public interface ResourceClassDetailsPublicRead_Repo extends JpaRepository<ResourceClassDetail, ResourceClassDetailPK> 
{
	@Query(value = "SELECT * FROM RESOURCE_CLASS_DETAILS order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceClassDetail> getAllResourceClassDetails();
	
	@Query(value = "SELECT * FROM RESOURCE_CLASS_DETAILS a WHERE (a.resource_seq_no in :ids) order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceClassDetail> getSelectResourceClassDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);

	@Query(value = "SELECT * FROM RESOURCE_CLASS_DETAILS a WHERE (a.resource_class_seq_no in :ids) order by resource_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceClassDetail> getSelectResourcesForClasses(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "DELETE FROM RESOURCE_CLASS_DETAILS WHERE (a.resource_seq_no in :ids)", nativeQuery = true)
	void delSelectResourceClassDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "DELETE FROM RESOURCE_CLASS_DETAILS a WHERE (a.resource_class_seq_no in :ids)", nativeQuery = true)
	void delSelectResourcesForClasses(@Param("ids") CopyOnWriteArrayList<Long> ids);

}
