package sqa.example.ptit.controller;


import java.util.HashSet;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
import sqa.example.model.MonHoc;
import sqa.example.model.Nganh;
import sqa.example.ptit.Dummy;
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
	private static final String ROOT_URI = "/api/v1/thong-ke-truot";

	@Mock private NamHocService namHocService;
	@Mock private NamHocKyHocService namHocKyHocService;
	@Mock private NienKhoaNganhService nienKhoaNganhService;
	@Mock private NienKhoaNganhNamHocKyHocService nienKhoaNganhNamHocKyHocService;
	@Mock private NienKhoaNganhNamHocKyHocMonHocService nienKhoaNganhNamHocKyHocMonHocService;
	@Mock private LopHocPhanService lopHocPhanService;
	@Mock private ThangDiemService thangDiemService;
	@InjectMocks private FailureRateStatisticsController failureRateStatisticsController;

	private MockMvc mvc;
	private final Dummy dummy = new Dummy();
	
	@BeforeEach
    public void setup() {
        Mockito.reset();
        mvc = standaloneSetup(failureRateStatisticsController).build();
    }
	
	@Test
	public void getAllNamHoc() throws Exception {
		when(namHocService.findAll()).thenReturn(dummy.namHocList);
		
		String uri = ROOT_URI + "/nam-hocs";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(dummy.namHocList.size())))
					 .andReturn();
	}
	
	@Test
	public void getAllKyHocOfNamHoc() throws Exception {
		when(namHocKyHocService.findAllByNamHocId(dummy.namHoc.getId())).thenReturn(dummy.namHocKyHocList);
		
		String uri = ROOT_URI + "/nam-hocs/{namHocId}/ky-hocs";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1)
							   .contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(dummy.namHocKyHocList.size())))
					 .andReturn();
	}
	
	@Test
	public void getAllNienKhoaOfNamHocKiHoc() throws Exception {
		when(namHocKyHocService.findByNamHocAndKyHocId(dummy.namHoc.getId(), dummy.kyHoc.getId())).thenReturn(dummy.namHocKyHoc);
		when(nienKhoaNganhNamHocKyHocService.findAllByNamHocKyHocId(dummy.namHocKyHoc.getId())).thenReturn(dummy.nienKhoaNganhNamHocKyHocList);
		
		String uri = ROOT_URI + "/nam-hocs/{namHocId}/ky-hocs/{kiHocId}/nien-khoas";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1, 1)
							   .contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(dummy.nienKhoaNganhNamHocKyHocList.size())))
					 .andReturn();
	}
	
	@Test
	public void getAllNganhOfNienKhoaNamHocKiHoc() throws Exception {
		when(namHocKyHocService.findByNamHocAndKyHocId(dummy.namHoc.getId(), dummy.kyHoc.getId())).thenReturn(dummy.namHocKyHoc);
		when(nienKhoaNganhNamHocKyHocService.findAllByNamHocKyHocId(dummy.namHocKyHoc.getId())).thenReturn(dummy.nienKhoaNganhNamHocKyHocList);
		HashSet<Nganh> nganhSet = new HashSet<>();
		for (var E : dummy.nienKhoaNganhNamHocKyHocList) nganhSet.add(E.getNienKhoaNganh().getNganh());
		
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
		when(namHocKyHocService.findByNamHocAndKyHocId(dummy.namHoc.getId(), dummy.kyHoc.getId())).thenReturn(dummy.namHocKyHoc);
		when(nienKhoaNganhService.findByNienKhoaAndNganhId(dummy.nienKhoa.getId(), dummy.nganh.getId())).thenReturn(dummy.nienKhoaNganh);
		when(nienKhoaNganhNamHocKyHocService.findByNienKhoaNganhAndNamHocKyHocId(dummy.nienKhoaNganh.getId(), dummy.namHocKyHoc.getId())).thenReturn(dummy.nienKhoaNganhNamHocKyHoc);
		when(nienKhoaNganhNamHocKyHocMonHocService.findAllByNienKhoaNganhNamHocKyHocId(dummy.nienKhoaNganhNamHocKyHoc.getId())).thenReturn(dummy.nienKhoaNganhNamHocKyHocMonHocList);
		
		var monHocSet = new HashSet<MonHoc>();
		for (var nkmh : dummy.nienKhoaNganhNamHocKyHocMonHocList) monHocSet.add(nkmh.getMonHoc());
		
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
		when(namHocKyHocService.findByNamHocAndKyHocId(dummy.namHoc.getId(), dummy.kyHoc.getId())).thenReturn(dummy.namHocKyHoc);
		when(nienKhoaNganhService.findByNienKhoaAndNganhId(dummy.nienKhoa.getId(), dummy.nganh.getId())).thenReturn(dummy.nienKhoaNganh);
		when(nienKhoaNganhNamHocKyHocService.findByNienKhoaNganhAndNamHocKyHocId(dummy.nienKhoaNganh.getId(), dummy.namHocKyHoc.getId())).thenReturn(dummy.nienKhoaNganhNamHocKyHoc);
		when(nienKhoaNganhNamHocKyHocMonHocService.findByNienKhoaNganhNamHocKyHocAndMonHocId(dummy.nienKhoaNganhNamHocKyHoc.getId(), dummy.monHoc.getId())).thenReturn(dummy.nienKhoaNganhNamHocKyHocMonHoc);
		when(lopHocPhanService.findAllByNienKhoaNganhNamHocKyHocMonHocId(dummy.nienKhoaNganhNamHocKyHocMonHoc.getId())).thenReturn(dummy.lopHocPhanList);
		when(thangDiemService.findByName(dummy.thangDiem.getDiemChu())).thenReturn(dummy.thangDiem);

		String uri = ROOT_URI + "/nam-hocs/{namHocId}/ky-hocs/{kyHocId}/nien-khoas/{nienKhoaId}/nganhs/{nganhId}/mon-hocs/{monHocId}/lop-hoc-phans";
		var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1, 1, 1, 1, 1)
							   .contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andDo(print());
		resultActions.andExpect(status().isOk())
					 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
					 .andExpect(jsonPath("$", hasSize(dummy.lopHocPhanList.size())))
					 .andReturn();
	}
}
