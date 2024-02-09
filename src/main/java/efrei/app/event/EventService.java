package efrei.app.event;

import efrei.app.place.Place;
import efrei.app.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PlaceRepository placeRepository;
    public Event getEventById(Integer id){
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> findAllEvents(){
        return eventRepository.findAll();
    }
    public ResponseEntity<?> saveEvent(Event event){
        try{
            Place savedPLace = placeRepository.save(event.getPlace());
            event.setPlace(savedPLace);
            eventRepository.save(event);
            return ResponseEntity.ok("Event created successfuly");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public ResponseEntity<?> findAndUpdateEvent(Integer id, Event event){
        Event eventAModifier = eventRepository.findById(id).orElseGet(null);
        if(eventAModifier != null){
            if(event.getName() != null){
                eventAModifier.setName(event.getName());
            }
            if (event.getDate() != null){
                eventAModifier.setDate(event.getDate());
            }
            eventRepository.save(eventAModifier);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<?> deleteEventById(Integer id){
        if(!eventRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eventRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
