package ru.otus.prj.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.prj.backend.controller.BackEndController;
import ru.otus.prj.backend.request.BackEndRequest;
import ru.otus.prj.backend.response.BackEndResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BackEndController.class)
public class BackEndControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getResponseWithStatus400() throws Exception {
        mvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCorrectResponse() throws Exception {
        BackEndResponse backEndResponse = new BackEndResponse(6);
        String goodRespJson = mapToJson(backEndResponse);

        String inputJson = mapToJson(new BackEndRequest(1, 2, 3));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(goodRespJson, mvcResult.getResponse().getContentAsString());
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
