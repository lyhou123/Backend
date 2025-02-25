package co.istad.backend.features.user;


import co.istad.backend.base.BaseResponse;
import co.istad.backend.features.user.dto.ResponseUserDto;
import co.istad.backend.features.user.dto.UpdateUserDto;
import co.istad.backend.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserRestController {

    private final UserServiceImpl userService;


    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public BaseResponse<ResponseUserDto> findUserByUuid(@PathVariable String uuid) {

        return BaseResponse
                .<ResponseUserDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .data(userService.getUserByUuid(uuid))
                .message("User has been found successfully.")
                .build();
    }


    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseUserDto> getAllUsersByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size) {

            return userService.getAllUsersByPage(page, size);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<ResponseUserDto>> getAllUsers() {

        return BaseResponse
                .<List<ResponseUserDto>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .data(userService.getAllUsers())
                .message("Users found successfully.")
                .build();
    }


    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<ResponseUserDto> deleteUserByUuid(@PathVariable String uuid) {

        userService.deleteUser(uuid);

        return BaseResponse
                .<ResponseUserDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NO_CONTENT.value())
                .message("User has been deleted successfully.")
                .build();

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public BaseResponse<ResponseUserDto> updateProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails, @Valid @RequestBody UpdateUserDto updateUserDto) {

        return BaseResponse
                .<ResponseUserDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .data(userService.updateProfile(customUserDetails,updateUserDto))
                .message("Profile has been updated successfully.")
                .build();
    }


    @PutMapping("/block/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<ResponseUserDto> blockUser(@PathVariable String uuid) {

        userService.blockUser(uuid);

        return BaseResponse
                .<ResponseUserDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("User has been blocked successfully.")
                .build();

    }

    @PutMapping("/unblock/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<ResponseUserDto> unblockUser(@PathVariable String uuid) {

        userService.unblockUser(uuid);

        return BaseResponse
                .<ResponseUserDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("User has been unblocked successfully.")
                .build();

    }


    @Operation(summary = "count all user")
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Integer> countAllUser() {

        return BaseResponse
                .<Integer>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .data(userService.countAllUser())
                .message("User count found successfully.")
                .build();
    }
}
