//package com.capstone.journly;
//
//
//
//import com.capstone.journly.models.Bookshelf;
//import com.capstone.journly.models.GratitudeEntry;
//import com.capstone.journly.models.Prompt;
//import com.capstone.journly.models.User;
//import com.capstone.journly.repositories.GratitudeEntryRepository;
//import com.capstone.journly.repositories.PromptRepository;
//import com.capstone.journly.repositories.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class StartupRunner implements CommandLineRunner {
//
//    private final UserRepository userDao;
//    private final GratitudeEntryRepository gratitudeEntryDao;
//    private final PasswordEncoder encoder;
//    private final PromptRepository promptDao;
//
//    public StartupRunner(UserRepository userDao, GratitudeEntryRepository gratitudeEntryDao, PasswordEncoder encoder, PromptRepository promptDao) {
//        this.userDao = userDao;
//        this.gratitudeEntryDao = gratitudeEntryDao;
//        this.encoder = encoder;
//        this.promptDao = promptDao;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        if (userDao.count() != 0) {
//            return;
//        }
//        User user = new User();
//        user.setUsername("cody");
//        user.setEmail("cody@codeup.com");
//        user.setPassword(encoder.encode("codeuprocks"));
////        user.setBookshelf(new Bookshelf());
//        userDao.save(user);
//
//        GratitudeEntry gratitudeEntry = new GratitudeEntry();
//        gratitudeEntry.setUser(user);
//        gratitudeEntry.setCreatedAt(new Date(System.currentTimeMillis()));
//        gratitudeEntry.setPrompt(promptDao.getOne(1L));
//        gratitudeEntry.setBody("lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem ");
//        gratitudeEntry.setImgFilePath("/uploads/default3.jpg");
//        gratitudeEntry.setIsPublic(true);
//        gratitudeEntryDao.save(gratitudeEntry);
//
//        GratitudeEntry gratitudeEntry2 = new GratitudeEntry();
//        gratitudeEntry2.setUser(user);
//        gratitudeEntry2.setCreatedAt(new Date(System.currentTimeMillis()));
//        gratitudeEntry2.setPrompt(promptDao.getOne(3L));
//        gratitudeEntry2.setBody("Here is an example of a journal post.");
//        gratitudeEntry2.setImgFilePath("/uploads/default2.jpeg");
//        gratitudeEntry2.setIsPublic(true);
//        gratitudeEntryDao.save(gratitudeEntry2);
//
//        GratitudeEntry gratitudeEntry3 = new GratitudeEntry();
//        gratitudeEntry3.setUser(user);
//        gratitudeEntry3.setCreatedAt(new Date(System.currentTimeMillis()));
//        gratitudeEntry3.setPrompt(promptDao.getOne(4L));
//        gratitudeEntry3.setBody("I am so hap!");
//        gratitudeEntry3.setImgFilePath("/uploads/default.jpeg");
//        gratitudeEntry3.setIsPublic(false);
//        gratitudeEntryDao.save(gratitudeEntry3);
//
//    }
//}
