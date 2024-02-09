package efrei.app.place;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceRepository extends CrudRepository<Place, Integer> {
    List<Place> findAll();
}
