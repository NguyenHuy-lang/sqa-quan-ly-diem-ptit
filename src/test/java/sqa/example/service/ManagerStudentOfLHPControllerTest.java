package sqa.example.service;

import com.google.protobuf.Any;
import org.hibernate.type.AnyType;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import sqa.example.controller.ManagerStudentOfLHPController;
import sqa.example.model.*;
import sqa.example.repository.*;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
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

    @Mock
    HttpSession session;
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

    @Test
    @Transactional(rollbackOn = {RuntimeException.class, SQLException.class})
    public void getAllKyHocOfNamHoc() throws Exception {
        setUp();
        List<KyHoc> kyHocList = Arrays.asList(
                new KyHoc(1, "hoc ky 1"),
                new KyHoc(2, "hoc ky 2"),
                new KyHoc(3, "hoc ky 3"),
                new KyHoc(4, "hoc ky he"),
                new KyHoc(5, "hoc ky dong")
                );
        NamHoc namHoc = new NamHoc(1, "2017-2018");
        List<NamHocKyHoc> hocKyHocList = Arrays.asList(
                new NamHocKyHoc(1, namHoc, kyHocList.get(0)),
                new NamHocKyHoc(2, namHoc, kyHocList.get(1)),
                new NamHocKyHoc(3, namHoc, kyHocList.get(2)),
                new NamHocKyHoc(4, namHoc, kyHocList.get(3)),
                new NamHocKyHoc(5, namHoc, kyHocList.get(4))
                );
        when(namHocKyHocRepository.
                getNamHocKyHoc(any())).thenReturn(hocKyHocList);
        mockMvc.perform(get("/api/v1/admin/nam-hocs/2017-2018/ky-hocs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))

                .andExpect(jsonPath("$[0].kyHoc.id", is(1)))
                .andExpect(jsonPath("$[0].kyHoc.ten", is("hoc ky 1")))
                .andExpect(jsonPath("$[0].namHoc.id", is(1)))
                .andExpect(jsonPath("$[0].namHoc.ten", is("2017-2018")))

                .andExpect(jsonPath("$[1].kyHoc.id", is(2)))
                .andExpect(jsonPath("$[1].kyHoc.ten", is("hoc ky 2")))
                .andExpect(jsonPath("$[1].namHoc.id", is(1)))
                .andExpect(jsonPath("$[1].namHoc.ten", is("2017-2018")))

                .andExpect(jsonPath("$[2].kyHoc.id", is(3)))
                .andExpect(jsonPath("$[2].kyHoc.ten", is("hoc ky 3")))
                .andExpect(jsonPath("$[2].namHoc.id", is(1)))
                .andExpect(jsonPath("$[2].namHoc.ten", is("2017-2018")))

                .andExpect(jsonPath("$[3].kyHoc.id", is(4)))
                .andExpect(jsonPath("$[3].kyHoc.ten", is("hoc ky he")))
                .andExpect(jsonPath("$[3].namHoc.id", is(1)))
                .andExpect(jsonPath("$[3].namHoc.ten", is("2017-2018")))

                .andExpect(jsonPath("$[4].kyHoc.id", is(5)))
                .andExpect(jsonPath("$[4].kyHoc.ten", is("hoc ky dong")))
                .andExpect(jsonPath("$[4].namHoc.id", is(1)))
                .andExpect(jsonPath("$[4].namHoc.ten", is("2017-2018")))
        ;
    }

    @Test
    public void testGetAllNienKhoa() throws Exception {
        setUp();
        Integer ky_hoc_id = 1;
        Integer nam_hoc_id = 1;
        Integer nien_khoa_id = 1;
        NamHocKyHoc namHocKyHoc = new NamHocKyHoc(
                1,
                new NamHoc(1, "2017-2018"),
                new KyHoc(1, "hoc ky 1"));
        Integer nam_hoc_ky_hoc_id = 1;

        List<NienKhoaNganhNamHocKyHoc> nienKhoaNganhNamHocKyHocList
                = Arrays.asList(
                        new NienKhoaNganhNamHocKyHoc(1, namHocKyHoc,
                                new NienKhoaNganh(1,
                                        new Nganh(1, "cong nghe thong tin"),
                                        new NienKhoa(1, "d19")))
        );
        NienKhoaNganh nienKhoaNganh = new NienKhoaNganh(1,
                new Nganh(1, "cong nghe thong tin"),
                new NienKhoa(1, "d19"));
        NienKhoa nienKhoa = new NienKhoa(1, "d19");

        when(kyHocService.getIdKyHocByNameKyHoc(anyString())).thenReturn(ky_hoc_id);
        when(namHocService.getIdNamHocByName(anyString())).thenReturn(nam_hoc_id);
        when(namHocKyHocRepository.getNamHocKyHoc(anyInt(), anyInt())).thenReturn(namHocKyHoc);
        when(nienKhoaNganhRepository.getById(anyInt())).thenReturn(nienKhoaNganh);
        when(nienKhoaNganhNamHocKyHocRepository.getListNienKhoaNganhNamHocKyHoc(anyInt())).
                thenReturn(nienKhoaNganhNamHocKyHocList);
        when(nienKhoaRepository.getById(anyInt())).thenReturn(nienKhoa);

        mockMvc.perform(get("/api/v1/admin/nam-hocs/2017-2018/ky-hocs/hoc-ky-1/nien-khoas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].ten", is("d19")));
    }



    @Test
    public void testStandardized() {
        ManagerStudentOfLHPController example = new ManagerStudentOfLHPController();

        // First test case
        String input1 = "2023-04-22";
        String expectedOutput1 = "2023 04 22";
        String actualOutput1 = example.standardized(input1);
        assertEquals(expectedOutput1, actualOutput1);

        // Second test case
        String input2 = "2022-12-31";
        String expectedOutput2 = "2022 12 31";
        String actualOutput2 = example.standardized(input2);
        assertEquals(expectedOutput2, actualOutput2);
    }

}
