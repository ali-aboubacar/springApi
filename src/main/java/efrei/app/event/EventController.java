package efrei.app.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.InternalFrameEvent;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping
    public void createEvent(@RequestBody Event event){
        eventService.saveEvent(event);
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