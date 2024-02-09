package efrei.app.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public Place getPlaceById(Integer id){
        return placeRepository.findById(id).orElseGet(null);
    }

    public List<Place> findAllPlaces(){
        return placeRepository.findAll();
    }
    public void savePlace(Place place){
        placeRepository.save(place);
    }

    public ResponseEntity<?> deletePlaceById(Integer id){
        if(!placeRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        placeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
