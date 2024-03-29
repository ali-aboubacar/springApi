package efrei.app.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import efrei.app.place.Place;
import efrei.app.ticket.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String name;


    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Place place;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
