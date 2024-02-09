package efrei.app.place;

import efrei.app.event.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Event> events;
}
