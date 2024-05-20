package bg.softuni.UserSystem.services;

import bg.softuni.UserSystem.entities.User;
import bg.softuni.UserSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final String USERS_FILE_PATH = "src/main/resources/dbContent/users.txt";

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void seedUsersInDatabase() throws IOException {
        if (userRepository.count() > 0) return;

        Files.readAllLines(Path.of(USERS_FILE_PATH))
                .stream()
                .skip(1)
                .forEach(line -> {
                    String[] args = line.split(", ");
                    String username = args[0];
                    String password = args[1];
                    String email = args[2];
                    LocalDateTime lastTimeLoggedIn = LocalDateTime.parse(args[3],
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                    boolean isDeleted = Boolean.parseBoolean(args[4]);

                    User user = new User(username, password, email, lastTimeLoggedIn, isDeleted);
                    userRepository.saveAndFlush(user);
                });
    }

    @Override
    public void seedUserWithWrongEmail() {
        userRepository.saveAndFlush(new User("user_4", "DaNi23$", "mike@.unknown.soft",
                LocalDateTime.parse("22/04/2022 01:19:41", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                false));
    }

    @Override
    public void seedUserWithWrongPassword() {
        userRepository.saveAndFlush(new User("user_4", "dani", "123@gmail.com",
                LocalDateTime.parse("22/04/2022 01:19:41", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                false));
    }

    @Override
    public void printUsernamesAndEmailsByEmailProvider(String emailProvider) {
        List<User> users = userRepository.findUsersByEmailContaining(emailProvider.toLowerCase());

        if (users.isEmpty()) {
            System.out.println("No such email provider");
        } else {
            users.forEach(user -> System.out.println(user.getUsernameAndEmail()));
        }
    }
    //в задачата се иска първо is_deleted на юзърите логнати преди дадената дата да се сетне на true,
    //да се изпринти броят им и тогава да се изтрият, което според мен е безсмислено като може направо да ги изтрием
    @Override
    public void printCountOfUsersSetAsDeleted(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<User> users = userRepository.getByLastTimeLoggedDateBeforeGivenDate(localDate);

        if (!users.isEmpty()) {
            users.forEach(user -> {
                user.setIsDeleted(true);
                userRepository.saveAndFlush(user);
            });
        }
        System.out.println(users.size());
    }

    @Override
    public void deleteUsersSetAsDeleted() {
        userRepository.deleteAll(userRepository.findUsersByIsDeleted(true));
    }
}
