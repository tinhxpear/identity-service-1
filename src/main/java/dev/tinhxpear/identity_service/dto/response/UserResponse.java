package dev.tinhxpear.identity_service.dto.response;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
public class UserResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
