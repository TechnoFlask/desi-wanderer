package in.co.technoflask.projects.desiwanderer.comment.domain;

import in.co.technoflask.projects.desiwanderer.post.domain.PostDomain;
import in.co.technoflask.projects.desiwanderer.user.domain.UserDomain;
import java.time.Instant;
import java.util.UUID;

public interface CommentDomain {

  UUID getId();

  default String getContent() {
    return null;
  }

  default Boolean getIsApproved() {
    return null;
  }

  default UserDomain getAuthor() {
    return null;
  }

  default PostDomain getPost() {
    return null;
  }

  default CommentReportDomain getReport() {
    return null;
  }

  default Instant getCreatedAt() {
    return null;
  }

  default Instant getUpdatedAt() {
    return null;
  }
}
