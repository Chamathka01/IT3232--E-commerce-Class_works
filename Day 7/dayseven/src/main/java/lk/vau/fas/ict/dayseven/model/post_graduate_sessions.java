package lk.vau.fas.ict.dayseven.model;

import jakarta.persistence.Entity;

@Entity
public class post_graduate_sessions {
    private int post_graduates_id;
	private int sessions_id;

    public post_graduate_sessions(int post_graduates_id, int sessions_id) {
		super();
		this.post_graduates_id = post_graduates_id;
		this.sessions_id = sessions_id;
	}

	public int getPost_graduates_id() {
		return post_graduates_id;
	}

	public void setPost_graduates_id(int post_graduates_id) {
		this.post_graduates_id = post_graduates_id;
	}

	public int getSessions_id() {
		return sessions_id;
	}

	public void setSessions_id(int sessions_id) {
		this.sessions_id = sessions_id;
	}
}
