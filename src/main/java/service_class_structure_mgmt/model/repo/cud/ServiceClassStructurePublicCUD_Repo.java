package service_class_structure_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_class_structure_mgmt.model.master.ServiceClassStructure;
import service_class_structure_mgmt.model.master.ServiceClassStructurePK;

@Repository("serviceClassStructurePublicCUDRepo")
public interface ServiceClassStructurePublicCUD_Repo extends JpaRepository<ServiceClassStructure, ServiceClassStructurePK> 
{
	
	@Query(value = "DELETE FROM SERVICE_CLASS_STRUCTURE WHERE (a.service_class_seq_no in :ids and a.par_service_class_seq_no in :pids)", nativeQuery = true)
	void delSelectServiceClassStructures(@Param("ids") CopyOnWriteArrayList<Long> ids, @Param("pids") CopyOnWriteArrayList<Long> pids);

	@Query(value = "SELECT * FROM SERVICE_CLASS_STRUCTURE a WHERE a.par_service_class_seq_no in :ids", nativeQuery = true)
	void delSelectServiceClassStructuresByParents(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "delete FROM SERVICE_CLASS_STRUCTURE a WHERE a.party_seq_no in :ids", nativeQuery = true)
	void delSelectServiceClassStructuresByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
}
