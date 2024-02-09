package efrei.app.ticket;

import efrei.app.event.Event;
import efrei.app.user.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findAll();
    Boolean existsByUserAndEvent_Date(User user, LocalDate eventDate);
}
