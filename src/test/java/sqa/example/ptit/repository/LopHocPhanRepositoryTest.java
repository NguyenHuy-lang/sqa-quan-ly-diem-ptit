package sqa.example.ptit.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.example.repository.LopHocPhanRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "sqa")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LopHocPhanRepositoryTest {
	@Autowired private LopHocPhanRepository repository;

	@Test
	public void findBySinhVienAndLopHocPhanId() {
		var result = repository.findAllByNienKhoaNganhNamHocKyHocMonHocId(1);
		assertEquals(result.size(), 4);
	}
}