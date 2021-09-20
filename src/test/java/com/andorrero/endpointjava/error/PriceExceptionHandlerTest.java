package com.andorrero.endpointjava.error;

import com.andorrero.endpointjava.controller.PriceController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static com.andorrero.endpointjava.error.PriceErrorCode.ERR_001;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PriceController.class)
class PriceExceptionHandlerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceController priceController;

    @Test
    void testWhenMissingRequestParametersShouldHandleMissingServletRequestParameterException() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/brand/1/product/1/pvp")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.description").value(ERR_001.getReasonPhrase()))
                .andReturn();
    }

}
