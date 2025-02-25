package co.istad.backend.features.auth;



import co.istad.backend.features.auth.dto.AuthRequest;
import co.istad.backend.features.auth.dto.AuthResponse;
import co.istad.backend.features.auth.dto.RefreshTokenRequest;
import co.istad.backend.features.user.dto.ResponseUserDto;
import co.istad.backend.features.user.dto.UserRegisterDto;


/**
 * AuthService
 * @author : @lyhou
 * @since : 1.0 (2024)
 * @see AuthServiceImpl
 */
public interface AuthService {


    /**
     * Login user
     * @param authRequest is the request object
     * @return {@link AuthResponse}
     * author : @lyhou
     *
     */
    AuthResponse login(AuthRequest authRequest);

    /**
     * Create user
     * @param userRegisterDto is the request object
     * @return {@link ResponseUserDto}
     * author : @lyhou
     *
     */
    ResponseUserDto createUser(UserRegisterDto userRegisterDto);

    /**
     * Refresh token
     * @param refreshToken is the request object
     * @return {@link AuthResponse}
     * author : @lyhou
     *
     */
    AuthResponse refreshToken(RefreshTokenRequest refreshToken);



}
