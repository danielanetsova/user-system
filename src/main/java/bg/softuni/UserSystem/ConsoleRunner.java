package bg.softuni.UserSystem;

import bg.softuni.UserSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsersInDatabase();
//        userService.seedUserWithWrongEmail();
//        userService.seedUserWithWrongEmail();
//        userService.printUsernamesAndEmailsByEmailProvider("Gmail");
//        userService.printCountOfUsersSetAsDeleted("31/12/2012");
        userService.deleteUsersSetAsDeleted();
    }
}
