package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.UsersApi;
import ch.heigvd.amt.api.model.User;
import ch.heigvd.amt.api.model.UserDTO;
import ch.heigvd.amt.api.service.Authentication;
import ch.heigvd.amt.api.service.DecodedToken;
import ch.heigvd.amt.api.util.Role;
import ch.heigvd.amt.entities.UserEntity;
import ch.heigvd.amt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    Authentication authentication;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    ////////////////// CREATE //////////////////
    @Override
    public ResponseEntity<Void> createUsers(String authorization, @Valid UserDTO user) {
        DecodedToken decodedToken = (DecodedToken) httpServletRequest.getAttribute("token");

        if(!decodedToken.isAdmin())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if(userRepository.existsByEmail(user.getEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        if(user.getRole() == null || (!user.getRole().equals(Role.USER) && !user.getRole().equals(Role.ADMIN)))
            user.setRole(Role.USER);

        user.setPassword(authentication.hashPassword(user.getPassword()));
        UserEntity userEntity = toUserEntity(user);

        userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    ////////////////// READ //////////////////
    @Override
    public ResponseEntity<List<User>> getUsers(String authorization) {
        List<User> users = new ArrayList<>();
        for(UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUserById(Integer userid, String authorization) {
        Optional<UserEntity> userEntity = userRepository.findById(userid);

        if(userEntity.isPresent()) {
            return ResponseEntity.ok(toUser(userEntity.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    ////////////////// UPDATE //////////////////
    @Override
    public ResponseEntity<Void> updateUserById(Integer userid, String authorization, @Valid UserDTO user) {
        UserEntity entity;

        try {
            entity = userRepository.findById(userid).get();

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        DecodedToken decodedToken = (DecodedToken) httpServletRequest.getAttribute("token");

        if(decodedToken.isAdmin() || entity.getEmail().equals(decodedToken.getEmail())) {
            changeElements(entity, user);
            userRepository.save(entity);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    ////////////////// DELETE //////////////////
    @Override
    public ResponseEntity<Void> deleteUserById(Integer userid, String authorization) {
        DecodedToken decodedToken = (DecodedToken) httpServletRequest.getAttribute("token");
        if(!decodedToken.isAdmin())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if (!userRepository.existsById(userid))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        userRepository.deleteById(userid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setFirstname(entity.getFirstname());
        user.setLastname(entity.getLastname());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());

        return user;
    }

    private void changeElements(UserEntity entity, UserDTO dto) {
        String firstname = dto.getFirstname();
        String lastname = dto.getLastname();
        String email = dto.getEmail();
        String password = dto.getPassword();
        String role = dto.getRole();

        if(firstname != null && !firstname.isEmpty())
            entity.setFirstname(firstname);

        if(lastname != null && !lastname.isEmpty())
            entity.setLastname(lastname);

        if(email != null && !email.isEmpty())
            entity.setEmail(email);

        if(password != null && !password.isEmpty())
            entity.setPassword(authentication.hashPassword(password));

        if(role == null || (!role.equals(Role.USER) && !role.equals(Role.ADMIN)))
            entity.setRole(Role.USER);
        else
            entity.setRole(role);
    }

    private UserEntity toUserEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());

        return entity;
    }
}