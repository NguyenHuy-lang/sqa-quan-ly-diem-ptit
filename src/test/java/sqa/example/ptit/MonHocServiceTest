package sqa.example.ptit;

import java.util.Arrays;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    @DisplayName("MonHoc Service - Get All")
    public void testGetAll() {
        var entityList = monHocService.getAll();
		assertThat(entityList).hasSize(3);
    }
	
	@Test
    @DisplayName("MonHoc Service - Get")
    public void testGet() {
	    assertEquals(dummies[0], monHocService.get(0));
    }
	
	@Test
    @DisplayName("MonHoc Service - Create")
    public void testCreate() {
		var newEntity = new MonHoc(4, "Test Mon Hoc 5", 0.1, 0.1, 0.1, 0.1, 0.6);
		when(monHocRepository.save(any(MonHoc.class))).thenReturn(newEntity);

		var createdEntity = monHocService.create(newEntity);
        assertEquals(createdEntity, createdEntity);
    }
	
	@Test
    @DisplayName("MonHoc Service - Create (Invalid)")
    public void testCreate_Invalid() {
		var newEntity = new MonHoc(5, "Test Mon Hoc 5", 0.0, 0.0, 0.0, 0.0, 999.999);
		when(monHocRepository.save(any(MonHoc.class))).thenReturn(null);

		var createdEntity = monHocService.create(newEntity);
        assertEquals(createdEntity, null);
	}
	
    @DisplayName("MonHoc Service - Update")
    public void testUpdate() {
		var newEntity = new MonHoc(0, "Test Mon Hoc 0", 0.2, 0.2, 0.2, 0.2, 0.2);
		when(monHocRepository.save(any(MonHoc.class))).thenReturn(newEntity);

		var updatedEntity = monHocService.update(newEntity);
        assertEquals(updatedEntity, newEntity);
    }
	
	@Test
    @DisplayName("MonHoc Service - Update (Not Exist)")
    public void testUpdate_NotExist() {
		var newEntity = new MonHoc(9, "Test Mon Hoc 9", 0.2, 0.2, 0.2, 0.2, 0.2);
		when(monHocRepository.save(any(MonHoc.class))).thenReturn(null);

		var updatedEntity = monHocService.update(newEntity);
        assertEquals(updatedEntity, null);
    }
	
	@Test
    @DisplayName("MonHoc Service - Delete")
    public void testDelete() {
		monHocService.delete(0);
	    verify(monHocRepository, times(1)).delete(dummies[0]);
	}
}
