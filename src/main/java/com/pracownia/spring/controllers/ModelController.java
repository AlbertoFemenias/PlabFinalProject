package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Model;
import com.pracownia.spring.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Model controller.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ModelController {

    @Autowired
    private ModelService modelService;


    /**
     * List all models.
     *
     */
    @RequestMapping(value = "/models", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Model> list(Model model) {
        return modelService.listAllModels();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/models", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Model> redirect(Model model) {
        return modelService.listAllModels();
    }

    @RequestMapping(value = "/models/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Model> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return modelService.listAllModelsPaging(pageNr, howManyOnPage.orElse(2));
    }


    /**
     * View a specific model by its id.
     *
     */
    @RequestMapping(value = "/model/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Model getByPublicId(@PathVariable("id") Integer publicId) {
        return modelService.getModelById(publicId);
    }

    /**
     * View a specific model by its id.
     *
     */
    @RequestMapping(value = "/model", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Model getByParamPublicId(@RequestParam("id") Integer publicId) {
        return modelService.getModelById(publicId);
    }

    /**
     * Save model to database.
     *
     */
    @RequestMapping(value = "/model", method = RequestMethod.POST)
    public ResponseEntity<Model> create(@RequestBody @Valid @NotNull Model model) {
        //model.setModelId(UUID.randomUUID().toString());
        modelService.saveModel(model);
        return ResponseEntity.ok().body(model);
    }


    /**
     * Edit model in database.
     *
     */
    @RequestMapping(value = "/model", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Model model) {
        if(!modelService.checkIfExist(model.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            modelService.saveModel(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Delete model by its id.
     *
     */
    @RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(HttpServletResponse response, @PathVariable Integer id) {
        modelService.deleteModel(id);
        return new RedirectView("/api/models", true);
    }

}
