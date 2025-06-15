package co.devskills.springbootboilerplate.repository;

import co.devskills.springbootboilerplate.model.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<Presentation, String> {

}
