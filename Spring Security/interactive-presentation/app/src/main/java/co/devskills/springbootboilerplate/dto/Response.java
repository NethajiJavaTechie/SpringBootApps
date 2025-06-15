package co.devskills.springbootboilerplate.dto;

import co.devskills.springbootboilerplate.model.Presentation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String presentation_id;
}
