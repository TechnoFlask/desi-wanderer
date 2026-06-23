package in.co.technoflask.projects.desiwanderer.security.exception;

import in.co.technoflask.projects.desiwanderer.exception.web.UnauthorizedException;

public class InvalidAuthenticationTypeException extends UnauthorizedException {

  public InvalidAuthenticationTypeException() {
    super("Invalid Authentication", "The current authentication is not of the expected type");
  }
}
