package com.aphabankTest.Task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@WebMvcTest
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldShowGif() {
        String wrongSrc = "";
        List<Endpoints> endpointsList = Arrays.asList(Endpoints.values());

        endpointsList.forEach(endpoint -> {

            try {
                mockMvc.perform(get("/get/" + endpoint.toString()))
                        .andExpect(status().isOk())
                        .andExpect(xpath("//iframe[@src='" + wrongSrc + "']").doesNotExist());
                System.out.println(endpoint.toString() + " passed ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}