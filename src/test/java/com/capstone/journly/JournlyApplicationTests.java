package com.capstone.journly;

import com.capstone.journly.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.Test;
import static org.junit.Assert.*;

@SpringBootTest
class JournlyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateUser () {
        User testUser = new User(1L, "user");
    }

    @Test
    public void testCreateGratitudeEntry () {

    }

    @Test
    public void testLike () {

    }

    @Test
    public void testDeleteGratitudeEntry () {

    }

    @Test
    public void addBook () {

    }

    @Test
    public void deleteBook () {

    }





}
