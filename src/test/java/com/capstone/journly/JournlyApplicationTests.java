package com.capstone.journly;

import com.capstone.journly.models.User;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
//import org.junit.Test;

@SpringBootTest
class JournlyApplicationTests {

    private MockMvc mvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(mvc);
    }
}
