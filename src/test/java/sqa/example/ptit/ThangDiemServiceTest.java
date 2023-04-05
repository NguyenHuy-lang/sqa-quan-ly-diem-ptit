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
import sqa.example.model.ThangDiem;
import sqa.example.repository.ThangDiemRepository;
import sqa.example.service.ThangDiemService;

@SpringBootTest
public class ThangDiemServiceTest 
{
	private ThangDiem[] dummies = new ThangDiem[] {
		new ThangDiem(0, 0.0, 3.0, "C", 1.0),
		new ThangDiem(1, 3.0, 6.0, "B", 2.0),
		new ThangDiem(2, 6.0, 9.0, "A", 3.0),
	};
	
	@Mock
    private ThangDiemRepository thangDiemRepository;
	
	@InjectMocks
    private ThangDiemService thangDiemService;

	@BeforeEach
    public void setup() {
		when(thangDiemRepository.findAll()).thenReturn(Arrays.asList(dummies));
		when(thangDiemRepository.findById(0)).thenReturn(Optional.of(dummies[0]));
		when(thangDiemRepository.findById(1)).thenReturn(Optional.of(dummies[1]));
	    when(thangDiemRepository.findById(2)).thenReturn(Optional.of(dummies[2]));
	}
	
	@Test
    @DisplayName("ThangDiem Service - Get All")
    public void testGetAll() {
        var entityList = thangDiemService.getAll();
		assertThat(entityList).hasSize(3);
    }
	
	@Test
    @DisplayName("ThangDiem Service - Get")
    public void testGet() {
	    assertEquals(dummies[0], thangDiemService.get(0));
    }
	
	@Test
    @DisplayName("ThangDiem Service - Create")
    public void testCreate() {
		var newEntity = new ThangDiem(4, 0.0, 10.0, "Test", 10.0);
		when(thangDiemRepository.save(any(ThangDiem.class))).thenReturn(newEntity);

		var createdNewMH = thangDiemService.create(newEntity);
        assertEquals(createdNewMH, createdNewMH);
    }
	
	@Test
    @DisplayName("ThangDiem Service - Create (Invalid)")
    public void testCreate_Invalid() {
		var newEntity = new ThangDiem(4, 0.0, 10.0, "Test", 10.0);
		when(thangDiemRepository.save(any(ThangDiem.class))).thenReturn(null);

		var createdNewMH = thangDiemService.create(newEntity);
        assertEquals(createdNewMH, null);
    }
	
	@Test
    @DisplayName("ThangDiem Service - Update")
    public void testUpdate() {
		var newEntity = new ThangDiem(0, 0.0, 2.0, "", 0.0);
		when(thangDiemRepository.save(any(ThangDiem.class))).thenReturn(newEntity);

		var updatedNewMH = thangDiemService.update(newEntity);
        assertEquals(updatedNewMH, newEntity);
    }
	
	@Test
    @DisplayName("ThangDiem Service - Update (Not Exist)")
    public void testUpdate_NotExist() {
		var newEntity = new ThangDiem(4, 0.0, 2.0, "", 0.0);
		when(thangDiemRepository.save(any(ThangDiem.class))).thenReturn(null);

		var updatedNewMH = thangDiemService.update(newEntity);
        assertEquals(updatedNewMH, null);
    }
	
	@Test
    @DisplayName("ThangDiem Service - Delete")
    public void testDelete() {
		thangDiemService.delete(0);
	    verify(thangDiemRepository, times(1)).delete(dummies[0]);
	}
}
