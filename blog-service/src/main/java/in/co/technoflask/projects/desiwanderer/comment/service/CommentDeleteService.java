package in.co.technoflask.projects.desiwanderer.comment.service;

import in.co.technoflask.projects.desiwanderer.comment.entity.Comment;
import in.co.technoflask.projects.desiwanderer.comment.repository.CommentRepository;
import in.co.technoflask.projects.desiwanderer.postgres.session.annotation.InitializePostgresSession;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentDeleteService {

  private final CommentRepository commentRepository;
  private final CommentQueryService commentQueryService;

  @Transactional
  @InitializePostgresSession
  public Comment deleteById(UUID id) {
    Comment comment = this.commentQueryService.findById(id);
    this.commentRepository.delete(comment);
    log.info("Comment deleted: comment={}", comment);
    return comment;
  }
}
