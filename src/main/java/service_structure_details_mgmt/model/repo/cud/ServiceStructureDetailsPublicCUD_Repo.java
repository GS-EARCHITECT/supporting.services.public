package service_structure_details_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_structure_details_mgmt.model.master.ServiceStructureDetail;
import service_structure_details_mgmt.model.master.ServiceStructureDetailPK;

@Repository("serviceStructureDetailsPublicCUDRepo")
public interface ServiceStructureDetailsPublicCUD_Repo extends JpaRepository<ServiceStructureDetail, ServiceStructureDetailPK> 
{
	@Query(value = "delete FROM SERVICE_STRUCTURE_DETAILS a WHERE (a.service_seq_no in :ids and a.service_class_seq_no in :cids)", nativeQuery = true)
	void delSelectServiceStructureDetailsByServices(@Param("ids") CopyOnWriteArrayList<Long> ids, @Param("cids") CopyOnWriteArrayList<Long> cids);

	@Query(value = "delete FROM SERVICE_STRUCTURE_DETAILS a WHERE a.par_service_class_seq_no in :pids", nativeQuery = true)
	void delSelectServiceStructureDetailsByParents(@Param("pids") CopyOnWriteArrayList<Long> pids);
}
