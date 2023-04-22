package sqa.example.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import sqa.example.controller.ManagerStudentOfLHPController;
import sqa.example.model.NamHoc;
import sqa.example.repository.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ManagerStudentOfLHPControllerTest {

    @Mock
    private NganhRepository nganhRepository;

    @Mock
    private NamHocRepository namHocRepository;

    @Mock
    private NienKhoaNganhRepository nienKhoaNganhRepository;

    @Mock
    private NienKhoaService nienKhoaService;

    @Mock
    private NguoiDungRepository nguoiDungRepository;

    @Mock
    private NamHocService namHocService;

    @Mock
    private NienKhoaRepository nienKhoaRepository;

    @Mock
    private NganhService nganhService;

    @Mock
    private KyHocService kyHocService;

    @Mock
    private MonHocService monHocService;

    @Mock
    private SinhVienRepository sinhVienRepository;

    @Mock
    private NienKhoaNganhNamHocKyHocRepository nienKhoaNganhNamHocKyHocRepository;

    @Mock
    private NienKhoaNganhNamHocKyHocMonHocRepository nienKhoaNganhNamHocKyHocMonHocRepository;

    @Mock
    private NamHocKyHocRepository namHocKyHocRepository;

    @Mock
    private LopHocPhanRepository lopHocPhanRepository;

    @Mock
    private KetQuaRepository ketQuaRepository;

    @Mock
    private ThangDiemRepository thangDiemRepository;

    @InjectMocks
    private ManagerStudentOfLHPController managerStudentOfLHPController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(managerStudentOfLHPController).build();
    }
    @Test
    @Transactional(rollbackOn = {RuntimeException.class, SQLException.class})
    public void testGetAllNamhoc() throws Exception {
        setUp();

        List<NamHoc> namHocs = new ArrayList<>();
        namHocs.add(new NamHoc(1, "2021-2022"));
        namHocs.add(new NamHoc(2, "2022-2023"));

        when(namHocRepository.findAll()).thenReturn(namHocs);

        mockMvc.perform(get("/api/v1/admin/nam-hocs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].ten", is("2021-2022")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].ten", is("2022-2023")));
    }
}
