package sqa.example.ptit.controllerTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sqa.example.controller.ManagerStudentOfLHPController;
import sqa.example.model.NamHoc;
import sqa.example.repository.NamHocRepository;


import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@WebMvcTest(ManagerStudentOfLHPController.class)
public class ManagerStudentLHPControllerTest {

    @MockBean
    private  NamHocRepository namHocRepository;
    @Autowired
    private  MockMvc mockMvc;


    @Test
    public void getAllNamHoc() throws Exception {
        MockitoAnnotations.openMocks(this);
        List<NamHoc> namHocList = List.of(
                new NamHoc(1, "2017-2018"),
                new NamHoc(2, "2018-2019"),
                new NamHoc(3, "2019-2020"),
                new NamHoc(4, "2020-2021"),
                new NamHoc(5, "2021-2022"));
        Mockito.when(namHocRepository.findAll()).thenReturn(namHocList);
        String URI = "/api/v1/admin/nam-hocs";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().toString());
        String expectedJson = this.mapToJson(namHocList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
