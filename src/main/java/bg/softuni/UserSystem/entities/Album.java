package bg.softuni.UserSystem.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String name;
    private String backgroundColor;
    private Boolean isPublic;

    @ManyToMany
    private Set<Picture> pictures;
}
