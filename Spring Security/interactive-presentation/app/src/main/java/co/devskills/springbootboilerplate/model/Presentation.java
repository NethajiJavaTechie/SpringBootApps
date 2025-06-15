package co.devskills.springbootboilerplate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Presentation {
    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "presentation_id")
    private List<Poll> polls;

    private Integer currentPollIndex;
}
