package service_location_mgmt.model.dto;

import java.io.Serializable;

public class ServiceLocationMaster_DTO implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6869153238430065831L;
	private Long serviceSeqNo;
	private Long locationSeqNo;
	private Long partySeqNo;
	private Float duration;
	private Long durSeqNo;

	public Long getServiceSeqNo() {
		return serviceSeqNo;
	}

	public void setServiceSeqNo(Long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}

	public Long getLocationSeqNo() {
		return locationSeqNo;
	}

	public void setLocationSeqNo(Long locationSeqNo) {
		this.locationSeqNo = locationSeqNo;
	}

	public Long getPartySeqNo() {
		return partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) 
	{
		this.duration = duration;
	}

	public Long getDurSeqNo() 
	{
		return durSeqNo;
	}

	public void setDurSeqNo(Long durSeqNo) {
		this.durSeqNo = durSeqNo;
	}

	public ServiceLocationMaster_DTO(Long serviceSeqNo, Long locationSeqNo, Long partySeqNo, Float duration,
			Long durSeqNo) {
		super();
		this.serviceSeqNo = serviceSeqNo;
		this.locationSeqNo = locationSeqNo;
		this.partySeqNo = partySeqNo;
		this.duration = duration;
		this.durSeqNo = durSeqNo;
	}

	public ServiceLocationMaster_DTO() {
		super();
	}

}