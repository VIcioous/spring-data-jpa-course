package com.example.demo.user;

import com.example.demo.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUser implements UserDetails {


    @SequenceGenerator(name = "user_Sequence",
            sequenceName = "user_Sequence",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_Sequence")
    private Long id;
    private String name;
    private String surname;
    private String email;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
    @Column(name = "is_unlocked")

    private boolean isUnlocked;
    @Column(name = "is_enabled")
    private boolean isEnabled;

    private int blames;
@JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Reservation> listOfReservations;

    public AppUser(String name,
                   String surname,
                   String email,
                   String password,
                   UserType userType
    ) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.isEnabled = false;
        this.isUnlocked = true;
        this.blames = 0;
        this.listOfReservations = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userType.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isUnlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}
