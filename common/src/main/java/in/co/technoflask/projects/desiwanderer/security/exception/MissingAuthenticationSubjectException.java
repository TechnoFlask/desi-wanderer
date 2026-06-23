package in.co.technoflask.projects.desiwanderer.security.exception;

import in.co.technoflask.projects.desiwanderer.exception.web.UnauthorizedException;

public class MissingAuthenticationSubjectException extends UnauthorizedException {

  public MissingAuthenticationSubjectException() {
    super("Missing Authentication Subject", "The authentication subject is missing");
  }
}
