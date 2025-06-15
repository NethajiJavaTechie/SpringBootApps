package co.devskills.springbootboilerplate.dto;

import co.devskills.springbootboilerplate.model.Option;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PollResponse {
    private String poll_id;
    private String question;
    private List<Option> options;
}
