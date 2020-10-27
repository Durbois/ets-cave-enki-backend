package com.etscaveenki.caveenki.repository;

import com.etscaveenki.caveenki.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    @Sql("user.sql")
    public void whenInitializedByDbUnit_thenFindsByName() {
        Optional<User> userOptional = userRepository.findByUsername("user");
        assert (userOptional != null);
        assert( "user".equals(userOptional.get().getUsername()) );
    }
}