package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Manufacturer;
import com.pracownia.spring.services.ManufacturerService;
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
 * Manufacturer controller.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;


    /**
     * List all manufacturers.
     *
     */
    @RequestMapping(value = "/manufacturers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Manufacturer> list(Model model) {
        return manufacturerService.listAllManufacturers();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/manufacturers", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Manufacturer> redirect(Model model) {
        return manufacturerService.listAllManufacturers();
    }

    @RequestMapping(value = "/manufacturers/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Manufacturer> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return manufacturerService.listAllManufacturersPaging(pageNr, howManyOnPage.orElse(2));
    }


    /**
     * View a specific manufacturer by its id.
     *
     */
    @RequestMapping(value = "/manufacturer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Manufacturer getByPublicId(@PathVariable("id") Integer publicId) {
        return manufacturerService.getManufacturerById(publicId);
    }

    /**
     * View a specific manufacturer by its id.
     *
     */
    @RequestMapping(value = "/manufacturer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Manufacturer getByParamPublicId(@RequestParam("id") Integer publicId) {
        return manufacturerService.getManufacturerById(publicId);
    }

    /**
     * Save manufacturer to database.
     *
     */
    @RequestMapping(value = "/manufacturer", method = RequestMethod.POST)
    public ResponseEntity<Manufacturer> create(@RequestBody @Valid @NotNull Manufacturer manufacturer) {
        //manufacturer.setManufacturerId(UUID.randomUUID().toString());
        manufacturerService.saveManufacturer(manufacturer);
        return ResponseEntity.ok().body(manufacturer);
    }


    /**
     * Edit manufacturer in database.
     *
     */
    @RequestMapping(value = "/manufacturer", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Manufacturer manufacturer) {
        if(!manufacturerService.checkIfExist(manufacturer.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            manufacturerService.saveManufacturer(manufacturer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Delete manufacturer by its id.
     *
     */
    @RequestMapping(value = "/manufacturer/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(HttpServletResponse response, @PathVariable Integer id) {
        manufacturerService.deleteManufacturer(id);
        return new RedirectView("/api/manufacturers", true);
    }

}
