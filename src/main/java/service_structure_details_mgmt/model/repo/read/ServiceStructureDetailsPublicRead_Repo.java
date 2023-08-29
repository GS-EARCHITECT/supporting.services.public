package service_structure_details_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_structure_details_mgmt.model.master.ServiceStructureDetail;
import service_structure_details_mgmt.model.master.ServiceStructureDetailPK;

@Repository("serviceStructureDetailsPublicReadRepo")
public interface ServiceStructureDetailsPublicRead_Repo extends JpaRepository<ServiceStructureDetail, ServiceStructureDetailPK> 
{
	@Query(value = "SELECT * FROM SERVICE_STRUCTURE_DETAILS a WHERE (a.service_seq_no in :ids and a.service_class_seq_no in :cids) order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceStructureDetail> getSelectServiceStructureDetailsByServices(@Param("ids") CopyOnWriteArrayList<Long> ids, @Param("cids") CopyOnWriteArrayList<Long> cids);

	@Query(value = "SELECT * FROM SERVICE_STRUCTURE_DETAILS a WHERE a.par_service_class_seq_no in :pids order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceStructureDetail> getSelectServiceClassesByParents(@Param("pids") CopyOnWriteArrayList<Long> pids);
}
