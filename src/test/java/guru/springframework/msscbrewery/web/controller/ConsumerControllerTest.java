package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.ConsumerService;
import guru.springframework.msscbrewery.web.model.ConsumerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsumerController.class)
public class ConsumerControllerTest {

    @MockBean
    ConsumerService consumerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    ConsumerDto validConsumer;

    @Before
    public void setUp() {
        validConsumer = ConsumerDto.builder()
                .id(UUID.randomUUID())
                .name("Pepe")
                .build();
    }

    @Test
    public void getConsumer() throws Exception {
        given(consumerService.getConsumerById(any(UUID.class))).willReturn(validConsumer);

        mockMvc.perform(get("/api/v1/consumer/" + validConsumer.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validConsumer.getId().toString())))
                .andExpect(jsonPath("$.name", is("Pepe")));



    }

    @Test
    public void handlePost() throws Exception {
        ConsumerDto consumerDto = validConsumer;
        consumerDto.setId(null);
        ConsumerDto savedDto = ConsumerDto.builder().id(UUID.randomUUID()).name("New User").build();
        String consumerDtoToJson = objectMapper.writeValueAsString(consumerDto);

        given(consumerService.saveNewConsumer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(consumerDtoToJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void handlePut() throws Exception {
        ConsumerDto consumerDto = validConsumer;
        String consumerDtoToJson = objectMapper.writeValueAsString(consumerDto);

        mockMvc.perform(put("/api/v1/consumer/" + validConsumer.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(consumerDtoToJson))
                .andExpect(status().isNoContent());

        then(consumerService).should().updateConsumer(any(), any());
    }

    @Test
    public void deleteConsumer() throws Exception {
        mockMvc.perform(delete("/api/v1/consumer/" + validConsumer.getId().toString()))
                .andExpect(status().isNoContent());
    }
}