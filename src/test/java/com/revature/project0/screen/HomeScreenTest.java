package com.revature.project0.screen;

import com.revature.project0.models.User;
import com.revature.project0.repo.UserRepository;
import com.revature.project0.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public class HomeScreenTest {

    private UserService sut;
    private UserRepository mockUserRepo = Mockito.mock(UserRepository.class);
    Set<User> mockUsers = new HashSet<>();

    @Before
    public void setup() {
        sut = new UserService(mockUserRepo);
        mockUsers.add(new User (1, "Adam", "Inn", "adaminn", "adaminnn@gmail.com", "password"));
        mockUsers.add(new User (2, "Albert", "Nice", "alnice", "alnice@gmail.com", "password"));
    }

    @After
    public void tearDown() {
        sut = null;
        mockUsers.removeAll(mockUsers);
    }


}
