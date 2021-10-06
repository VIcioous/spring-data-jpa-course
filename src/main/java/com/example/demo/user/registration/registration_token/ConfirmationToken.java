package com.example.demo.user.registration.registration_token;

import com.example.demo.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "confirmation_token")
public class ConfirmationToken {

    @SequenceGenerator(name = "token_Sequence",
            sequenceName = "token_Sequence"
            , allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_Sequence")
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiresAt;
    @Column(name = "confirmed_at")

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id")
    private AppUser appUser;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser=appUser;
    }
}
