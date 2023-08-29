package resource_structure_details_mgmt.service.read;

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
import resource_structure_details_mgmt.model.dto.ResourceStructureDetail_DTO;
import resource_structure_details_mgmt.model.master.ResourceStructureDetail;
import resource_structure_details_mgmt.model.master.ResourceStructureDetailPK;
import resource_structure_details_mgmt.model.repo.read.ResourceStructureDetailsPublicRead_Repo;

@Service("resourceStructureDetailsPublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ResourceStructureDetailsPublicRead_Service implements I_ResourceStructureDetailsPublicRead_Service {

	@Autowired
	private ResourceStructureDetailsPublicRead_Repo resourceStructureDetailsPublicReadRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getAllResourceStructureDetails() {
		CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ResourceStructureDetail> lDetails = (CopyOnWriteArrayList<ResourceStructureDetail>) resourceStructureDetailsPublicReadRepo
							.findAll();
					CopyOnWriteArrayList<ResourceStructureDetail_DTO> resourceClassDetailsDTOs = lDetails != null
							? this.getResourceStructureDetail_DTOs(lDetails)
							: null;
					return resourceClassDetailsDTOs;
				}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetails(
			CopyOnWriteArrayList<ResourceStructureDetailPK> ids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ResourceStructureDetail> lDetails = (CopyOnWriteArrayList<ResourceStructureDetail>) resourceStructureDetailsPublicReadRepo
							.findAllById(ids);
					CopyOnWriteArrayList<ResourceStructureDetail_DTO> resourceClassDetailsDTOs = lDetails != null
							? this.getResourceStructureDetail_DTOs(lDetails)
							: null;
					return resourceClassDetailsDTOs;
				}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetailsByResources(
			CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> cids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ResourceStructureDetail> lDetails = resourceStructureDetailsPublicReadRepo
							.getSelectResourceStructureDetailsByResources(ids, cids);
					CopyOnWriteArrayList<ResourceStructureDetail_DTO> resourceClassDetailsDTOs = lDetails != null
							? this.getResourceStructureDetail_DTOs(lDetails)
							: null;
					return resourceClassDetailsDTOs;
				}, asyncExecutor);
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> getSelectResourceStructureDetailsByParents(
			CopyOnWriteArrayList<Long> ids) {
		CompletableFuture<CopyOnWriteArrayList<ResourceStructureDetail_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<ResourceStructureDetail> lDetails = resourceStructureDetailsPublicReadRepo
							.getSelectResourceClassesByParents(ids);
					CopyOnWriteArrayList<ResourceStructureDetail_DTO> resourceClassDetailsDTOs = lDetails != null
							? this.getResourceStructureDetail_DTOs(lDetails)
							: null;
					return resourceClassDetailsDTOs;
				}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<ResourceStructureDetail_DTO> getResourceStructureDetail_DTOs(
			CopyOnWriteArrayList<ResourceStructureDetail> lStructures) {
		ResourceStructureDetail_DTO lDTO = null;
		CopyOnWriteArrayList<ResourceStructureDetail_DTO> lStructureDTOs = new CopyOnWriteArrayList<ResourceStructureDetail_DTO>();
		for (int i = 0; i < lStructures.size(); i++) {
			lDTO = getResourceStructureDetail_DTO(lStructures.get(i));
			lStructureDTOs.add(lDTO);
		}
		return lStructureDTOs;
	}

	private synchronized ResourceStructureDetail_DTO getResourceStructureDetail_DTO(
			ResourceStructureDetail lStructure) {
		ResourceStructureDetail_DTO lDTO = new ResourceStructureDetail_DTO();
		lDTO.setParResourceClassSeqNo(lStructure.getId().getParResourceClassSeqNo());
		lDTO.setResourceClassSeqNo(lStructure.getId().getResourceClassSeqNo());
		lDTO.setParResourceSeqNo(lStructure.getId().getParResourceSeqNo());
		lDTO.setResourceSeqNo(lStructure.getId().getResourceSeqNo());
		return lDTO;
	}

}