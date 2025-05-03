package lk.vau.fas.ict.daysix.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity
public class Insurance {
    @Id
	@Column(name="Insurance_id")
    private int id;
    @Column(name="Insurance_type")
    private String type;

    @OneToOne
	private Employee employees;
}
