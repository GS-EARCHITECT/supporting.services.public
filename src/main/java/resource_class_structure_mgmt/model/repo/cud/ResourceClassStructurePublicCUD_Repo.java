package resource_class_structure_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import resource_class_structure_mgmt.model.master.ResourceClassStructure;
import resource_class_structure_mgmt.model.master.ResourceClassStructurePK;

@Repository("resourceClassStructurePublicCUDRepo")
public interface ResourceClassStructurePublicCUD_Repo extends JpaRepository<ResourceClassStructure, ResourceClassStructurePK> 
{
	
	@Query(value = "DELETE FROM RESOURCE_CLASS_STRUCTURE WHERE (a.resource_class_seq_no in :ids and a.par_resource_class_seq_no in :pids)", nativeQuery = true)
	void delSelectResourceClassStructures(@Param("ids") CopyOnWriteArrayList<Long> ids, @Param("pids") CopyOnWriteArrayList<Long> pids);

	@Query(value = "SELECT * FROM RESOURCE_CLASS_STRUCTURE a WHERE a.par_resource_class_seq_no in :ids", nativeQuery = true)
	void delSelectResourceClassStructuresByParents(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM RESOURCE_CLASS_STRUCTURE a WHERE a.party_seq_no in :ids", nativeQuery = true)
	void delSelectResourceClassStructuresByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
}
