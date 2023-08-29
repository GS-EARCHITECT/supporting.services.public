package service_class_structure_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_class_structure_mgmt.model.master.ServiceClassStructure;
import service_class_structure_mgmt.model.master.ServiceClassStructurePK;

@Repository("serviceClassStructurePublicReadRepo")
public interface ServiceClassStructurePublicRead_Repo extends JpaRepository<ServiceClassStructure, ServiceClassStructurePK> 
{
	
	@Query(value = "SELECT * FROM SERVICE_CLASS_STRUCTURE order by service_class_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceClassStructure> getAllServiceClassStructures();
	
	@Query(value = "SELECT * FROM SERVICE_CLASS_STRUCTURE a WHERE (a.service_class_seq_no in :ids and a.par_service_class_seq_no in :pids) order by service_class_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceClassStructure> getSelectServiceClassStructures(@Param("ids") CopyOnWriteArrayList<Long> ids, @Param("pids") CopyOnWriteArrayList<Long> pids);

	@Query(value = "SELECT * FROM SERVICE_CLASS_STRUCTURE a WHERE a.party_seq_no in :ids order by service_class_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceClassStructure> getSelectServiceClassStructuresByParties(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "SELECT * FROM SERVICE_CLASS_STRUCTURE a WHERE a.par_service_class_seq_no in :ids order by service_class_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceClassStructure> getSelectServiceClassesByParents(@Param("ids") CopyOnWriteArrayList<Long> ids);
}
