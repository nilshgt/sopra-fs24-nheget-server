package ch.uzh.ifi.hase.soprafs24.rest.dto;

import java.time.LocalDate;

import ch.uzh.ifi.hase.soprafs24.constant.UserStatus;

public class UserGetDTO {

  private Long id;
  private String username;
  private String password;
  private UserStatus status;
  private LocalDate birthday;
  private LocalDate creationDate;

  public LocalDate getBirthday() {
      return birthday;
  }

  public void setBirthday(LocalDate birthday) {
      this.birthday = birthday;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
      this.creationDate = creationDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }
}
