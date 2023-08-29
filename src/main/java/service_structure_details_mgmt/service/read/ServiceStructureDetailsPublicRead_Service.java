package service_structure_details_mgmt.service.read;

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

import service_structure_details_mgmt.model.dto.ServiceStructureDetail_DTO;
import service_structure_details_mgmt.model.master.ServiceStructureDetail;
import service_structure_details_mgmt.model.master.ServiceStructureDetailPK;
import service_structure_details_mgmt.model.repo.read.ServiceStructureDetailsPublicRead_Repo;

@Service("serviceStructureDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceStructureDetailsPublicRead_Service implements I_ServiceStructureDetailsPublicRead_Service {

	@Autowired
	private ServiceStructureDetailsPublicRead_Repo serviceStructureDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getAllServiceStructureDetails() {
		CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ServiceStructureDetail> lDetails = (CopyOnWriteArrayList<ServiceStructureDetail>) serviceStructureDetailsPublicReadRepo
							.findAll();
					CopyOnWriteArrayList<ServiceStructureDetail_DTO> serviceClassDetailsDTOs = lDetails != null
							? this.getServiceStructureDetail_DTOs(lDetails)
							: null;
					return serviceClassDetailsDTOs;
				}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetails(
			CopyOnWriteArrayList<ServiceStructureDetailPK> ids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ServiceStructureDetail> lDetails = (CopyOnWriteArrayList<ServiceStructureDetail>) serviceStructureDetailsPublicReadRepo
							.findAllById(ids);
					CopyOnWriteArrayList<ServiceStructureDetail_DTO> serviceClassDetailsDTOs = lDetails != null
							? this.getServiceStructureDetail_DTOs(lDetails)
							: null;
					return serviceClassDetailsDTOs;
				}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetailsByServices(
			CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> cids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ServiceStructureDetail> lDetails = serviceStructureDetailsPublicReadRepo
							.getSelectServiceStructureDetailsByServices(ids, cids);
					CopyOnWriteArrayList<ServiceStructureDetail_DTO> serviceClassDetailsDTOs = lDetails != null
							? this.getServiceStructureDetail_DTOs(lDetails)
							: null;
					return serviceClassDetailsDTOs;
				}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> getSelectServiceStructureDetailsByParents(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<ServiceStructureDetail_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ServiceStructureDetail> lDetails = serviceStructureDetailsPublicReadRepo
							.getSelectServiceClassesByParents(ids);
					CopyOnWriteArrayList<ServiceStructureDetail_DTO> serviceClassDetailsDTOs = lDetails != null
							? this.getServiceStructureDetail_DTOs(lDetails)
							: null;
					return serviceClassDetailsDTOs;
				}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<ServiceStructureDetail_DTO> getServiceStructureDetail_DTOs(
			CopyOnWriteArrayList<ServiceStructureDetail> lStructures) {
		ServiceStructureDetail_DTO lDTO = null;
		CopyOnWriteArrayList<ServiceStructureDetail_DTO> lStructureDTOs = new CopyOnWriteArrayList<ServiceStructureDetail_DTO>();
		for (int i = 0; i < lStructures.size(); i++) {
			lDTO = getServiceStructureDetail_DTO(lStructures.get(i));
			lStructureDTOs.add(lDTO);
		}
		return lStructureDTOs;
	}

	private synchronized ServiceStructureDetail_DTO getServiceStructureDetail_DTO(
			ServiceStructureDetail lStructure) {
		ServiceStructureDetail_DTO lDTO = new ServiceStructureDetail_DTO();
		lDTO.setParServiceClassSeqNo(lStructure.getId().getParServiceClassSeqNo());
		lDTO.setServiceClassSeqNo(lStructure.getId().getServiceClassSeqNo());
		lDTO.setParServiceSeqNo(lStructure.getId().getParServiceSeqNo());
		lDTO.setServiceSeqNo(lStructure.getId().getServiceSeqNo());
		return lDTO;
	}

}