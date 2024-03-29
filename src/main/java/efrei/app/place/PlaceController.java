package efrei.app.place;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @GetMapping("/{placeId}")
    public ResponseEntity<Place> findPlaceByPlaceId(@PathVariable Integer placeId){
        Place place = placeService.getPlaceById(placeId);
        if(place == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(place, HttpStatus.OK);
    }
    @GetMapping
    public List<Place> getAllPLaces(){
        return placeService.findAllPlaces();
    }
    @PutMapping("/{placeId}")
    public ResponseEntity<?> updatePlace(@PathVariable Integer placeId,
                                         @RequestBody Place place){
        return placeService.findAndUpdatePLcae(placeId,place);
    }
//    @PostMapping
//    public void createPlace(@Valid @RequestBody Place place){
//        placeService.savePlace(place);
//    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<?> deletePlace(@PathVariable Integer placeId){
        return placeService.deletePlaceById(placeId);
    }
}
