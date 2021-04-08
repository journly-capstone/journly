package com.capstone.journly;

import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.Prompt;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.*;

import org.hibernate.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JournlyApplication.class)
@AutoConfigureMockMvc
public class JournlyCoverageTesting {

    private User user;
    private GratitudeEntry entry;
    private HttpSession http;
    private Session session;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private GratitudeEntryRepository entryDao;

    @Autowired
    private PromptRepository promptDao;

    @Autowired
    private QuoteRepository quoteDao;

    @Autowired
    private LikeRepository likeDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {
        user = userDao.findByUsername("educator");
        entry = entryDao.findGratitudeEntriesById(1);

        if (user == null) {
            User newUser = new User();
            newUser.setUsername("educator");
            newUser.setEmail("educator@educate.com");
            newUser.setPassword("teach");
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.setImgFilePath("/img/rocks.jpg");
            user = userDao.save(newUser);
        }

        if (entry == null) {
            GratitudeEntry newEntry = new GratitudeEntry();
            newEntry.setPrompt(promptDao.findRandomPrompt());
            newEntry.setBody("Placeholder text for testing Gratitude Entry body");
            newEntry.setCreatedAt(new Date());
            newEntry.setImgFilePath("/img/rocks.jpg");
            newEntry.setIsPublic(true);
            entry = entryDao.save(newEntry);
        }

        http = this.mvc.perform(post("/login").with(csrf())
                .param("username", "educator")
                .param("password", "teach"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/dashboard"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    public void userActive() throws Exception {
        assertNotNull(http);
    }

    @Test
    public void landingPageTest() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void registrationPageTest() throws Exception {
        this.mvc.perform(get("/sign-up"))
                .andExpect(status().isOk());
    }

    @Test
    @Rollback(false)
    // allows data to be available in next methods
    public void createGratitudeEntryTest() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.post("/dashboard/" + entry.getId()).with(csrf())
                .session((MockHttpSession) http)
                .param("prompt", String.valueOf(new Prompt()))
                .param("body", "Placeholder body")
                .param("createdAt", String.valueOf(new Date()))
                .param("imgFilePath", "/img/rocks.jpg")
                .param("isPublic", "true"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void readGratitudeEntryTest() throws Exception {
        this.mvc.perform(get("/gratitude-board/" + entry.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(entry.getBody())));
    }


}
