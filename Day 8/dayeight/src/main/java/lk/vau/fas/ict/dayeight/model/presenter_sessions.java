package lk.vau.fas.ict.dayseven.model;
import jakarta.persistence.Entity;

@Entity
public class presenter_sessions {
    private int presenters_id;
	private int sessions_id;
	
	public presenter_sessions(int presenters_id, int sessions_id) {
		super();
		this.presenters_id = presenters_id;
		this.sessions_id = sessions_id;
	}

	public int getPresenters_id() {
		return presenters_id;
	}

	public void setPresenters_id(int presenters_id) {
		this.presenters_id = presenters_id;
	}

	public int getSessions_id() {
		return sessions_id;
	}

	public void setSessions_id(int sessions_id) {
		this.sessions_id = sessions_id;
	}

}
