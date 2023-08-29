package service_class_structure_mgmt.service.read;

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

import service_class_structure_mgmt.model.dto.ServiceClassStructure_DTO;
import service_class_structure_mgmt.model.master.ServiceClassStructure;
import service_class_structure_mgmt.model.repo.read.ServiceClassStructurePublicRead_Repo;

@Service("serviceClassStructurePublicReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceClassStructurePublicRead_Service implements I_ServiceClassStructurePublicRead_Service 
{

	@Autowired
	private ServiceClassStructurePublicRead_Repo serviceClassStructurePublicReadRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructures(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> pids)
    {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceClassStructure> lDetails = serviceClassStructurePublicReadRepo.getSelectServiceClassStructures(ids, pids);
		CopyOnWriteArrayList<ServiceClassStructure_DTO> serviceClassDetailsDTOs = lDetails != null ? this.getServiceClassStructure_DTOs(lDetails) : null;
		return serviceClassDetailsDTOs;
		}, asyncExecutor);
 		return future;

	}
	
	public CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getAllServiceClassStructures()
    {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceClassStructure> lDetails = (CopyOnWriteArrayList<ServiceClassStructure>) serviceClassStructurePublicReadRepo.getAllServiceClassStructures();
		CopyOnWriteArrayList<ServiceClassStructure_DTO> serviceClassDetailsDTOs = lDetails != null ? this.getServiceClassStructure_DTOs(lDetails) : null;
		return serviceClassDetailsDTOs;
		}, asyncExecutor);
 		return future;
	}

	
	public CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructuresByParties( CopyOnWriteArrayList<Long> ids)
    {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceClassStructure> lDetails = serviceClassStructurePublicReadRepo.getSelectServiceClassStructuresByParties(ids);
		CopyOnWriteArrayList<ServiceClassStructure_DTO> serviceClassDetailsDTOs = lDetails != null ? this.getServiceClassStructure_DTOs(lDetails) : null;
		return serviceClassDetailsDTOs;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> getSelectServiceClassStructuresByParents(CopyOnWriteArrayList<Long> ids)
    {
		CompletableFuture<CopyOnWriteArrayList<ServiceClassStructure_DTO>> future = CompletableFuture.supplyAsync(() -> 
 		{
		CopyOnWriteArrayList<ServiceClassStructure> lDetails = serviceClassStructurePublicReadRepo.getSelectServiceClassesByParents(ids);
		CopyOnWriteArrayList<ServiceClassStructure_DTO> serviceClassDetailsDTOs = lDetails != null ? this.getServiceClassStructure_DTOs(lDetails) : null;
		return serviceClassDetailsDTOs;
		}, asyncExecutor);
 		return future;
	}
	
	private synchronized CopyOnWriteArrayList<ServiceClassStructure_DTO> getServiceClassStructure_DTOs(
			CopyOnWriteArrayList<ServiceClassStructure> lStructures) {
		ServiceClassStructure_DTO lDTO = null;
		CopyOnWriteArrayList<ServiceClassStructure_DTO> lStructureDTOs = new CopyOnWriteArrayList<ServiceClassStructure_DTO>();
		for (int i = 0; i < lStructures.size(); i++) {
			lDTO = getServiceClassStructure_DTO(lStructures.get(i));
			lStructureDTOs.add(lDTO);
		}
		return lStructureDTOs;
	}

	private synchronized ServiceClassStructure_DTO getServiceClassStructure_DTO(ServiceClassStructure lStructure) {
		ServiceClassStructure_DTO lDTO = new ServiceClassStructure_DTO();
		lDTO.setParServiceClassSeqNo(lStructure.getId().getParServiceClassSeqNo());
		lDTO.setServiceClassSeqNo(lStructure.getId().getServiceClassSeqNo());
		lDTO.setPartySeqNo(lStructure.getId().getPartySeqNo());
		return lDTO;
	}
}