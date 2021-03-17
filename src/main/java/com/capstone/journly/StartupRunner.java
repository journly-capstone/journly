package com.capstone.journly;



import com.capstone.journly.models.GratitudeEntry;
import com.capstone.journly.models.User;
import com.capstone.journly.repositories.GratitudeEntryRepository;
import com.capstone.journly.repositories.PromptRepository;
import com.capstone.journly.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StartupRunner implements CommandLineRunner {

    private final UserRepository userDao;
    private final GratitudeEntryRepository gratitudeEntryDao;
    private final PasswordEncoder encoder;
    private final PromptRepository promptDao;

    public StartupRunner(UserRepository userDao, GratitudeEntryRepository gratitudeEntryDao, PasswordEncoder encoder, PromptRepository promptDao) {
        this.userDao = userDao;
        this.gratitudeEntryDao = gratitudeEntryDao;
        this.encoder = encoder;
        this.promptDao = promptDao;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userDao.count() != 0) {
            return;
        }
        User user = new User();
        user.setUsername("cody");
        user.setEmail("cody@codeup.com");
        user.setPassword(encoder.encode("codeuprocks"));
        userDao.save(user);

        GratitudeEntry gratitudeEntry = new GratitudeEntry();
        gratitudeEntry.setUser(user);
        gratitudeEntry.setCreatedAt(new Date(System.currentTimeMillis()));
        gratitudeEntry.setPrompt(promptDao.findRandomPrompt());
        gratitudeEntry.setBody("lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem ");
        gratitudeEntry.setImgFilePath("/Users/kristencollier/IdeaProjects/journly/src/main/resources/static/uploads/default.jpeg");
        gratitudeEntry.setIsPublic(true);

        gratitudeEntryDao.save(gratitudeEntry);



    }
}
