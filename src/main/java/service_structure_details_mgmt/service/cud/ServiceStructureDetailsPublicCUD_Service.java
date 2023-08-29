package service_structure_details_mgmt.service.cud;

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
import service_structure_details_mgmt.model.repo.cud.ServiceStructureDetailsPublicCUD_Repo;

@Service("serviceStructureDetailsPublicCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ServiceStructureDetailsPublicCUD_Service implements I_ServiceStructureDetailsPublicCUD_Service 
{

	@Autowired
	private ServiceStructureDetailsPublicCUD_Repo serviceStructureDetailsPublicCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	public CompletableFuture<ServiceStructureDetail_DTO> newServiceStructureDetail(ServiceStructureDetail_DTO lStructure) 
	{
		CompletableFuture<ServiceStructureDetail_DTO> future = CompletableFuture.supplyAsync(() -> {
		ServiceStructureDetail_DTO serviceStructureDetailDTO = null;
		ServiceStructureDetailPK ServiceStructureDetailPK = new ServiceStructureDetailPK();
		ServiceStructureDetailPK.setParServiceClassSeqNo(lStructure.getParServiceClassSeqNo());
		ServiceStructureDetailPK.setServiceClassSeqNo(lStructure.getServiceClassSeqNo());
		ServiceStructureDetailPK.setParServiceSeqNo(lStructure.getParServiceSeqNo());
		ServiceStructureDetailPK.setServiceSeqNo(lStructure.getServiceSeqNo());
		
		if (!serviceStructureDetailsPublicCUDRepo.existsById(ServiceStructureDetailPK)) 
		{			
			serviceStructureDetailDTO = getServiceStructureDetail_DTO(
			serviceStructureDetailsPublicCUDRepo.save(this.setServiceStructureDetail(lStructure)));
		}
		return serviceStructureDetailDTO;
		}, asyncExecutor);
return future;
	}
	
	public CompletableFuture<Void> updServiceStructureDetail(ServiceStructureDetail_DTO lStructure) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		ServiceStructureDetailPK ServiceStructureDetailPK = new ServiceStructureDetailPK();
		ServiceStructureDetailPK.setParServiceClassSeqNo(lStructure.getParServiceClassSeqNo());
		ServiceStructureDetailPK.setServiceClassSeqNo(lStructure.getServiceClassSeqNo());
		ServiceStructureDetailPK.setParServiceSeqNo(lStructure.getParServiceSeqNo());
		ServiceStructureDetailPK.setServiceSeqNo(lStructure.getServiceSeqNo());

		if (serviceStructureDetailsPublicCUDRepo.existsById(ServiceStructureDetailPK)) 
		{
			serviceStructureDetailsPublicCUDRepo.save(this.setServiceStructureDetail(lStructure));
		}
		return;
		}, asyncExecutor);
return future;
	}

		
	public CompletableFuture<Void> delAllServiceStructureDetails() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		serviceStructureDetailsPublicCUDRepo.deleteAll();
		return;
		}, asyncExecutor);
		return future;		
	}

	public CompletableFuture<Void> delSelectServiceStructureDetails(CopyOnWriteArrayList<ServiceStructureDetailPK> ids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		if (ids != null) 
		{
			serviceStructureDetailsPublicCUDRepo.deleteAllById(ids);
		}
		return;
		}, asyncExecutor);
		return future;		
	}
	
	
	public CompletableFuture<Void> delSelectServiceStructureDetailsByServices(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> cids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		if (ids != null)
		{
			serviceStructureDetailsPublicCUDRepo.delSelectServiceStructureDetailsByServices(ids, cids);
		}
		return;
		}, asyncExecutor);
		return future;		
	}
	
	public CompletableFuture<Void> delSelectServiceStructureDetailsByParents(CopyOnWriteArrayList<Long> pids) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {			
		if (pids != null)
		{
			serviceStructureDetailsPublicCUDRepo.delSelectServiceStructureDetailsByParents(pids);
		}
		return;
		}, asyncExecutor);
		return future;		
	}
	
	private synchronized ServiceStructureDetail_DTO getServiceStructureDetail_DTO(ServiceStructureDetail lStructure) {
		ServiceStructureDetail_DTO lDTO = new ServiceStructureDetail_DTO();
		lDTO.setParServiceClassSeqNo(lStructure.getId().getParServiceClassSeqNo());
		lDTO.setServiceClassSeqNo(lStructure.getId().getServiceClassSeqNo());
		lDTO.setParServiceSeqNo(lStructure.getId().getParServiceSeqNo());
		lDTO.setServiceSeqNo(lStructure.getId().getServiceSeqNo());
		return lDTO;
	}

	private synchronized ServiceStructureDetail setServiceStructureDetail(ServiceStructureDetail_DTO lDTO) 
	{
		ServiceStructureDetail lStructure = new ServiceStructureDetail();
		ServiceStructureDetailPK ServiceStructureDetailPK = new ServiceStructureDetailPK();
		ServiceStructureDetailPK.setParServiceClassSeqNo(lDTO.getParServiceClassSeqNo());
		ServiceStructureDetailPK.setServiceClassSeqNo(lDTO.getServiceClassSeqNo());
		ServiceStructureDetailPK.setParServiceSeqNo(lDTO.getParServiceSeqNo());
		ServiceStructureDetailPK.setServiceSeqNo(lDTO.getServiceSeqNo());
		lStructure.setId(ServiceStructureDetailPK);
		return lStructure;
	}
}