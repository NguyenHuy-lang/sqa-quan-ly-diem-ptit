package sqa.example.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.example.model.Nganh;
import sqa.example.model.NienKhoa;
import sqa.example.model.NienKhoaNganh;
import sqa.example.repository.NienKhoaNganhRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NienKhoaNganhRepositoryTest {
    @Autowired private NienKhoaNganhRepository repository;

    @Test
    public void findByNienKhoaAndNganhId() {
        var expectedNienKhoa = new NienKhoa(1, "d19");
        var expectedNganh = new Nganh(1, "cong nghe thong tin");
        var expectedResult = new NienKhoaNganh(1, expectedNganh, expectedNienKhoa);

        var result = repository.getNienKhoaNganhByNienKhoaAndNganh(expectedNienKhoa.getId(), expectedNganh.getId());
        assertEquals(result.getId(),					expectedResult.getId());
        assertEquals(result.getNienKhoa().getId(),		expectedResult.getNienKhoa().getId());
        assertEquals(result.getNienKhoa().getTen(),		expectedResult.getNienKhoa().getTen());
        assertEquals(result.getNganh().getId(),			expectedResult.getNganh().getId());
        assertEquals(result.getNganh().getTen(),		expectedResult.getNganh().getTen());
    }

    @Test
    public void findAllByNganhId() {
        var result = repository.findAllByNganhId(1);
        assertEquals(result.isEmpty(), false);
    }
}