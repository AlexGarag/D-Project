package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;

//import java.util.HashSet;
//import java.util.Set;

@Entity
@Data
@ToString
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}