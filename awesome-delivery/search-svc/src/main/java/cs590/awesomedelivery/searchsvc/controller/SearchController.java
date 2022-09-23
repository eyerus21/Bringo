package cs590.awesomedelivery.searchsvc.controller;

import cs590.awesomedelivery.searchsvc.domain.Restaurant;
import cs590.awesomedelivery.searchsvc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable String id){
        return new ResponseEntity<>(searchService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> search(
            @RequestParam(value="restaurant", required = false) String restaurantName,
            @RequestParam(value="menu", required = false) String menuName){

        if((restaurantName != null && !restaurantName.isBlank()) &&
                (menuName == null || menuName.isBlank())) {
            return new ResponseEntity<>(searchService.findByRestaurantName(restaurantName), HttpStatus.OK);
        }
        else if((restaurantName == null || restaurantName.isBlank()) &&
                (menuName != null && !menuName.isBlank())) {
            return new ResponseEntity<>(searchService.findByMenusName(menuName), HttpStatus.OK);
        }
        else if((restaurantName != null && !restaurantName.isBlank()) &&
                (menuName != null && !menuName.isBlank())) {
            return new ResponseEntity<>(searchService.findByNameAndMenusName(restaurantName, menuName), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(searchService.findAll(), HttpStatus.OK);
        }
    }
}
