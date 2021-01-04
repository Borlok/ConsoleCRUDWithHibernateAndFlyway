package com.borlok.service;

import com.borlok.model.Specialty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpecialtyServiceTests {
    private   String name = "Check";
    @Mock
    private Specialty specialty;

    @Mock
    private Specialty returnedSpecialty;

    @Mock
    private List<Specialty> specialties;

    @Spy
    private SpecialtyService specialtyService;

    @Before
    public void setUp() {
        when(specialty.getName()).thenReturn(name);
    }

    @Test
    public void getSpecialtyTests() {
        assertEquals(name, specialty.getName());
        assertEquals(0,specialty.getId());
    }

    @Test
    public void createTest () {
        doReturn(returnedSpecialty).when(specialtyService).create(specialty);
        assertEquals(returnedSpecialty, specialtyService.create(specialty));
    }

    @Test
    public void getAllTest () {
        doReturn(specialties).when(specialtyService).getAll();
        assertEquals(specialties, specialtyService.getAll());
    }

    @Test
    public void updateTest () {
        doReturn(returnedSpecialty).when(specialtyService).update(specialty,15);
        assertEquals(returnedSpecialty, specialtyService.update(specialty, 15));
    }

}
