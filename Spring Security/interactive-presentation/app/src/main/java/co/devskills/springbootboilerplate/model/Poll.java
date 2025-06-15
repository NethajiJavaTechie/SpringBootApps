package co.devskills.springbootboilerplate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    private List<Option> options;
}
