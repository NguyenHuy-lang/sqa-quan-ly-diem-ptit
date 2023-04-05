package sqa.example.ptit.repositoryTests;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import sqa.example.model.Nganh;
import sqa.example.model.NguoiDung;
import sqa.example.model.NienKhoaNganh;
import sqa.example.model.SinhVien;
import sqa.example.repository.SinhVienRepository;
import static org.assertj.core.api.Assertions.*;

import java.util.Date;

//@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SinhVienRepositoryTests {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private SinhVienRepository sinhVienRepository;
    @Test
    @Order(1)
    public void saveSinhVienTest() {
        SinhVien sinhVien = SinhVien.builder().maSinhVien("B19DCCN900s")
                .nienKhoaNganh(
                        NienKhoaNganh.builder().nganh(
                                Nganh.builder().ten("cong nghe thong tinss").build()
                        ).build()
                ).nguoiDung(NguoiDung.builder().email("test2@gmail.com")
                        .name("tester")
                        .ngaySinh(new Date())
                        .soDienThoai("032789468910112")
                        .gioiTinh("nam")
                        .build()).build();
        sinhVienRepository.save(sinhVien);
        System.out.println(sinhVien.getId());
        Assertions.assertTrue(sinhVien.getId() != null);
    }

}
