package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Car;
import com.pracownia.spring.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Car controller.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;


    /**
     * List all cars.
     *
     */
    @RequestMapping(value = "/cars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Car> list(Model model) {
        return carService.listAllCars();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/cars", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Car> redirect(Model model) {
        return carService.listAllCars();
    }

    @RequestMapping(value = "/cars/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Car> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return carService.listAllCarsPaging(pageNr, howManyOnPage.orElse(2));
    }


    /**
     * View a specific car by its id.
     *
     */
    @RequestMapping(value = "/car/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Car getByPublicId(@PathVariable("id") Integer publicId) {
        return carService.getCarById(publicId);
    }

    /**
     * View a specific car by its id.
     *
     */
    @RequestMapping(value = "/car", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Car getByParamPublicId(@RequestParam("id") Integer publicId) {
        return carService.getCarById(publicId);
    }

    /**
     * Save car to database.
     *
     */
    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<Car> create(@RequestBody @Valid @NotNull Car car) {
        //car.setCarId(UUID.randomUUID().toString());
        carService.saveCar(car);
        return ResponseEntity.ok().body(car);
    }


    /**
     * Edit car in database.
     *
     */
    @RequestMapping(value = "/car", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Car car) {
        if(!carService.checkIfExist(car.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            carService.saveCar(car);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Delete car by its id.
     *
     */
    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(HttpServletResponse response, @PathVariable Integer id) {
        carService.deleteCar(id);
        return new RedirectView("/api/cars", true);
    }

}
