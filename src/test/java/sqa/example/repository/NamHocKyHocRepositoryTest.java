package sqa.example.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.example.model.KyHoc;
import sqa.example.model.NamHoc;
import sqa.example.model.NamHocKyHoc;
import sqa.example.model.Nganh;
import sqa.example.model.NienKhoa;
import sqa.example.model.NienKhoaNganh;
import sqa.example.model.NienKhoaNganhNamHocKyHoc;
import sqa.example.repository.NamHocKyHocRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NamHocKyHocRepositoryTest {
    @Autowired private NamHocKyHocRepository repository;

    @Test
    public void findByNamHocAndKyHocId() {
        var expectedNamHoc = new NamHoc(1, "d19");
        var expectedKyHoc = new KyHoc(1, "ki 1");
        var expectedResult = new NamHocKyHoc(1, expectedNamHoc, expectedKyHoc);

        var result = repository.getNamHocKyHoc(expectedNamHoc.getId(), expectedKyHoc.getId());
        assertEquals(result.getId(),				expectedResult.getId());
        assertEquals(result.getNamHoc().getId(),	expectedResult.getNamHoc().getId());
        assertEquals(result.getKyHoc().getId(),		expectedResult.getKyHoc().getId());
    }

    @Test
    public void findAllByNamHocKyHocId() {
        var result = repository.getNamHocKyHoc(1);
        assertEquals(result.isEmpty(), false);
    }
}