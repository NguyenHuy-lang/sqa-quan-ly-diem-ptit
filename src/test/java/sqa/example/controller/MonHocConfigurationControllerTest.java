package sqa.example.controller;

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
import sqa.example.controller.MonHocConfigurationController;
import sqa.example.model.KyHoc;
import sqa.example.model.MonHoc;
import sqa.example.model.NamHoc;
import sqa.example.model.NamHocKyHoc;
import sqa.example.model.Nganh;
import sqa.example.model.NienKhoa;
import sqa.example.model.NienKhoaNganh;
import sqa.example.model.NienKhoaNganhNamHocKyHoc;
import sqa.example.model.NienKhoaNganhNamHocKyHocMonHoc;
import sqa.example.service.MonHocService;
import sqa.example.service.NganhService;
import sqa.example.service.NienKhoaNganhNamHocKyHocMonHocService;
import sqa.example.service.NienKhoaNganhNamHocKyHocService;
import sqa.example.service.NienKhoaNganhService;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "sqa")
public class MonHocConfigurationControllerTest {
    private static final String ROOT_URI = "/api/v1/cau-hinh-mon-hoc";

    @Mock private NganhService nganhService;
    @Mock private NienKhoaNganhService nienKhoaNganhService;
    @Mock private NienKhoaNganhNamHocKyHocService nienKhoaNganhNamHocKyHocService;
    @Mock private NienKhoaNganhNamHocKyHocMonHocService nienKhoaNganhNamHocKyHocMonHocService;
    @Mock private MonHocService monHocService;

    @InjectMocks
    private MonHocConfigurationController monHocConfigurationController;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        Mockito.reset();
        mvc = standaloneSetup(monHocConfigurationController).build();
    }

    @Test
    public void getAllNganh() throws Exception {
        List<Nganh> dummies = Arrays.asList(
                new Nganh(1, "Nganh A"),
                new Nganh(2, "Nganh B"),
                new Nganh(3, "Nganh C")
        );
        when(nganhService.findAll()).thenReturn(dummies);

        String uri = ROOT_URI + "/nganhs";
        var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(dummies.size())))
                .andReturn();
    }

    @Test
    public void getAllMonHocByNganh() throws Exception {
        var dummyNganh = new Nganh(1, "Nganh A");
        var dummyNienKhoa = new NienKhoa(1, "2019");
        var dummyNamHoc = new NamHoc(1, "2019");
        var dummyKyHoc = new KyHoc(1, "Hoc Ki 1");
        var dummyMonHcc = new MonHoc(1, "Mon Hoc A", 0.2, 0.2, 0.2, 0.2, 0.2);
        var dummyNienKhoaNganh = new NienKhoaNganh(1, dummyNganh, dummyNienKhoa);
        var dummyNamHocKyHoc = new NamHocKyHoc(1, dummyNamHoc, dummyKyHoc);
        var dummyNienKhoaNganhNamHocKyHoc = new NienKhoaNganhNamHocKyHoc(1, dummyNamHocKyHoc, dummyNienKhoaNganh);
        var dummyNienKhoaNganhNamHocKyHocMonHoc = new NienKhoaNganhNamHocKyHocMonHoc(1, dummyMonHcc, dummyNienKhoaNganhNamHocKyHoc);

        when(nienKhoaNganhService.findAllByNganhId(dummyNganh.getId())).thenReturn(Arrays.asList(dummyNienKhoaNganh));
        when(nienKhoaNganhNamHocKyHocService.findAllByNienKhoaNganhId(dummyNienKhoaNganh.getId())).thenReturn(Arrays.asList(dummyNienKhoaNganhNamHocKyHoc));
        when(nienKhoaNganhNamHocKyHocMonHocService.getNienKhoaNganhNamHocKyHocMonHoc(dummyNienKhoaNganhNamHocKyHoc.getId())).thenReturn(Arrays.asList(dummyNienKhoaNganhNamHocKyHocMonHoc));

        String uri = ROOT_URI + "/nganhs/{nganhId}/mon-hocs";
        var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1));
        resultActions.andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllMonHocByNganh_invalidId() throws Exception {
        when(nienKhoaNganhService.findAllByNganhId(999)).thenReturn(null);

        String uri = ROOT_URI + "/nganhs/{nganhId}/mon-hocs";
        var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 999));
        resultActions.andExpect(status().isNoContent()).andReturn();
    }

    @Test
    public void getMonHoc() throws Exception {
        var dummyMonHcc = new MonHoc(1, "Mon Hoc A", 0.2, 0.2, 0.2, 0.2, 0.2);
        when(monHocService.findById(dummyMonHcc.getId())).thenReturn(dummyMonHcc);

        String uri = ROOT_URI + "/nganhs/{nganhId}/mon-hocs/{monHocId}";
        var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1, 1));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",				is(dummyMonHcc.getId())))
                .andExpect(jsonPath("$.ten",				is(dummyMonHcc.getTen())))
                .andExpect(jsonPath("$.tyLeDiemCC",		is(dummyMonHcc.getTyLeDiemCC())))
                .andExpect(jsonPath("$.tyLeDiemTH",		is(dummyMonHcc.getTyLeDiemTH())))
                .andExpect(jsonPath("$.tyLeDiemKT",		is(dummyMonHcc.getTyLeDiemKT())))
                .andExpect(jsonPath("$.tyLeDiemBT",		is(dummyMonHcc.getTyLeDiemBT())))
                .andExpect(jsonPath("$.tyLeDiemCuoiKy",	is(dummyMonHcc.getTyLeDiemCuoiKy())))
                .andReturn();
    }

    @Test
    public void getMonHoc_invalidId() throws Exception {
        var dummyMonHcc = new MonHoc(1, "Mon Hoc A", 0.2, 0.2, 0.2, 0.2, 0.2);
        when(monHocService.findById(dummyMonHcc.getId())).thenReturn(dummyMonHcc);

        String uri = ROOT_URI + "/nganhs/{nganhId}/mon-hocs/{monHocId}";
        var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1, 999));
        resultActions.andExpect(status().isNoContent()).andReturn();
    }

    @Test
    public void update() throws Exception {
        var dummy = new MonHoc(1, "Mon Hoc A", 0.2, 0.2, 0.2, 0.2, 0.2);
        var dummyJson = new ObjectMapper().writeValueAsString(dummy);

        // Truong hop cap nhat thanh cong, Service tra ve doi tuong duoc cap nhat.
        when(monHocService.update(any(MonHoc.class))).thenReturn(dummy);

        String uri = ROOT_URI + "/nganhs/{nganhId}/mon-hocs";
        var resultActions = mvc.perform(MockMvcRequestBuilders.put(uri, 1)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dummyJson));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",				is(dummy.getId())))
                .andExpect(jsonPath("$.ten",				is(dummy.getTen())))
                .andExpect(jsonPath("$.tyLeDiemCC",		is(dummy.getTyLeDiemCC())))
                .andExpect(jsonPath("$.tyLeDiemTH",		is(dummy.getTyLeDiemTH())))
                .andExpect(jsonPath("$.tyLeDiemKT",		is(dummy.getTyLeDiemKT())))
                .andExpect(jsonPath("$.tyLeDiemBT",		is(dummy.getTyLeDiemBT())))
                .andExpect(jsonPath("$.tyLeDiemCuoiKy",	is(dummy.getTyLeDiemCuoiKy())))
                .andReturn();
    }


    @Test
    public void update_invalidObject() throws Exception {
        var dummy = new MonHoc(1, "Mon Hoc A", 0.2, 0.2, 0.2, 0.2, 0.2);
        var dummyJson = new ObjectMapper().writeValueAsString(dummy);

        // Truong hop cap nhat doi tuong that bai, Service tra ve doi tuong duoc cap nhat.
        when(monHocService.update(any(MonHoc.class))).thenReturn(null);

        String uri = ROOT_URI + "/nganhs/{nganhId}/mon-hocs";
        var resultActions = mvc.perform(MockMvcRequestBuilders.put(uri, 1)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dummyJson));

        resultActions.andExpect(status().isNoContent()).andReturn();
    }
}