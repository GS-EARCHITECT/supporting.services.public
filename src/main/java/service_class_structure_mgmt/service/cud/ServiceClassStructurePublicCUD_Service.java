package service_class_structure_mgmt.service.cud;

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
import service_class_structure_mgmt.model.master.ServiceClassStructurePK;
import service_class_structure_mgmt.model.repo.cud.ServiceClassStructurePublicCUD_Repo;

@Service("serviceClassStructurePublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceClassStructurePublicCUD_Service implements I_ServiceClassStructurePublicCUD_Service 
{

	@Autowired
	private ServiceClassStructurePublicCUD_Repo serviceClassStructurePublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<ServiceClassStructure_DTO> newServiceClassStructure(ServiceClassStructure_DTO lStructure) 
	{

		CompletableFuture<ServiceClassStructure_DTO> future = CompletableFuture.supplyAsync(() -> 
 		{
		ServiceClassStructure_DTO serviceClassStructureDTO = null;
		ServiceClassStructurePK serviceClassStructurePK = new ServiceClassStructurePK();
		serviceClassStructurePK.setParServiceClassSeqNo(lStructure.getParServiceClassSeqNo());
		serviceClassStructurePK.setServiceClassSeqNo(lStructure.getServiceClassSeqNo());
		serviceClassStructurePK.setPartySeqNo(lStructure.getPartySeqNo());
		
		if (!serviceClassStructurePublicCUDRepo.existsById(serviceClassStructurePK)) 
		{			
			serviceClassStructureDTO = getServiceClassStructure_DTO(
			serviceClassStructurePublicCUDRepo.save(this.setServiceClassStructure(lStructure)));
		}
		return serviceClassStructureDTO;
		}, asyncExecutor);
 		return future;

	}
	
	public CompletableFuture<Void> updServiceClassStructure(ServiceClassStructure_DTO lStructure) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		ServiceClassStructurePK serviceClassStructurePK = new ServiceClassStructurePK();
		serviceClassStructurePK.setParServiceClassSeqNo(lStructure.getParServiceClassSeqNo());
		serviceClassStructurePK.setServiceClassSeqNo(lStructure.getServiceClassSeqNo());
		serviceClassStructurePK.setPartySeqNo(lStructure.getPartySeqNo());

		if (serviceClassStructurePublicCUDRepo.existsById(serviceClassStructurePK)) 
		{
			serviceClassStructurePublicCUDRepo.save(this.setServiceClassStructure(lStructure));
		}
		return;
		}, asyncExecutor);
 		return future;
	}
		
	public CompletableFuture<Void> delAllServiceClassStructures() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		serviceClassStructurePublicCUDRepo.deleteAll();
		return;
		}, asyncExecutor);
 		return future;
	}

	public CompletableFuture<Void> delSelectServiceClassStructures(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> pids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		if (ids != null && pids != null) {
			serviceClassStructurePublicCUDRepo.delSelectServiceClassStructures(ids, pids);
		}
		return;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectServiceClassStructuresByParties(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		if (ids != null)
		{
			serviceClassStructurePublicCUDRepo.delSelectServiceClassStructuresByParties(ids);
		}
		return;
		}, asyncExecutor);
 		return future;
	}
	
	public CompletableFuture<Void> delSelectServiceClassStructuresByParents(CopyOnWriteArrayList<Long> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
 		{
		if (ids != null)
		{
			serviceClassStructurePublicCUDRepo.delSelectServiceClassStructuresByParents(ids);
		}
		return;
	}, asyncExecutor);
 		return future;
	}

	private synchronized ServiceClassStructure_DTO getServiceClassStructure_DTO(ServiceClassStructure lStructure) {
		ServiceClassStructure_DTO lDTO = new ServiceClassStructure_DTO();
		lDTO.setParServiceClassSeqNo(lStructure.getId().getParServiceClassSeqNo());
		lDTO.setServiceClassSeqNo(lStructure.getId().getServiceClassSeqNo());
		lDTO.setPartySeqNo(lStructure.getId().getPartySeqNo());
		return lDTO;
	}

	private synchronized ServiceClassStructure setServiceClassStructure(ServiceClassStructure_DTO lDTO) {
		ServiceClassStructure lStructure = new ServiceClassStructure();
		ServiceClassStructurePK serviceClassStructurePK = new ServiceClassStructurePK();
		serviceClassStructurePK.setParServiceClassSeqNo(lDTO.getParServiceClassSeqNo());
		serviceClassStructurePK.setServiceClassSeqNo(lDTO.getServiceClassSeqNo());
		serviceClassStructurePK.setPartySeqNo(lDTO.getPartySeqNo());
		lStructure.setId(serviceClassStructurePK);
		return lStructure;
	}
}