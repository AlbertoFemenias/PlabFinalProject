package com.pracownia.spring.services;

import com.pracownia.spring.entities.Model;
import com.pracownia.spring.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Model service implement.
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Iterable<Model> listAllModelsPaging(Integer pageNr, Integer howManyOnPage) {
        return modelRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Iterable<Model> listAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public Model getModelById(Integer id) {
        return modelRepository.findOne(id);
    }

    @Override
    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public void deleteModel(Integer id) {
        modelRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (modelRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
