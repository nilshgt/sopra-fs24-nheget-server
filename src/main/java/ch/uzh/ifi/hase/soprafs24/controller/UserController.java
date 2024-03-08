package ch.uzh.ifi.hase.soprafs24.controller;

import ch.uzh.ifi.hase.soprafs24.entity.User;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserGetDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserPostDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserUpdateDTO;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import ch.uzh.ifi.hase.soprafs24.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User Controller
 * This class is responsible for handling all REST request that are related to
 * the user.
 * The controller will receive the request and delegate the execution to the
 * UserService and finally return the result.
 */
@RestController
public class UserController {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<UserGetDTO> getAllUsers() {
    // fetch all users in the internal representation
    List<User> users = userService.getUsers();
    List<UserGetDTO> userGetDTOs = new ArrayList<>();

    // convert each user to the API representation
    for (User user : users) {
      userGetDTOs.add(DTOMapper.INSTANCE.convertEntityToUserGetDTO(user));
    }
    return userGetDTOs;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ResponseEntity<?> loginUser(@RequestBody UserPostDTO userPostDTO) {
      User user = userService.loginUser(userPostDTO.getUsername(), userPostDTO.getPassword());
      if (user != null) {
          // Return actual user ID instead of a placeholder
          return ResponseEntity.ok().body(user.getId());
      } else {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
      }
  }

  @PostMapping("/user")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public UserGetDTO createUser(@RequestBody UserPostDTO userPostDTO) {
    // convert API user to internal representation
    User userInput = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

    // create user
    User createdUser = userService.createUser(userInput);
    // convert internal representation of user back to API
    return DTOMapper.INSTANCE.convertEntityToUserGetDTO(createdUser);
  }

  @GetMapping("/user/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public UserGetDTO getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    return DTOMapper.INSTANCE.convertEntityToUserGetDTO(user);
  }

  @PutMapping("/user/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserGetDTO updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
      User updatedUser = userService.updateUser(id, userUpdateDTO);
      
      
      return DTOMapper.INSTANCE.convertEntityToUserGetDTO(updatedUser);
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(@RequestBody Long id) {
    userService.logoutUser(id);
    return ResponseEntity.ok().build();
  }

}



