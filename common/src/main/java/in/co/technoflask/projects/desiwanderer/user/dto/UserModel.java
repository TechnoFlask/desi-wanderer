package in.co.technoflask.projects.desiwanderer.user.dto;

import in.co.technoflask.projects.desiwanderer.user.domain.UserDomain;
import java.util.UUID;

public record UserModel(UUID id, String email, String firstName, String lastName, String username) {

  public static UserModel from(UserDomain user) {
    return new UserModel(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getUsername());
  }
}
