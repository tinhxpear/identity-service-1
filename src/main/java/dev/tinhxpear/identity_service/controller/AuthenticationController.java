package dev.tinhxpear.identity_service.controller;

import com.nimbusds.jose.JOSEException;
import dev.tinhxpear.identity_service.dto.request.AuthenticationRequest;
import dev.tinhxpear.identity_service.dto.request.IntrospectRequest;
import dev.tinhxpear.identity_service.dto.response.ApiResponse;
import dev.tinhxpear.identity_service.dto.response.AuthenticationResponse;
import dev.tinhxpear.identity_service.dto.response.IntrospectResponse;
import dev.tinhxpear.identity_service.exception.AppException;
import dev.tinhxpear.identity_service.exception.ErrorCode;
import dev.tinhxpear.identity_service.repository.UserRepository;
import dev.tinhxpear.identity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;
    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(result)
                .build();
    }

}
