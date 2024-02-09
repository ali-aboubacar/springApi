package efrei.app.event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private EventRepository eventRepository;

    public Event getEventById(Integer id){
        return eventRepository.findById(id).orElse(null);
    }

    public void saveEvent(Event event){
        eventRepository.save(event);
    }

    public ResponseEntity<?> deleteEventById(Integer id){
        if(!eventRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eventRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
