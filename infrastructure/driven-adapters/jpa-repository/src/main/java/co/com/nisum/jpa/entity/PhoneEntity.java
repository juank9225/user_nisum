package co.com.nisum.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "phones_users")
@NoArgsConstructor
@AllArgsConstructor
public class PhoneEntity {

    @Id
    @Column(name = "number", nullable = false, length = 250)
    private String number;

    @Column(name = "cityCode", nullable = false, length = 250)
    private String cityCode;

    @Column(name = "countryCode", nullable = false, length = 250)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private UserEntity user;
}
