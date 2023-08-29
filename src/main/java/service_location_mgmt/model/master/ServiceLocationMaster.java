package service_location_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the SERVICE_LOCATION_MASTER database table.
 * 
 */
@Entity
@Table(name = "SERVICE_LOCATION_MASTER")
public class ServiceLocationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ServiceLocationMasterPK id;

	@Column(name = "DURATION")
	private Float duration;

	@Column(name = "DURATION_SEQ_NO")
	private Long durSeqNo;

	public ServiceLocationMasterPK getId() {
		return id;
	}

	public void setId(ServiceLocationMasterPK id) {
		this.id = id;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public Long getDurSeqNo() {
		return durSeqNo;
	}

	public void setDurSeqNo(Long durSeqNo) {
		this.durSeqNo = durSeqNo;
	}

	public ServiceLocationMaster(ServiceLocationMasterPK id, Float duration, Long durSeqNo) {
		super();
		this.id = id;
		this.duration = duration;
		this.durSeqNo = durSeqNo;
	}

	public ServiceLocationMaster() {
		super();
	}

}