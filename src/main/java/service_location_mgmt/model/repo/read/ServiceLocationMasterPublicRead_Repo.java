package service_location_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service_location_mgmt.model.master.ServiceLocationMaster;
import service_location_mgmt.model.master.ServiceLocationMasterPK;

@Repository("serviceLocationMasterPublicReadRepo")
public interface ServiceLocationMasterPublicRead_Repo extends JpaRepository<ServiceLocationMaster, ServiceLocationMasterPK> 
{
	@Query(value = "SELECT coalesce(qoh,0) FROM SERVICE_LOCATION_MASTER a WHERE ((a.service_seq_no = :rid) and (a.location_seq_no = :lid) and (a.party_seq_no = :pid))", nativeQuery = true)
	Float getServiceDur(@Param("rid") Long rid, @Param("pid") Long pid, @Param("lid") Long lid);

	@Query(value = "select * FROM SERVICE_LOCATION_MASTER a WHERE a.location_seq_no in :lids order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceLocationMaster> getSelectServicesByLocations(@Param("lids") CopyOnWriteArrayList<Long> lidss);

	@Query(value = "select * FROM SERVICE_LOCATION_MASTER a WHERE a.party_seq_no in :lids order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceLocationMaster> getSelectServicesByParties(@Param("pids") CopyOnWriteArrayList<Long> pidss);
	
	@Query(value = "select * FROM SERVICE_LOCATION_MASTER a WHERE a.service_seq_no in :rids order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceLocationMaster> getSelectServices(@Param("rids") CopyOnWriteArrayList<Long> ridss);

	@Query(value = "select * FROM SERVICE_LOCATION_MASTER a order by service_seq_no", nativeQuery = true)
	CopyOnWriteArrayList<ServiceLocationMaster> getAllServicePartyLocations();


}
