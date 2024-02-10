package efrei.app.ticket;

import efrei.app.event.Event;
import efrei.app.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<?> deleteTicketById(Integer id){
        if(!ticketRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<Ticket> findAllTickets(){
        return ticketRepository.findAll();
    }
    public List<Ticket> findAllTicketsByUser(Integer id){
        return ticketRepository.findByUser_Id(id);
    }
    public void buyTicket(User user, Event event){
        LocalDate eventDate = event.getDate();

        if (ticketRepository.existsByUserAndEvent_Date(user, eventDate)){
            throw new RuntimeException(" User already has a ticket for this event! ");
        }
        Ticket newTicket = new Ticket();
        newTicket.setUser(user);
        newTicket.setEvent(event);
        ticketRepository.save(newTicket);
    }
}
