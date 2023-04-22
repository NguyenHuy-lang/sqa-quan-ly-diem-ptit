package sqa.example.ptit.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.example.model.KyHoc;
import sqa.example.model.MonHoc;
import sqa.example.model.NamHoc;
import sqa.example.model.NamHocKyHoc;
import sqa.example.model.Nganh;
import sqa.example.model.NienKhoa;
import sqa.example.model.NienKhoaNganh;
import sqa.example.model.NienKhoaNganhNamHocKyHoc;
import sqa.example.model.NienKhoaNganhNamHocKyHocMonHoc;
import sqa.example.repository.NienKhoaNganhNamHocKyHocMonHocRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "sqa")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NienKhoaNganhNamHocKyHocMonHocRepositoryTest {
	@Autowired private NienKhoaNganhNamHocKyHocMonHocRepository repository;
	
	@Test
	public void findByNienKhoaNganhAndNamHocKyHocId() {
		var expectedNienKhoa = new NienKhoa(1, "d19");
		var expectedNganh = new Nganh(1, "cong nghe thong tin");
		var expectedNamHoc = new NamHoc(1, "d19");
		var expectedKyHoc = new KyHoc(1, "ki 1");
		var expectedNienKhoaNganh = new NienKhoaNganh(1, expectedNienKhoa, expectedNganh);
		var expectedNamHocKyHoc = new NamHocKyHoc(1, expectedNamHoc, expectedKyHoc);
		var expectedNienKhoaNganhNamHocKyHoc = new NienKhoaNganhNamHocKyHoc(1, expectedNienKhoaNganh, expectedNamHocKyHoc);
		var expectedMonHoc = new MonHoc(1, "Mon Hoc A", 0.0, 0.0, 0.0, 0.0, 0.0);		
		var expectedResult = new NienKhoaNganhNamHocKyHocMonHoc(1, expectedNienKhoaNganhNamHocKyHoc, expectedMonHoc);
		
		var result = repository.findByNienKhoaNganhNamHocKyHocAndMonHocId(expectedNienKhoaNganhNamHocKyHoc.getId(), expectedMonHoc.getId());
		assertEquals(result.getId(), expectedResult.getId());
		assertEquals(result.getNienKhoaNganhNamHocKyHoc().getId(), expectedResult.getNienKhoaNganhNamHocKyHoc().getId());
		assertEquals(result.getMonHoc().getId(), expectedResult.getMonHoc().getId());
	}
	
	@Test
	public void findAllByNienKhoaNganhNamHocKyHocId() {
		var result = repository.findAllByNienKhoaNganhNamHocKyHocId(1);
		assertEquals(result.isEmpty(), false);
	}
}