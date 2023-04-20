package sqa.example.ptit.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sqa.example.controller.FailureRateStatisticsController;
import sqa.example.controller.MonHocConfigurationController;
import sqa.example.model.GiaoVien;
import sqa.example.model.KetQua;
import sqa.example.model.KyHoc;
import sqa.example.model.LopHocPhan;
import sqa.example.model.MonHoc;
import sqa.example.model.NamHoc;
import sqa.example.model.NamHocKyHoc;
import sqa.example.model.Nganh;
import sqa.example.model.NguoiDung;
import sqa.example.model.NienKhoa;
import sqa.example.model.NienKhoaNganh;
import sqa.example.model.NienKhoaNganhNamHocKyHoc;
import sqa.example.model.NienKhoaNganhNamHocKyHocMonHoc;
import sqa.example.model.PhongHoc;
import sqa.example.model.SinhVien;
import sqa.example.model.ThangDiem;
import sqa.example.service.LopHocPhanService;
import sqa.example.service.NamHocKyHocService;
import sqa.example.service.NamHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocMonHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocService;
import sqa.example.service.NienKhoaNganhService;
import sqa.example.service.ThangDiemService;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "sqa")
public class FailureRateStatisticsControllerTest {
	/*
	private static  String ROOT_URI = "/api/v1/thong-ke-truot";

	@Mock private NamHocService namHocService;
	@Mock private NamHocKyHocService namHocKyHocService;
	@Mock private NienKhoaNganhService nienKhoaNganhService;
	@Mock private NienKhoaNganhNamHocKyHocService nienKhoaNganhNamHocKyHocService;
	@Mock private NienKhoaNganhNamHocKyHocMonHocService nienKhoaNganhNamHocKyHocMonHocService;
	@Mock private LopHocPhanService lopHocPhanService;
	@Mock private ThangDiemService thangDiemService;
	@InjectMocks private FailureRateStatisticsController failureRateStatisticsController;
	private MockMvc mvc;
	
	@BeforeEach
    public void setup() {
        Mockito.reset();
        mvc = standaloneSetup(failureRateStatisticsController).build();
    }
	
	@Test
	public void getAllNamHoc() throws Exception {
		when(namHocService.findAll()).thenReturn(dummyNamHocList);
		
		String uri = ROOT_URI + "/nam-hocs";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(dummyNamHocList.size())))
					 .andReturn();
	}
	
	@Test
	public void getAllKyHocOfNamHoc() throws Exception {
		when(namHocKyHocService.findAllByNamHocId(dummyNamHoc.getId())).thenReturn(dummyNamHocKyHocList);
		
		String uri = ROOT_URI + "/nam-hocs/{namHocId}/ky-hocs";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1)
							   .contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(dummyNamHocKyHocList.size())))
					 .andReturn();
	}
	
	@Test
	public void getAllNienKhoaOfNamHocKiHoc() throws Exception {
		when(namHocKyHocService.findByNamHocAndKyHocId(dummyNamHoc.getId(), dummyKyHoc.getId())).thenReturn(dummyNamHocKyHoc);
		when(nienKhoaNganhNamHocKyHocService.findAllByNamHocKyHocId(dummyNamHocKyHoc.getId())).thenReturn(dummyNienKhoaNganhNamHocKyHocList);
		
		String uri = ROOT_URI + "/nam-hocs/{namHocId}/ky-hocs/{kiHocId}/nien-khoas";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1, 1)
							   .contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(dummyNienKhoaNganhNamHocKyHocList.size())))
					 .andReturn();
	}
	
	@Test
	public void getAllNganhOfNienKhoaNamHocKiHoc() throws Exception {
		when(namHocKyHocService.findByNamHocAndKyHocId(dummyNamHoc.getId(), dummyKyHoc.getId())).thenReturn(dummyNamHocKyHoc);
		when(nienKhoaNganhNamHocKyHocService.findAllByNamHocKyHocId(dummyNamHocKyHoc.getId())).thenReturn(dummyNienKhoaNganhNamHocKyHocList);
		Set<Nganh> nganhSet = new HashSet<>();
		for (var E : dummyNienKhoaNganhNamHocKyHocList) nganhSet.add(E.getNienKhoaNganh().getNganh());
		
		String uri = ROOT_URI + "/nam-hocs/{namHocId}/ky-hocs/{kiHocId}/nien-khoas/{nienKhoaId}/nganhs";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1, 1, 1)
							   .contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(nganhSet.size())))
					 .andReturn();
	}
	
	@Test
	public void getMonHocOfNienKhoaNganhNamHocKiHoc() throws Exception {
		when(namHocKyHocService.findByNamHocAndKyHocId(dummyNamHoc.getId(), dummyKyHoc.getId())).thenReturn(dummyNamHocKyHoc);
		when(nienKhoaNganhService.findByNienKhoaAndNganhId(dummyNienKhoa.getId(), dummyNganh.getId())).thenReturn(dummyNienKhoaNganh);
		when(nienKhoaNganhNamHocKyHocService.findByNienKhoaNganhAndNamHocKyHocId(dummyNienKhoaNganh.getId(), dummyNamHocKyHoc.getId())).thenReturn(dummyNienKhoaNganhNamHocKyHoc);
		when(nienKhoaNganhNamHocKyHocMonHocService.findAllByNienKhoaNganhNamHocKyHocId(dummyNienKhoaNganhNamHocKyHoc.getId())).thenReturn(dummyNienKhoaNganhNamHocKyHocMonHocList);
		
		var monHocSet = new HashSet<MonHoc>();
		for (var nkmh : dummyNienKhoaNganhNamHocKyHocMonHocList) monHocSet.add(nkmh.getMonHoc());
		
		String uri = ROOT_URI + "/nam-hocs/{namHocId}/ky-hocs/{kyHocId}/nien-khoas/{nienKhoaId}/nganhs/{nganhId}/mon-hocs";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1, 1, 1, 1)
							   .contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(monHocSet.size())))
					 .andReturn();
	}
	
	@Test
	public void getLopHocPhanOfNienKhoaNganhNamHocKyHocMonHoc() throws Exception {
		when(namHocKyHocService.findByNamHocAndKyHocId(dummyNamHoc.getId(), dummyKyHoc.getId())).thenReturn(dummyNamHocKyHoc);
		when(nienKhoaNganhService.findByNienKhoaAndNganhId(dummyNienKhoa.getId(), dummyNganh.getId())).thenReturn(dummyNienKhoaNganh);
		when(nienKhoaNganhNamHocKyHocService.findByNienKhoaNganhAndNamHocKyHocId(dummyNienKhoaNganh.getId(), dummyNamHocKyHoc.getId())).thenReturn(dummyNienKhoaNganhNamHocKyHoc);
		when(nienKhoaNganhNamHocKyHocMonHocService.findByNienKhoaNganhNamHocKyHocAndMonHocId(dummyNienKhoaNganhNamHocKyHoc.getId(), dummyMonHoc.getId())).thenReturn(dummyNienKhoaNganhNamHocKyHocMonHoc);
		when(lopHocPhanService.findAllByNienKhoaNganhNamHocKyHocMonHocId(dummyNienKhoaNganhNamHocKyHocMonHoc.getId())).thenReturn(dummyLopHocPhanList);
		when(thangDiemService.findByName(dummyThangDiem.getDiemChu())).thenReturn(dummyThangDiem);

		String uri = ROOT_URI + "/nam-hocs/{namHocId}/ky-hocs/{kyHocId}/nien-khoas/{nienKhoaId}/nganhs/{nganhId}/mon-hocs/{monHocId}/lop-hoc-phans";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1, 1, 1, 1, 1)
							   .contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(dummyKetQuaList.size())))
					 .andReturn();
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////// DUMMY OBJECT /////////////////////////////////////////////////////////////////////////////////////////////
	private final NamHoc dummyNamHoc															= new NamHoc(1, "2019");
	private final KyHoc dummyKyHoc																= new KyHoc(1, "2019");
	private final NienKhoa dummyNienKhoa														= new NienKhoa(1, "2019");	
	private final Nganh dummyNganh																= new Nganh(1, "CNTT");		
	private final MonHoc dummyMonHoc															= new MonHoc(1, "Mon Hoc A", 0.0, 0.0, 0.0, 0.0, 0.0);		
	private final KetQua dummyKetQua															= new KetQua(1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "", null, null);
	private final ThangDiem dummyThangDiem														= new ThangDiem(1, 0.0, 3.9, "D", 1.0);
	private final NamHocKyHoc dummyNamHocKyHoc													= new NamHocKyHoc(1, dummyNamHoc, dummyKyHoc);
	private final NienKhoaNganh dummyNienKhoaNganh												= new NienKhoaNganh(1, dummyNienKhoa, dummyNganh);
	private final NienKhoaNganhNamHocKyHoc dummyNienKhoaNganhNamHocKyHoc						= new NienKhoaNganhNamHocKyHoc(1, dummyNienKhoaNganh, dummyNamHocKyHoc);
	private final NienKhoaNganhNamHocKyHocMonHoc dummyNienKhoaNganhNamHocKyHocMonHoc			= new NienKhoaNganhNamHocKyHocMonHoc(1, dummyNienKhoaNganhNamHocKyHoc, dummyMonHoc);
	
	private final PhongHoc dummyPhongHoc														= new PhongHoc(1, "101A");
	private final NguoiDung dummyNguoiDungGV													= new NguoiDung(1, "", "", "", "", "", null);
	private final NguoiDung dummyNguoiDungSV													= new NguoiDung(1, "", "", "", "", "", null);
	private final GiaoVien dummyGiaoVien														= new GiaoVien(1, dummyNguoiDungGV, "");
	
	private final List<KetQua> dummyKetQuaList													= Arrays.asList(dummyKetQua);
	private final LopHocPhan dummyLopHocPhan													= new LopHocPhan(1, new Date(), new Date(), dummyNienKhoaNganhNamHocKyHocMonHoc, dummyPhongHoc, dummyKetQuaList, dummyGiaoVien);
	
	private final SinhVien dummySinhVien														= new SinhVien(1, dummyNguoiDungSV, "", dummyNienKhoaNganh);
	
	private final List<NamHoc> dummyNamHocList													= Arrays.asList(dummyNamHoc);
	private final List<NamHocKyHoc> dummyNamHocKyHocList										= Arrays.asList(dummyNamHocKyHoc);
	private final List<NienKhoaNganh> dummyNienKhoaNganhList									= Arrays.asList(dummyNienKhoaNganh);
	private final List<NienKhoaNganhNamHocKyHoc> dummyNienKhoaNganhNamHocKyHocList				= Arrays.asList(dummyNienKhoaNganhNamHocKyHoc);
	private final List<NienKhoaNganhNamHocKyHocMonHoc> dummyNienKhoaNganhNamHocKyHocMonHocList	= Arrays.asList(dummyNienKhoaNganhNamHocKyHocMonHoc);
	private final List<LopHocPhan> dummyLopHocPhanList											= Arrays.asList(dummyLopHocPhan);
}
