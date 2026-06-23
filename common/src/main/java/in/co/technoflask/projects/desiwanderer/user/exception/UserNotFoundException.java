package in.co.technoflask.projects.desiwanderer.user.exception;

import in.co.technoflask.projects.desiwanderer.exception.web.HttpServiceException;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends HttpServiceException {

  public UserNotFoundException(UUID id) {
    super(HttpStatus.NOT_FOUND, "User Not Found", "User with id %s not found".formatted(id));
  }
}
