package co.devskills.springbootboilerplate.service;

import co.devskills.springbootboilerplate.exception.PresentationNotFoundException;
import co.devskills.springbootboilerplate.model.Presentation;
import co.devskills.springbootboilerplate.repository.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PresentationService {

    @Autowired
    PresentationRepository presentationRepository;

    public Presentation getPresentationById(String id)
    {
        return presentationRepository.findById(id)
                .orElseThrow(()->new PresentationNotFoundException("Presentation not found with ID: "+id));
    }

    public Presentation savePresentation(Presentation presentation)
    {
        return presentationRepository.save(presentation);
    }

    public Collection<Presentation> getAllPresentations()
    {
        return presentationRepository.findAll();
    }


}
