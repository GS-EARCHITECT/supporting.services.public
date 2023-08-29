package service_location_mgmt.model.repo.cud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_location_mgmt.model.master.ServiceLocationMaster;
import service_location_mgmt.model.master.ServiceLocationMasterPK;

@Repository("serviceLocationMasterPublicCUDRepo")
public interface ServiceLocationMasterPublicCUD_Repo extends JpaRepository<ServiceLocationMaster, ServiceLocationMasterPK> 
{
	@Query(value = "update SERVICE_LOCATION_MASTER set dur = dur + :dur WHERE ((a.service_seq_no = :rid) and (a.location_seq_no = :lid) and (a.company_seq_no = :pid))", nativeQuery = true)
	void addServiceDur(@Param("rid") Long rid, @Param("dur") Float dur, @Param("lid") Long lid, @Param("pid") Long pid);

	@Query(value = "update SERVICE_LOCATION_MASTER set dur = dur - :dur WHERE ((a.service_seq_no = :rid) and (a.location_seq_no = :lid) and (a.company_seq_no = :pid))", nativeQuery = true)
	void subServiceDur(@Param("rid") Long rid, @Param("dur") Float dur, @Param("lid") Long lid, @Param("pid") Long pid);
	
	@Query(value = "update SERVICE_LOCATION_MASTER set dur = :dur WHERE ((a.service_seq_no = :rid) and (a.location_seq_no = :lid) and (a.company_seq_no = :pid))", nativeQuery = true)
	void setServiceDur(@Param("rid") Long rid, @Param("dur") Float dur, @Param("lid") Long lid, @Param("pid") Long pid);
}
