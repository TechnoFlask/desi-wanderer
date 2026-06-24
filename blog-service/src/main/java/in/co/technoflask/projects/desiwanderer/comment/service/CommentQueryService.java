package in.co.technoflask.projects.desiwanderer.comment.service;

import in.co.technoflask.projects.desiwanderer.comment.entity.Comment;
import in.co.technoflask.projects.desiwanderer.comment.exception.CommentNotFoundException;
import in.co.technoflask.projects.desiwanderer.comment.repository.CommentRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentQueryService {

  private final CommentRepository commentRepository;

  public Comment findById(UUID id) {
    return this.commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
  }
}
