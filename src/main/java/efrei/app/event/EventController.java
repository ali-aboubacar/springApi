package efrei.app.event;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.InternalFrameEvent;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping
    public void createEvent(@Valid @RequestBody Event event){
        eventService.saveEvent(event);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Integer eventId,
                                         @RequestBody Event event){
        return eventService.findAndUpdateEvent(eventId, event);
    }
    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.findAllEvents();
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<Event> findEventById(@PathVariable Integer eventId){
        Event event = eventService.getEventById(eventId);
        if(event == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer eventId){
        return eventService.deleteEventById(eventId);
    }
}
