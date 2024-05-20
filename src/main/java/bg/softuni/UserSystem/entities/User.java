package bg.softuni.UserSystem.entities;

import bg.softuni.UserSystem.annotations.Email;
import bg.softuni.UserSystem.annotations.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @NotNull
    @Size(min = 4, max = 30)
    private String username;

    @NotNull
    @Password
    private String password;

    @NotNull
    @Email
    private String email;

    @Column(name = "registered_on")
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Short age;

    @ManyToMany
    private Set<User> friends;

    @ManyToOne
    private Town bornTown;

    @ManyToOne
    private Town currentlyLivingTown;

    @OneToMany
    private Set<Album> albums;

    public User() {}

    public User(String username, String password, String email,
                LocalDateTime lastTimeLoggedIn, Boolean isDeleted) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.isDeleted = isDeleted;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getUsernameAndEmail() {
        return String.format("Username: %s -> Email: %s", this.username, this.email);
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
