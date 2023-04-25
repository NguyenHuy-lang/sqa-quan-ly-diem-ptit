package sqa.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sqa.example.model.MonHoc;
import sqa.example.repository.MonHocRepository;
import sqa.example.service.MonHocService;

@SpringBootTest
public class MonHocServiceTest
{
    private MonHoc[] dummies = new MonHoc[] {
            new MonHoc(0, "Test Mon Hoc 0", 0.1, 0.1, 0.2, 0.2, 0.4),
            new MonHoc(1, "Test Mon Hoc 1", 0.2, 0.2, 0.2, 0.2, 0.2),
            new MonHoc(2, "Test Mon Hoc 2", 0.3, 0.3, 0.2, 0.1, 0.1),
    };

    @Mock
    private MonHocRepository monHocRepository;

    @InjectMocks
    private MonHocService monHocService;

    @BeforeEach
    public void setup() {
        when(monHocRepository.findAll()).thenReturn(Arrays.asList(dummies));
        when(monHocRepository.findById(0)).thenReturn(Optional.of(dummies[0]));
        when(monHocRepository.findById(1)).thenReturn(Optional.of(dummies[1]));
        when(monHocRepository.findById(2)).thenReturn(Optional.of(dummies[2]));
    }

    @Test
    public void findAll() {
        var entityList = monHocService.findAll();
        assertThat(entityList).hasSize(3);
    }

    @Test
    public void findById() {
        assertEquals(dummies[0], monHocService.findById(0));
    }

    public void update() {
        var newEntity = new MonHoc(0, "Test Mon Hoc 0", 0.2, 0.2, 0.2, 0.2, 0.2);
        when(monHocRepository.save(any(MonHoc.class))).thenReturn(newEntity);

        var updatedEntity = monHocService.update(newEntity);
        assertEquals(updatedEntity, newEntity);
    }

    @Test
    public void update_invalid() {
        var newEntity = new MonHoc(0, "Test Mon Hoc 0", 9.9, 9.9, 9.9, 9.9, 9.9);
        when(monHocRepository.save(any(MonHoc.class))).thenReturn(null);

        var updatedEntity = monHocService.update(newEntity);
        assertEquals(updatedEntity, null);
    }

    @Test
    public void update_notExist() {
        var newEntity = new MonHoc(999, "Test Mon Hoc 9", 0.2, 0.2, 0.2, 0.2, 0.2);
        when(monHocRepository.save(any(MonHoc.class))).thenReturn(null);

        var updatedEntity = monHocService.update(newEntity);
        assertEquals(updatedEntity, null);
    }

    @Test
    public void utils_isInvalidF() {
        var dummy = new MonHoc(1, "", 0.2, 0.2, 0.2, 0.2, 0.2);
        var expectedResult = false;

        assertEquals(MonHocService.isInvalid(dummy), expectedResult);
    }

    @Test
    public void utils_isInvalidT() {
        var dummy = new MonHoc(1, "", 1.0, 0.2, 0.2, 0.2, 0.2);
        var expectedResult = true;

        assertEquals(MonHocService.isInvalid(dummy), expectedResult);
    }
}