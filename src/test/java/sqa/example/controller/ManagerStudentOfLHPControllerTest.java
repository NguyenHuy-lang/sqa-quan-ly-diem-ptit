package sqa.example.controller;

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
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sqa.example.service.*;

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
    @Transactional(rollbackOn = {RuntimeException.class, SQLException.class})
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
    @Transactional(rollbackOn = {RuntimeException.class, SQLException.class})
    public void getAllNganh() throws Exception{
        setUp();

        Integer ky_hoc_id = 1;
        Integer nam_hoc_id = 1;
        Integer nien_khoa_id = 1;
        NamHocKyHoc namHocKyHoc = new NamHocKyHoc(1,
                new NamHoc(1, "2017-2018"),
                new KyHoc(1, "hoc ky 1"));

        List<NienKhoaNganhNamHocKyHoc> nienKhoaNganhNamHocKyhocList
                = Arrays.asList(
                        new NienKhoaNganhNamHocKyHoc(1,
                                new NamHocKyHoc(
                                        1,
                                        new NamHoc(1, "2017-2018"),
                                        new KyHoc(1, "hoc ky 1")
                                ),
                                new NienKhoaNganh(1,
                                        new Nganh(1, "cong nghe thong tin"),
                                        new NienKhoa(1, "d19")
                                )
                        )
                );
        when(kyHocService.getIdKyHocByNameKyHoc(anyString())).thenReturn(ky_hoc_id);
        when(namHocService.getIdNamHocByName(anyString())).thenReturn(nam_hoc_id);
        when(nienKhoaService.getIdNienKhoaByName(anyString())).thenReturn(nien_khoa_id);
        when(namHocKyHocRepository.getNamHocKyHoc(anyInt(), anyInt())).thenReturn(namHocKyHoc);
        when(nienKhoaNganhNamHocKyHocRepository.getListNienKhoaNganhNamHocKyHoc(anyInt())).thenReturn(nienKhoaNganhNamHocKyhocList);

        mockMvc.perform(get("/api/v1/admin/nam-hocs/2017-2018/ky-hocs/hoc-ky-1/nien-khoas/d19/nganh"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].ten", is("cong nghe thong tin")));
    }

    @Test
    void testGetListMonHoc() throws Exception{
        setUp();
        Integer ky_hoc_id = 1;
        Integer nam_hoc_id = 1;
        Integer nien_khoa_id = 1;
        Integer nganh_id = 1;

        NienKhoaNganh nienKhoaNganh = new NienKhoaNganh(
                1,
                new Nganh(1, "cong nghe thong tin"),
                new NienKhoa(1, "d19")
        );

        NamHocKyHoc namHocKyHoc = new NamHocKyHoc(1,
                new NamHoc(1, "2017-2018"),
                new KyHoc(1, "hoc ky 1"));

        NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc =
                new NienKhoaNganhNamHocKyHoc(1, namHocKyHoc, nienKhoaNganh);

        List<NienKhoaNganhNamHocKyHocMonHoc> nienKhoaNganhNamHocKyHocMonHocList
                = Arrays.asList(new NienKhoaNganhNamHocKyHocMonHoc(1,
                        new MonHoc(1,
                                "nhap mon cong nghe phan mem 2",
                                0.1,
                                0.0,
                                0.1,
                                0.3,
                                0.5
                        ),
                        new NienKhoaNganhNamHocKyHoc(1,
                                new NamHocKyHoc(
                                        1,
                                        new NamHoc(1, "2017-2018"),
                                        new KyHoc(1, "hoc ky 1")
                                ),
                                new NienKhoaNganh(1,
                                        new Nganh(1, "cong nghe thong tin"),
                                        new NienKhoa(1, "d19")
                                )
                        )
                )
        );
        when(kyHocService.getIdKyHocByNameKyHoc(anyString())).thenReturn(ky_hoc_id);
        when(namHocService.getIdNamHocByName(anyString())).thenReturn(nam_hoc_id);
        when(nienKhoaService.getIdNienKhoaByName(anyString())).thenReturn(nien_khoa_id);
        when(nganhService.getIdOfNganhByName(anyString())).thenReturn(nganh_id);
        when(nienKhoaNganhRepository.getNienKhoaNganhByNienKhoaAndNganh(anyInt(), anyInt())).thenReturn(nienKhoaNganh);
        when(namHocKyHocRepository.getNamHocKyHoc(anyInt(), anyInt())).thenReturn(namHocKyHoc);
        when(nienKhoaNganhNamHocKyHocRepository.getNienKhoaNganhNamHocKyHoc(anyInt(), anyInt())).thenReturn(nienKhoaNganhNamHocKyHoc);
        when(nienKhoaNganhNamHocKyHocMonHocRepository.getNienKhoaNganhNamHocKyHocMonHoc(anyInt())).thenReturn(nienKhoaNganhNamHocKyHocMonHocList);

        mockMvc.perform(get("/api/v1/admin/nam-hocs/2017-2018/ky-hocs/hoc-ky-1/nien-khoas/d19/nganh/cong-nghe-thong-tin/mon-hocs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].ten", is("nhap mon cong nghe phan mem 2")))
                .andExpect(jsonPath("$[0].tyLeDiemCC", is(0.1)))
                .andExpect(jsonPath("$[0].tyLeDiemTH", is(0.0)))
                .andExpect(jsonPath("$[0].tyLeDiemKT", is(0.1)))
                .andExpect(jsonPath("$[0].tyLeDiemBT", is(0.3)))
                .andExpect(jsonPath("$[0].tyLeDiemCuoiKy", is(0.5)));
    }

    @Test
    void addSinhVienIntoLhp() throws Exception{

    }
    @Test
    public void testGetListLHP () throws Exception{
        setUp();
        Integer ky_hoc_id = 1;
        Integer nam_hoc_id = 1;
        Integer nien_khoa_id = 1;
        Integer nganh_id = 1;
        Integer mon_hoc_id = 1;
        NienKhoaNganh nienKhoaNganh = new NienKhoaNganh(
                1,
                new Nganh(1, "cong nghe thong tin"),
                new NienKhoa(1, "d19")
        );
        NamHocKyHoc namHocKyHoc = new NamHocKyHoc(1,
                new NamHoc(1, "2017-2018"),
                new KyHoc(1, "hoc ky 1"));

        NienKhoaNganhNamHocKyHoc nienKhoaNganhNamHocKyHoc =
                new NienKhoaNganhNamHocKyHoc(1, namHocKyHoc, nienKhoaNganh);
        NienKhoaNganhNamHocKyHocMonHoc nienKhoaNganhNamHocKyHocMonHoc = new NienKhoaNganhNamHocKyHocMonHoc(1,
                new MonHoc(1,
                        "nhap mon cong nghe phan mem 2",
                        0.1,
                        0.0,
                        0.1,
                        0.3,
                        0.5
                ),
                new NienKhoaNganhNamHocKyHoc(1,
                        new NamHocKyHoc(
                                1,
                                new NamHoc(1, "2017-2018"),
                                new KyHoc(1, "hoc ky 1")
                        ),
                        new NienKhoaNganh(1,
                                new Nganh(1, "cong nghe thong tin"),
                                new NienKhoa(1, "d19")
                        )
                )
        );

        List<LopHocPhan> lopHocPhanList = Arrays.asList(

                new LopHocPhan(1,
                        null,null,
                        nienKhoaNganhNamHocKyHocMonHoc,
                        new PhongHoc(1, "101-a2"),
                        null,
                        new GiaoVien(1, "gvbcvt313",null)
                )
        );

        when(lopHocPhanRepository.getLopHocPhan(anyInt())).thenReturn(lopHocPhanList);
        when(kyHocService.getIdKyHocByNameKyHoc(anyString())).thenReturn(ky_hoc_id);
        when(namHocService.getIdNamHocByName(anyString())).thenReturn(nam_hoc_id);
        when(nienKhoaService.getIdNienKhoaByName(anyString())).thenReturn(nien_khoa_id);
        when(nganhService.getIdOfNganhByName(anyString())).thenReturn(nganh_id);
        when(monHocService.getIdMonHocByName(anyString())).thenReturn(mon_hoc_id);
        when(nienKhoaNganhRepository.getNienKhoaNganhByNienKhoaAndNganh(anyInt(), anyInt())).thenReturn(nienKhoaNganh);
        when(nienKhoaNganhNamHocKyHocRepository.
                getNienKhoaNganhNamHocKyHoc(anyInt(), anyInt())).
                thenReturn(nienKhoaNganhNamHocKyHoc);
        when(nienKhoaNganhNamHocKyHocMonHocRepository.getNienKhoaNganhNamHocKyHocMonHoc(anyInt(), anyInt())).thenReturn(nienKhoaNganhNamHocKyHocMonHoc);
        when(namHocKyHocRepository.getNamHocKyHoc(anyInt(), anyInt())).thenReturn(namHocKyHoc);
        when(lopHocPhanRepository.getLopHocPhan(anyInt())).thenReturn(lopHocPhanList);
        mockMvc.perform(get("/api/v1/admin/nam-hocs/2017-2018/ky-hocs/hoc-ky-1/nien-khoas/d19/nganh/cong-nghe-thong-tin/mon-hocs/nhap-mon-cong-nghe-phan-mem-2/lhps"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nienKhoaNganhNamHocKyHocMonHoc.monHoc.ten", is("nhap mon cong nghe phan mem 2")))
                .andExpect(jsonPath("$[0].giaoVien.maGiaoVien", is("gvbcvt313")));

    }


    @Test
    void testGetAllSinhVienOfLhp() throws Exception{
        setUp();
        LopHocPhan lopHocPhan = new LopHocPhan(
                1,
                null, null,
                new NienKhoaNganhNamHocKyHocMonHoc(1,
                        new MonHoc(1,
                                "nhap mon cong nghe phan mem 2",
                                0.1,
                                0.0,
                                0.1,
                                0.3,
                                0.5
                        ),
                        new NienKhoaNganhNamHocKyHoc(1,
                                new NamHocKyHoc(
                                        1,
                                        new NamHoc(1, "2017-2018"),
                                        new KyHoc(1, "hoc ky 1")
                                ),
                                new NienKhoaNganh(1,
                                        new Nganh(1, "cong nghe thong tin"),
                                        new NienKhoa(1, "d19")
                                )
                        )
                ),
                new PhongHoc(1, "101-a2"),
                List.of(
                        new KetQua(
                                1,
                                9.0,
                                9.0,
                                9.0,
                                9.0,
                                9.0,
                                null, null, null,
                                new SinhVien(
                                        1,
                                        new NguoiDung(
                                                1,
                                                "nguyen quang huy",
                                                "nam",
                                                "nam dinh",
                                                "huynq.b19dccn313@stu.ptit.edu.vn",
                                                "0353358048",
                                                null
                                        ),
                                        "b19dccn313",
                                        null
                                ),
                                null,
                                null
                        )
                ),
                new GiaoVien(1,
                        "gvbcvt313",
                        null
                )
        );

        when(lopHocPhanRepository.getById(anyInt())).thenReturn(lopHocPhan);
        mockMvc.perform(get("/api/v1/admin/nam-hocs/2017-2018/ky-hocs/hoc-ky-1/nien-khoas/d19/nganh/cong-nghe-thong-tin/mon-hocs/nhap-mon-cong-nghe-phan-mem/lhps/1/sinh-viens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nguoiDung.name", is("nguyen quang huy")))
                .andExpect(jsonPath("$[0].nguoiDung.gioiTinh", is("nam")))
                .andExpect(jsonPath("$[0].nguoiDung.diaChi", is("nam dinh")))
                .andExpect(jsonPath("$[0].nguoiDung.email", is("huynq.b19dccn313@stu.ptit.edu.vn")))
                .andExpect(jsonPath("$[0].maSinhVien", is("b19dccn313")))


        ;

    }
    @Test
    void testGetListKetQuaSinhVienOfLhp() throws Exception{
        setUp();
        LopHocPhan lopHocPhan = new LopHocPhan(
                1,
                null, null,
                new NienKhoaNganhNamHocKyHocMonHoc(1,
                        new MonHoc(1,
                                "nhap mon cong nghe phan mem 2",
                                0.1,
                                0.0,
                                0.1,
                                0.3,
                                0.5
                        ),
                        new NienKhoaNganhNamHocKyHoc(1,
                                new NamHocKyHoc(
                                        1,
                                        new NamHoc(1, "2017-2018"),
                                        new KyHoc(1, "hoc ky 1")
                                ),
                                new NienKhoaNganh(1,
                                        new Nganh(1, "cong nghe thong tin"),
                                        new NienKhoa(1, "d19")
                                )
                        )
                ),
                new PhongHoc(1, "101-a2"),
                List.of(
                        new KetQua(
                                1,
                                9.0,
                                9.0,
                                9.0,
                                9.0,
                                9.0,
                                null, null, null,
                                new SinhVien(
                                        1,
                                        new NguoiDung(
                                                1,
                                                "nguyen quang huy",
                                                "nam",
                                                "nam dinh",
                                                "huynq.b19dccn313@stu.ptit.edu.vn",
                                                "0353358048",
                                                null
                                        ),
                                        "b19dccn313",
                                        null
                                ),
                                null,
                                null
                        )
                ),
                new GiaoVien(1,
                        "gvbcvt313",
                        null
                )
        );

        when(lopHocPhanRepository.getById(anyInt())).thenReturn(lopHocPhan);
        when(lopHocPhanRepository.getById(anyInt())).thenReturn(lopHocPhan);
        mockMvc.perform(get("/api/v1/admin/nam-hocs/2017-2018/" +
                        "ky-hocs/hoc-ky-1/nien-khoas/d19/" +
                        "nganh/cong-nghe-thong-tin/mon-hocs/" +
                        "nhap-mon-cong-nghe-phan-mem/lhps/1/ket-quas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].diemCC", is(9.0)))
                .andExpect(jsonPath("$[0].diemKT", is(9.0)))
                .andExpect(jsonPath("$[0].diemBT", is(9.0)))
                .andExpect(jsonPath("$[0].diemCuoiKy", is(9.0)))
                .andExpect(jsonPath("$[0].nameSinhVien", is("nguyen quang huy")))
                .andExpect(jsonPath("$[0].maSinhVien", is("b19dccn313")));

    }
    void testAddSinhVienIntoLhp() throws Exception {
        setUp();
        LopHocPhan lopHocPhan = new LopHocPhan(
                1,
                null, null,
                new NienKhoaNganhNamHocKyHocMonHoc(1,
                        new MonHoc(1,
                                "nhap mon cong nghe phan mem 2",
                                0.1,
                                0.0,
                                0.1,
                                0.3,
                                0.5
                        ),
                        new NienKhoaNganhNamHocKyHoc(1,
                                new NamHocKyHoc(
                                        1,
                                        new NamHoc(1, "2017-2018"),
                                        new KyHoc(1, "hoc ky 1")
                                ),
                                new NienKhoaNganh(1,
                                        new Nganh(1, "cong nghe thong tin"),
                                        new NienKhoa(1, "d19")
                                )
                        )
                ),
                new PhongHoc(1, "101-a2"),
                List.of(
                        new KetQua(
                                1,
                                9.0,
                                9.0,
                                9.0,
                                9.0,
                                9.0,
                                null, null, null,
                                new SinhVien(
                                        1,
                                        new NguoiDung(
                                                1,
                                                "nguyen quang huy",
                                                "nam",
                                                "nam dinh",
                                                "huynq.b19dccn313@stu.ptit.edu.vn",
                                                "0353358048",
                                                null
                                        ),
                                        "b19dccn313",
                                        null
                                ),
                                null,
                                null
                        )
                ),
                new GiaoVien(1,
                        "gvbcvt313",
                        null
                )
        );


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
