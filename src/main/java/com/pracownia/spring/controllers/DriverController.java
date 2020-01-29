package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Driver;
import com.pracownia.spring.services.DriverService;
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
 * Driver controller.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class DriverController {

    @Autowired
    private DriverService driverService;


    /**
     * List all drivers.
     *
     */
    @RequestMapping(value = "/drivers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Driver> list(Model model) {
        return driverService.listAllDrivers();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/drivers", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Driver> redirect(Model model) {
        return driverService.listAllDrivers();
    }

    @RequestMapping(value = "/drivers/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Driver> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return driverService.listAllDriversPaging(pageNr, howManyOnPage.orElse(2));
    }


    /**
     * View a specific driver by its id.
     *
     */
    @RequestMapping(value = "/driver/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Driver getByPublicId(@PathVariable("id") Integer publicId) {
        return driverService.getDriverById(publicId);
    }

    /**
     * View a specific driver by its id.
     *
     */
    @RequestMapping(value = "/driver", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Driver getByParamPublicId(@RequestParam("id") Integer publicId) {
        return driverService.getDriverById(publicId);
    }

    /**
     * Save driver to database.
     *
     */
    @RequestMapping(value = "/driver", method = RequestMethod.POST)
    public ResponseEntity<Driver> create(@RequestBody @Valid @NotNull Driver driver) {
        //driver.setDriverId(UUID.randomUUID().toString());
        driverService.saveDriver(driver);
        return ResponseEntity.ok().body(driver);
    }


    /**
     * Edit driver in database.
     *
     */
    @RequestMapping(value = "/driver", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Driver driver) {
        if(!driverService.checkIfExist(driver.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            driverService.saveDriver(driver);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Delete driver by its id.
     *
     */
    @RequestMapping(value = "/driver/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(HttpServletResponse response, @PathVariable Integer id) {
        driverService.deleteDriver(id);
        return new RedirectView("/api/drivers", true);
    }

}
