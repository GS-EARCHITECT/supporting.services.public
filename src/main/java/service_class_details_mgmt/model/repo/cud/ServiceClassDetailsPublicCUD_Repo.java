package service_class_details_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_class_details_mgmt.model.master.ServiceClassDetail;
import service_class_details_mgmt.model.master.ServiceClassDetailPK;

@Repository("serviceClassDetailsPublicCUDRepo")
public interface ServiceClassDetailsPublicCUD_Repo extends JpaRepository<ServiceClassDetail, ServiceClassDetailPK> 
{
	
	@Query(value = "DELETE FROM SERVICE_CLASS_DETAILS WHERE (a.service_seq_no in :ids)", nativeQuery = true)
	void delSelectServiceClassDetails(@Param("ids") CopyOnWriteArrayList<Long> ids);
	
	@Query(value = "DELETE FROM SERVICE_CLASS_DETAILS a WHERE (a.service_class_seq_no in :ids)", nativeQuery = true)
	void delSelectServicesForClasses(@Param("ids") CopyOnWriteArrayList<Long> ids);

}
