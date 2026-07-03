package in.co.technoflask.projects.desiwanderer.post.service;

import in.co.technoflask.projects.desiwanderer.post.entity.Post;
import in.co.technoflask.projects.desiwanderer.post.exception.PostNotFoundException;
import in.co.technoflask.projects.desiwanderer.post.repository.PostRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostQueryService {

  private final PostRepository postRepository;

  public Post findById(UUID id) {
    return this.postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
  }
}
