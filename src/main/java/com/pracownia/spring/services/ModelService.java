package com.pracownia.spring.services;

import com.pracownia.spring.entities.Model;

public interface ModelService {

    Iterable<Model> listAllModels();

    Model getModelById(Integer id);

    Model saveModel(Model model);

    void deleteModel(Integer id);

    Boolean checkIfExist(Integer id);

    public Iterable<Model> listAllModelsPaging(Integer pageNr, Integer howManyOnPage);

}
