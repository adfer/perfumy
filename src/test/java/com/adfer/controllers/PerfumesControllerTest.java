package com.adfer.controllers;

import com.adfer.Application;
import com.adfer.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by adrianferenc on 06.10.2016.
 */
@ContextConfiguration(classes = {Application.class})
@ActiveProfiles("test")
@WebAppConfiguration
@Test(enabled = false)
public class PerfumesControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private PerfumeRepository repository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test(enabled = false)
    public void shouldThrowExceptionIncorrectPerfumeCategory() throws Exception {
        mockMvc.perform(get("/perfumes/fakeCategory"));
    }

    @Test(enabled = false)
    public void shouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/perfumes/women"))
                .andExpect(status().isOk());
    }
}