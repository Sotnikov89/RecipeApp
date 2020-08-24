package com.example.recipeapp.services;

import com.example.recipeapp.model.UnitOfMeasure;
import com.example.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultUnitOfMeasureService implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public List<UnitOfMeasure> getAllUnitOfMeasure() {
        return unitOfMeasureRepository.findAll();
    }
}
