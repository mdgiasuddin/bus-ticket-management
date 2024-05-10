package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.service.RouteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {TestContext.class, RouteController.class})
public class RouteControllerTest {

    @MockBean
    RouteService routeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetAllRoutes() throws Exception {
        when(routeService.getAllRoutes())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/routes").contentType("application/json"))
                .andExpect(status().isOk());
    }
}
