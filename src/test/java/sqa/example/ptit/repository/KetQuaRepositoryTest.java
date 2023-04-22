package sqa.example.ptit.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.example.model.KetQua;
import sqa.example.repository.KetQuaRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "sqa")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class KetQuaRepositoryTest {
	@Autowired private KetQuaRepository repository;

	@Test
	public void findBySinhVienAndLopHocPhanId() {
		var expectedResult = new KetQua(1, 9.0, 9.0, 9.0, 9.0, 9.0, 0.0, 0.0, "", null, null);
		var result = repository.findBySinhVienAndLopHocPhanId(1, 1);
		
		assertEquals(result.getId(),		 expectedResult.getId());
		assertEquals(result.getDiemCC(),	 expectedResult.getDiemCC());
		assertEquals(result.getDiemTH(),	 expectedResult.getDiemTH());
		assertEquals(result.getDiemKT(),	 expectedResult.getDiemKT());
		assertEquals(result.getDiemBT(),	 expectedResult.getDiemBT());
		assertEquals(result.getDiemCuoiKy(), expectedResult.getDiemCuoiKy());
	}
}