package in.co.technoflask.projects.desiwanderer.security.exception;

import in.co.technoflask.projects.desiwanderer.exception.web.UnauthorizedException;

public class InvalidAuthenticationSubjectException extends UnauthorizedException {

  public InvalidAuthenticationSubjectException() {
    super(
        "Invalid Authentication Subject",
        "The current authentication subject is not of the expected type");
  }
}
