package sqa.example.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
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
import sqa.example.model.ThangDiem;
import sqa.example.service.ThangDiemService;

import sqa.example.controller.ThangDiemConfigurationController;

@SpringBootTest
@AutoConfigureMockMvc
//@ComponentScan(basePackages = "sqa")
public class ThangDiemConfigurationControllerTest {
    private static final String ROOT_URI = "/api/v1/cau-hinh-thang-diem";

    @Mock private ThangDiemService thangDiemService;
    @InjectMocks private ThangDiemConfigurationController thangDiemConfigurationController;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        Mockito.reset();
        mvc = standaloneSetup(thangDiemConfigurationController).build();
    }

    @Test
    public void findAll() throws Exception {
        List<ThangDiem> dummies = Arrays.asList(
                new ThangDiem(1, 0.0, 2.5, "C", 1.0),
                new ThangDiem(2, 2.5, 5.0, "B", 2.0),
                new ThangDiem(3, 5.0, 7.5, "A", 3.0)
        );
        when(thangDiemService.findAll()).thenReturn(dummies);

        String uri = ROOT_URI + "/thang-diems";
        var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(dummies.size())))
                .andReturn();
    }

    @Test
    public void findById() throws Exception {
        var dummy = new ThangDiem(1, 0.0, 2.5, "C", 1.0);
        when(thangDiemService.findById(dummy.getId())).thenReturn(dummy);

        String uri = ROOT_URI + "/thang-diems/{id}";
        var resultActions = mvc.perform(MockMvcRequestBuilders.get(uri, 1));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",		is(dummy.getId())))
                .andExpect(jsonPath("$.from",		is(dummy.getFrom())))
                .andExpect(jsonPath("$.to",		is(dummy.getTo())))
                .andExpect(jsonPath("$.diemChu",	is(dummy.getDiemChu())))
                .andExpect(jsonPath("$.diemHe4",	is(dummy.getDiemHe4())))
                .andReturn();
    }

    @Test
    public void update() throws Exception {
        var dummy = new ThangDiem(1, 0.01, 2.51, "CCC", 1.01);
        var dummyJson = new ObjectMapper().writeValueAsString(dummy);

        // Truong hop cap nhat thanh cong, Service tra ve doi tuong duoc cap nhat.
        when(thangDiemService.update(any(ThangDiem.class))).thenReturn(dummy);

        String uri = ROOT_URI + "/thang-diems/";
        var resultActions = mvc.perform(MockMvcRequestBuilders.put(uri)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dummyJson));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",		is(dummy.getId())))
                .andExpect(jsonPath("$.from",		is(dummy.getFrom())))
                .andExpect(jsonPath("$.to",		is(dummy.getTo())))
                .andExpect(jsonPath("$.diemChu",	is(dummy.getDiemChu())))
                .andExpect(jsonPath("$.diemHe4",	is(dummy.getDiemHe4())))
                .andReturn();
    }

    @Test
    public void update_invalidObject() throws Exception {
        var dummy = new ThangDiem(999, 999.0, 999.0, "XYZ", 999.0);
        var dummyJson = new ObjectMapper().writeValueAsString(dummy);

        // Truong hop cap nhat doi tuong that bai hoac khong hop le, Service tra ve null.
        when(thangDiemService.update(any(ThangDiem.class))).thenReturn(null);

        String uri = ROOT_URI + "/thang-diems/";
        var resultActions = mvc.perform(MockMvcRequestBuilders.put(uri)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dummyJson));

        resultActions.andExpect(status().isNoContent()).andReturn();
    }
}