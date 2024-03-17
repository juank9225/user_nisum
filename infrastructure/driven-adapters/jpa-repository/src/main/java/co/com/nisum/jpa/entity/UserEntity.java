package co.com.nisum.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "user_aut")
public class UserEntity {

    @Column(name = "id", nullable = false, length = 250)
    private String id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Id
    @Column(name = "email", nullable = false, length = 250, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "modified", nullable = false)
    private Date modified;

    @Column(name = "lastLogin", nullable = false)
    private Date lastLogin;

    @Column(name = "token", nullable = false, length = 250)
    private String token;

    @Column(name = "isActive", nullable = false, length = 250)
    private Boolean isActive;
}
