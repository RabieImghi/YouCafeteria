package org.rabie.youcafeteria.domain;

import jakarta.persistence.*;
import lombok.*;
import org.rabie.youcafeteria.domain.enums.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Role role;

    @OneToMany(mappedBy = "appUser")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "appUser")
    private List<Notification> notifications;

}
