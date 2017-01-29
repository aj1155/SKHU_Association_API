package kr.ac.skhu.controller;


import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Manki Kim on 2017-01-23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class BoardPostControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCreateBoardPost() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/boardpost/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"title\":\"모임공지1\"," +
                        "\"content\":\"수요일 1시 미가엘관\"," +
                        "\"writer_id\":\"3\"," +
                        "\"writer_name\":\"김만기\"," +
                        "\"ownBoardId\":\"1\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        log.debug("{}", result.getResponse().getContentAsString());

    }

    @Test
    public void testReadGet() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/boardpost/category/1/boardType/공지사항"))
                .andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        log.debug("{}", result.getResponse().getContentAsString());

    }

    @Test
    public void testUpdateBoardPost() throws Exception {
        MvcResult result = mockMvc.perform(put("/api/v1/boardpost/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"id\":\"9\"," +
                        "\"title\":\"모임공지9\"," +
                        "\"content\":\"수요일 1시 미가엘관\"," +
                        "\"writer_id\":\"3\"," +
                        "\"writer_name\":\"김만기\"," +
                        "\"ownBoardId\":\"1\"" +
                        "}"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        log.debug("{}", result.getResponse().getContentAsString());

    }

    @Test
    public void testDelete() throws Exception {
        MvcResult result = mockMvc.perform(delete("/api/v1/boardpost/9"))
                .andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        log.debug("{}", result.getResponse().getContentAsString());

    }


}
