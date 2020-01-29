package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.License;
import com.pracownia.spring.services.LicenseService;
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
 * License controller.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;


    /**
     * List all licenses.
     *
     */
    @RequestMapping(value = "/licenses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<License> list(Model model) {
        return licenseService.listAllLicenses();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/licenses", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<License> redirect(Model model) {
        return licenseService.listAllLicenses();
    }

    @RequestMapping(value = "/licenses/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<License> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return licenseService.listAllLicensesPaging(pageNr, howManyOnPage.orElse(2));
    }


    /**
     * View a specific license by its id.
     *
     */
    @RequestMapping(value = "/license/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public License getByPublicId(@PathVariable("id") Integer publicId) {
        return licenseService.getLicenseById(publicId);
    }

    /**
     * View a specific license by its id.
     *
     */
    @RequestMapping(value = "/license", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public License getByParamPublicId(@RequestParam("id") Integer publicId) {
        return licenseService.getLicenseById(publicId);
    }

    /**
     * Save license to database.
     *
     */
    @RequestMapping(value = "/license", method = RequestMethod.POST)
    public ResponseEntity<License> create(@RequestBody @Valid @NotNull License license) {
        //license.setLicenseId(UUID.randomUUID().toString());
        licenseService.saveLicense(license);
        return ResponseEntity.ok().body(license);
    }


    /**
     * Edit license in database.
     *
     */
    @RequestMapping(value = "/license", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull License license) {
        if(!licenseService.checkIfExist(license.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            licenseService.saveLicense(license);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Delete license by its id.
     *
     */
    @RequestMapping(value = "/license/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(HttpServletResponse response, @PathVariable Integer id) {
        licenseService.deleteLicense(id);
        return new RedirectView("/api/licenses", true);
    }

}
