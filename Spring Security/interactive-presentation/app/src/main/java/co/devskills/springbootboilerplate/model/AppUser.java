package co.devskills.springbootboilerplate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AppUser")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;
}
