package resource_class_structure_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource_class_structure_mgmt.model.master.ResourceClassStructure;
import resource_class_structure_mgmt.model.master.ResourceClassStructurePK;

@Repository("resourceClassStructurePublicReadRepo")
public interface ResourceClassStructurePublicRead_Repo extends JpaRepository<ResourceClassStructure, ResourceClassStructurePK> 
{
	
	@Query(value = "SELECT * FROM RESOURCE_CLASS_STRUCTURE order by resource_class_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceClassStructure> getAllResourceClassStructures();
	
	@Query(value = "SELECT * FROM RESOURCE_CLASS_STRUCTURE a WHERE (a.resource_class_seq_no in :ids and a.par_resource_class_seq_no in :pids) order by resource_class_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceClassStructure> getSelectResourceClassStructures(@Param("ids") CopyOnWriteArrayList<Long> ids, @Param("pids") CopyOnWriteArrayList<Long> pids);

	@Query(value = "SELECT * FROM RESOURCE_CLASS_STRUCTURE a WHERE a.party_seq_no in :ids order by resource_class_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceClassStructure> getSelectResourceClassStructuresByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM RESOURCE_CLASS_STRUCTURE a WHERE a.par_resource_class_seq_no in :ids order by resource_class_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ResourceClassStructure> getSelectResourceClassesByParents(@Param("ids") CopyOnWriteArrayList<Long> ids);
}
