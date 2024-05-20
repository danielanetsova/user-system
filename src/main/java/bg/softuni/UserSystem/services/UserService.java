package bg.softuni.UserSystem.services;

import java.io.IOException;

public interface UserService {
    void seedUsersInDatabase() throws IOException;

    void seedUserWithWrongEmail();
    void seedUserWithWrongPassword();

    void printUsernamesAndEmailsByEmailProvider(String emailProvider);

    void printCountOfUsersSetAsDeleted(String date);
    void deleteUsersSetAsDeleted();
}
