package co.istad.backend.features.auth;



import co.istad.backend.base.BaseResponse;
import co.istad.backend.features.auth.dto.AuthRequest;
import co.istad.backend.features.auth.dto.AuthResponse;
import co.istad.backend.features.auth.dto.RefreshTokenRequest;
import co.istad.backend.features.user.dto.ResponseUserDto;
import co.istad.backend.features.user.dto.UserRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController

@RequestMapping("api/v1/auth")
@RequiredArgsConstructor

public class AuthRestController {

    private final AuthServiceImpl authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest)
    {
       return BaseResponse.<AuthResponse>
               builder()
               .status(HttpStatus.OK.value())
               .timestamp(LocalDateTime.now())
               .data(authService.login(authRequest))
               .message("Login successfully")
               .build();
    }


   @PostMapping("/register")
   @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ResponseUserDto> signup(@Valid @RequestBody UserRegisterDto userRegisterDto)
    {
        return BaseResponse.<ResponseUserDto>builder()
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .data(authService.createUser(userRegisterDto))
                .message("User created successfully")
                .build();
    }


    @PostMapping("/refreshToken")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {

        return BaseResponse.<AuthResponse>builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(authService.refreshToken(request))
                .message("Token refreshed successfully")
                .build();

    }
}
