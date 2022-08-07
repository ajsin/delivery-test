package com.delivery.api;

import com.delivery.api.application.dto.UserSignInReqDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryApplicationTests {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("로그인 테스트")
    void testSignIn() throws Exception {
        String body = mapper.writeValueAsString(
                UserSignInReqDTO.builder().id("ajsin").pw("Minho6565!@#").build()
        );
        mvc.perform(post("/user/sign-in")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").isString());
    }
}
