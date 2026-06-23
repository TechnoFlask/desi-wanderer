package in.co.technoflask.projects.desiwanderer.security.exception;

import in.co.technoflask.projects.desiwanderer.exception.web.UnauthorizedException;

public class MissingAuthenticationException extends UnauthorizedException {

  public MissingAuthenticationException() {
    super("Authentication Missing", "Failed to get user authentication details");
  }
}
