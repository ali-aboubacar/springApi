package efrei.app.ticket;

import efrei.app.event.Event;
import efrei.app.event.EventService;
import efrei.app.user.User;
import efrei.app.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final UserService userService;
    private final EventService eventService;
    @Autowired
    public TicketController(TicketService ticketService,
                            UserService userService,
                            EventService eventService){
        this.ticketService = ticketService;
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping
    public List<Ticket> getAllTIckects(){
        return ticketService.findAllTickets();
    }
    @PostMapping("/buy")
    public ResponseEntity<String> buyTicket(@RequestParam Integer userId,
                                            @RequestParam Integer eventId){
        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId) ;

        try{
            ticketService.buyTicket(user, event);
            return ResponseEntity.ok("Ticket purchased successfuly");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> deleteTickect(@PathVariable Integer ticketId){
        return ticketService.deleteTicketById(ticketId);
    }
}
