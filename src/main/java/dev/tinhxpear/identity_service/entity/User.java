package dev.tinhxpear.identity_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String id;
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
     Set<String> roles;

}
