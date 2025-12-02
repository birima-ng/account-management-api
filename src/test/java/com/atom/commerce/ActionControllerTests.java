package com.atom.commerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.atom.artaccount.ArtAccountApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ArtAccountApplication.class)
@AutoConfigureMockMvc  // active MockMvc
class ActionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    void testSayHello() throws Exception {
    	System.out.println("###################################### birima 111");
        mockMvc.perform(get("/api/action"))
                .andExpect(status().isOk()) // vérifier code HTTP 200
                //.andExpect(content().string("4028b8815645c7d4015645ca08570005"))
                ; // vérifier réponse
    }
}
