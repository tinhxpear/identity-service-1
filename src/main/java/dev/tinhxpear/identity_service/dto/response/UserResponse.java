package dev.tinhxpear.identity_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
     String id;
     String username;
     String firstName;
     String lastName;
     LocalDate dob;
    Set<String> roles;
}
