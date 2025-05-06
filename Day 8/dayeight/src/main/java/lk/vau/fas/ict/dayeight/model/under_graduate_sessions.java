package lk.vau.fas.ict.dayseven.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class under_graduate_sessions {
    @Id
    private String under_graduates_id;
    private String sessions_id;
    
    public under_graduate_sessions(String under_graduates_id, String sessions_id) {
        
        this.under_graduates_id = under_graduates_id;
        this.sessions_id = sessions_id;
    }
    public String getUnder_graduates_id() {
        return under_graduates_id;
    }
    public String getSessions_id() {
        return sessions_id;
    }
    public void setUnder_graduates_id(String under_graduates_id) {
        this.under_graduates_id = under_graduates_id;
    }
    public void setSessions_id(String sessions_id) {
        this.sessions_id = sessions_id;
    }

    
}
