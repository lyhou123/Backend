package co.istad.backend.features.user;


import co.istad.backend.domain.User;
import co.istad.backend.features.user.dto.ResponseUserDto;
import co.istad.backend.features.user.dto.UpdateUserDto;
import co.istad.backend.mapper.UserMapper;
import co.istad.backend.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RequiredArgsConstructor
@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public ResponseUserDto getUserByUuid(String uuid) {

        User findUser = userRepository.findUserByUuid(uuid);

        if(findUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with uuid: "+uuid);
        }

        return userMapper.mapFromUserToUserResponseDto(findUser);
    }


    @Override
    public void deleteUser(String uuid) {

        User findUser = userRepository.findUserByUuid(uuid);

        if(findUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with uuid: "+uuid);
        }

        findUser.setIsDeleted(true);

        userRepository.save(findUser);

    }

    @Override
    public List<ResponseUserDto> getAllUsers() {

        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User is empty");
        }

        return userMapper.mapFromListOfUserToListOfUserDto(userList);
    }

    @Override
    public Page<ResponseUserDto> getAllUsersByPage(int page, int size) {

        if(page < 0 || size < 0){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Page and size must be greater than 0");

        }

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));

        Page<User> userPage = userRepository.findAll(pageRequest);

        List<User> userFilter = userRepository.findAll().stream()
                .filter(user -> !user.getIsDeleted())
                .toList();

        if(userPage.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User is empty");

        }

        return new PageImpl<>
                (userMapper.mapFromListOfUserToListOfUserDto(userFilter),
                        pageRequest,userFilter.size());

    }


    @Override
    public ResponseUserDto updateProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails, UpdateUserDto updateUserDto) {

        if(customUserDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User unauthorized");

        }

        String uuid = customUserDetails.getUserUuid();

        User user = userRepository.findUserByUuid(uuid);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with uuid: "+uuid);
        }

        userMapper.updateUserFromRequest(user,updateUserDto);

        userRepository.save(user);

        return userMapper.mapFromUserToUserResponseDto(user);

    }

    @Override
    public void blockUser(String uuid) {

        User findUser = userRepository.findUserByUuid(uuid);

        if(findUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with uuid: "+uuid);
        }

        findUser.setIsEnabled(true);

        userRepository.save(findUser);

    }

    @Override
    public void unblockUser(String uuid) {

            User findUser = userRepository.findUserByUuid(uuid);

            if(findUser == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with uuid: "+uuid);
            }

            findUser.setIsEnabled(false);

            userRepository.save(findUser);

    }

    @Override
    public int countAllUser() {

        if (userRepository.findAll().isEmpty()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is empty");

        }

        return userRepository.findAll().size();
    }


}
