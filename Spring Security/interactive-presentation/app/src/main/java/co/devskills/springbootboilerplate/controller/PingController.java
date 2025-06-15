package co.devskills.springbootboilerplate.controller;

import co.devskills.springbootboilerplate.dto.PingResponse;
import co.devskills.springbootboilerplate.dto.PollResponse;
import co.devskills.springbootboilerplate.dto.Response;
import co.devskills.springbootboilerplate.model.Poll;
import co.devskills.springbootboilerplate.model.Presentation;
import co.devskills.springbootboilerplate.service.PresentationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class PingController {

    @Autowired
    PresentationService presentationService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public PingResponse healthCheck() {
        return new PingResponse("The service is up and running");
    }

    @PostMapping("/presentations")
    public ResponseEntity<Response> createPresentation(@RequestBody Presentation presentation) {
        List<Poll> polls = presentation.getPolls();

        if (polls == null || polls.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        boolean isInvalid = polls.stream().anyMatch(poll ->
                poll.getQuestion() == null || poll.getOptions() == null || poll.getOptions().size() < 2 ||
                        poll.getOptions().stream().anyMatch(option -> option.getKey() == null || option.getValue() == null)
        );

        if (isInvalid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        presentation.setCurrentPollIndex(0);
        presentation.setId(UUID.randomUUID().toString());
        presentation = presentationService.savePresentation(presentation);
        Response response = new Response(null);
        if (presentation != null) {
            response = new Response(presentation.getId());
        }

        log.info("Presentation saved");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/presentations/{presentation_id}/polls/current")
    public ResponseEntity<?> getCurrentPoll(@PathVariable String presentation_id) {
        Presentation presentation = presentationService.getPresentationById(presentation_id);

        if (presentation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Presentation not found"));
        }

        log.info("Loaded poll count: {}", presentation.getPolls().size());
        log.info("Loaded currentPollIndex: {}", presentation.getCurrentPollIndex());

        Integer index = presentation.getCurrentPollIndex();
        if (index == null || index < 0 || index > presentation.getPolls().size()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Poll currentPoll = presentation.getPolls().get(index);

        PollResponse response = new PollResponse(
                currentPoll.getId(),
                currentPoll.getQuestion(),
                currentPoll.getOptions());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/presentations/{id}/polls/current")
    public ResponseEntity<PollResponse> advanceToNextPoll(@PathVariable String id) {
        Presentation presentation = presentationService.getPresentationById(id);

        if (presentation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        int current = presentation.getCurrentPollIndex();
        if (current < presentation.getPolls().size() - 1) {
            presentation.setCurrentPollIndex(current + 1);
            presentationService.savePresentation(presentation);
        }

        Poll nextPoll = presentation.getPolls().get(presentation.getCurrentPollIndex());

        PollResponse response = new PollResponse(
                nextPoll.getId(),
                nextPoll.getQuestion(),
                nextPoll.getOptions());
        return ResponseEntity.ok(response);
    }
}
