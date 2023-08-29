package service_class_details_mgmt.service.cud;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service_class_details_mgmt.model.dto.ServiceClassDetail_DTO;
import service_class_details_mgmt.model.master.ServiceClassDetail;
import service_class_details_mgmt.model.master.ServiceClassDetailPK;
import service_class_details_mgmt.model.repo.cud.ServiceClassDetailsPublicCUD_Repo;

@Service("serviceClassDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceClassDetailsPublicCUD_Service implements I_ServiceClassDetailsPublicCUD_Service {

	@Autowired
	private ServiceClassDetailsPublicCUD_Repo serviceClassDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<ServiceClassDetail_DTO> newServiceClassDetail(ServiceClassDetail_DTO lDetails) {
		CompletableFuture<ServiceClassDetail_DTO> future = CompletableFuture.supplyAsync(() -> {
			Optional<ServiceClassDetail> serviceClassDetails = null;
			ServiceClassDetail_DTO serviceClassDetailsDTO = null;
			ServiceClassDetailPK serviceClassDetailsPK = new ServiceClassDetailPK();
			serviceClassDetailsPK.setServiceSeqNo(lDetails.getServiceSeqNo());
			serviceClassDetailsPK.setServiceClassSeqNo(lDetails.getServiceClassSeqNo());
			serviceClassDetails = serviceClassDetailsPublicCUDRepo.findById(serviceClassDetailsPK);

			if (!serviceClassDetails.isPresent()) {
				serviceClassDetails.get().setId(serviceClassDetailsPK);
				serviceClassDetailsDTO = getServiceClassDetail_DTO(
						serviceClassDetailsPublicCUDRepo.save(serviceClassDetails.get()));
			}
			return serviceClassDetailsDTO;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updServiceClassDetail(ServiceClassDetail_DTO lDetails) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			ServiceClassDetailPK serviceClassDetailsPK = new ServiceClassDetailPK();
			serviceClassDetailsPK.setServiceSeqNo(lDetails.getServiceSeqNo());
			serviceClassDetailsPK.setServiceClassSeqNo(lDetails.getServiceClassSeqNo());

			if (serviceClassDetailsPublicCUDRepo.existsById(serviceClassDetailsPK)) {
				serviceClassDetailsPublicCUDRepo.save(this.setServiceClassDetail(lDetails));
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delAllServiceClassDetails() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			serviceClassDetailsPublicCUDRepo.deleteAll();
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectServiceClassDetails(CopyOnWriteArrayList<ServiceClassDetailPK> ids) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) {
				serviceClassDetailsPublicCUDRepo.deleteAllById(ids);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectServicesForClasses(CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (ids != null) {
				serviceClassDetailsPublicCUDRepo.delSelectServicesForClasses(ids);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	private synchronized ServiceClassDetail_DTO getServiceClassDetail_DTO(ServiceClassDetail lDetails) {
		ServiceClassDetail_DTO lDTO = new ServiceClassDetail_DTO();
		lDTO.setServiceSeqNo(lDetails.getId().getServiceSeqNo());
		lDTO.setServiceClassSeqNo(lDetails.getId().getServiceClassSeqNo());
		return lDTO;
	}

	private synchronized ServiceClassDetail setServiceClassDetail(ServiceClassDetail_DTO lDTO) {
		ServiceClassDetail lDetails = new ServiceClassDetail();
		ServiceClassDetailPK serviceClassDetailsPK = new ServiceClassDetailPK();
		serviceClassDetailsPK.setServiceSeqNo(lDTO.getServiceSeqNo());
		serviceClassDetailsPK.setServiceClassSeqNo(lDTO.getServiceClassSeqNo());
		lDetails.setId(serviceClassDetailsPK);
		return lDetails;
	}
}