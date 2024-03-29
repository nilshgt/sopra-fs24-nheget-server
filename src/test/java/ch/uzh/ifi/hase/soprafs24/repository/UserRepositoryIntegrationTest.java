package ch.uzh.ifi.hase.soprafs24.repository;

import ch.uzh.ifi.hase.soprafs24.constant.UserStatus;
import ch.uzh.ifi.hase.soprafs24.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void findByName_success() {
    // given
    User user = new User();
    user.setPassword("password");
    user.setUsername("firstname@lastname");
    user.setStatus(UserStatus.OFFLINE);
    user.setId(1L);

    entityManager.persist(user);
    entityManager.flush();

    // when
    User found = userRepository.findByPassword(user.getPassword());

    // then
    assertNotNull(found.getId());
    assertEquals(found.getPassword(), user.getPassword());
    assertEquals(found.getUsername(), user.getUsername());
    assertEquals(found.getId(), user.getId());
    assertEquals(found.getStatus(), user.getStatus());
  }
}
