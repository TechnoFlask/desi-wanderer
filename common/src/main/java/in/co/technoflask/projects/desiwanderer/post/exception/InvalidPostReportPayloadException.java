package in.co.technoflask.projects.desiwanderer.post.exception;

import in.co.technoflask.projects.desiwanderer.exception.web.HttpServiceException;
import org.springframework.http.HttpStatus;

public class InvalidPostReportPayloadException extends HttpServiceException {

  public InvalidPostReportPayloadException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Post Report", "Post report format is invalid");
  }
}
