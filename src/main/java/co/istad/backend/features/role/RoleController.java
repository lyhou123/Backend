package co.istad.backend.features.role;

import co.istad.backend.base.BaseResponse;
import co.istad.backend.features.role.dto.RoleRequest;
import co.istad.backend.features.role.dto.RoleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor

public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<List<RoleResponse>> getAllUserRoles(){

        return BaseResponse
                .<List<RoleResponse>>builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(roleService.getAllUserRoles())
                .build();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<RoleResponse> createRole(@Valid @RequestBody RoleRequest roleRequest){

        return BaseResponse
                .<RoleResponse>builder()
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .data(roleService.createRole(roleRequest))
                .build();
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<RoleResponse> getRole(@PathVariable String uuid){

        return BaseResponse
                .<RoleResponse>builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(roleService.getRole(uuid))
                .build();
    }


    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<RoleResponse> updateRole(@PathVariable String uuid, @Valid @RequestBody RoleRequest roleRequest){

        return BaseResponse
                .<RoleResponse>builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(roleService.updateRole(uuid, roleRequest))
                .build();
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(@PathVariable String uuid){

        roleService.deleteRole(uuid);

    }



}
