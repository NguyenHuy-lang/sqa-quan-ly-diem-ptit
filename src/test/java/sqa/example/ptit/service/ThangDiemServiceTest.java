package sqa.example.ptit.service;

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
import sqa.example.model.ThangDiem;
import sqa.example.repository.ThangDiemRepository;
import sqa.example.service.ThangDiemService;

@SpringBootTest
public class ThangDiemServiceTest 
{
	/*
	private ThangDiem[] dummies = new ThangDiem[] {
		new ThangDiem(0, 0.0, 3.0, "C", 1.0),
		new ThangDiem(1, 3.0, 6.0, "B", 2.0),
		new ThangDiem(2, 6.0, 9.0, "A", 3.0),
	};
	
	@Mock private ThangDiemRepository thangDiemRepository;
	@InjectMocks private ThangDiemService thangDiemService;

	@BeforeEach
    public void setup() {
		when(thangDiemRepository.findAll()).thenReturn(Arrays.asList(dummies));
		when(thangDiemRepository.findById(0)).thenReturn(Optional.of(dummies[0]));
		when(thangDiemRepository.findById(1)).thenReturn(Optional.of(dummies[1]));
	    when(thangDiemRepository.findById(2)).thenReturn(Optional.of(dummies[2]));
		when(thangDiemRepository.findByName("C")).thenReturn(dummies[0]);
	}
	
	@Test
    public void findAll() {
        var entityList = thangDiemService.findAll();
		assertThat(entityList).hasSize(3);
    }
	
	@Test
    public void findById() {
	    assertEquals(dummies[0], thangDiemService.findById(0));
    }
	
	@Test
    public void findByName() {
	    assertEquals(dummies[0], thangDiemService.findByName("C"));
    }
	
	@Test
    public void update() {
		var newEntity = new ThangDiem(0, 0.0, 2.0, "", 0.0);
		when(thangDiemRepository.save(any(ThangDiem.class))).thenReturn(newEntity);

		var updatedNewEntity = thangDiemService.update(newEntity);
        assertEquals(updatedNewEntity, newEntity);
    }
	
	@Test
    public void update_invalid() {
		var newEntity = new ThangDiem(4, 0.0, 2.0, "", 0.0);
		when(thangDiemRepository.save(any(ThangDiem.class))).thenReturn(null);

		var updatedNewEntity = thangDiemService.update(newEntity);
        assertEquals(updatedNewEntity, null);
    }
	
	@Test
    public void update_notExist() {
		var newEntity = new ThangDiem(9999, 0.0, 2.0, "", 0.0);
		when(thangDiemRepository.save(any(ThangDiem.class))).thenReturn(null);

		var updatedNewEntity = thangDiemService.update(newEntity);
        assertEquals(updatedNewEntity, null);
    }
	*/
}
