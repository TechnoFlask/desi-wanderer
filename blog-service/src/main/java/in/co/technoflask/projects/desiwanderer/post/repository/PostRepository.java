package in.co.technoflask.projects.desiwanderer.post.repository;

import in.co.technoflask.projects.desiwanderer.post.entity.Post;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

  boolean existsBySlug(String slug);

  boolean existsByIdNotAndSlug(UUID id, String slug);
}
