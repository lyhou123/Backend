package co.istad.backend.features.user;


import co.istad.backend.features.user.dto.ResponseUserDto;
import co.istad.backend.features.user.dto.UpdateUserDto;
import co.istad.backend.security.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

/**
 *   UserService interface for user service
 *   @version 1.0
 * @author : lyhou
 * @see UserServiceImpl
 *
 */

public interface UserService {

    /**
     * Get user by uuid
     * @return {@link ResponseUserDto}
     * @param uuid is identifier of user
     * @author : lyhou
     */
    ResponseUserDto getUserByUuid(String uuid);


    /**
     * Delete user
     * @param uuid is identifier of user
     * @author : lyhou
     */
    void deleteUser(String uuid);

    /**
     * Get all users
     * @return {@link List<ResponseUserDto>}
     * @author : lyhou
     *
     */
    List<ResponseUserDto> getAllUsers();


    /**
     * Get all user by page
     * @return {@link ResponseUserDto}
     * @author : lyhou
     */

    Page<ResponseUserDto> getAllUsersByPage(int page, int size);

    /**
     * Update user profile
     * @param customUserDetails is custom user details
     * @return {@link ResponseUserDto}
     *
     */

    ResponseUserDto updateProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails, UpdateUserDto updateUserDto);


    /**
     * Block user
     * @param uuid is identifier of user
     * @author : lyhou
     */
    void blockUser(String uuid);

    /**
     * Unblock user
     * @param uuid is identifier of user
     * @author : lyhou
     */

    void unblockUser(String uuid);

    /**
     * count all user
     */
    int countAllUser();


}

