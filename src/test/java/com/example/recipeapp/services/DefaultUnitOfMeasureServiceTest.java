package com.example.recipeapp.services;

import com.example.recipeapp.model.UnitOfMeasure;
import com.example.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultUnitOfMeasureServiceTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    DefaultUnitOfMeasureService defaultUnitOfMeasureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        defaultUnitOfMeasureService = new DefaultUnitOfMeasureService(unitOfMeasureRepository);
    }

    @Test
    void getAllUnitOfMeasure() {
        List<UnitOfMeasure> unitOfMeasures = List.of(UnitOfMeasure.builder().id(1L).build(),
                UnitOfMeasure.builder().id(2L).build(),
                UnitOfMeasure.builder().id(3L).build());
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        assertEquals(defaultUnitOfMeasureService.getAllUnitOfMeasure().size(), 3);
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}