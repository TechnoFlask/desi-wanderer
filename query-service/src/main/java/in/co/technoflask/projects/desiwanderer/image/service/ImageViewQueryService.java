package in.co.technoflask.projects.desiwanderer.image.service;

import in.co.technoflask.projects.desiwanderer.image.entity.ImageView;
import in.co.technoflask.projects.desiwanderer.image.exception.ImageNotFoundException;
import in.co.technoflask.projects.desiwanderer.image.repository.ImageViewRepository;
import in.co.technoflask.projects.desiwanderer.postgres.session.annotation.InitializePostgresSession;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageViewQueryService {

  private final ImageViewRepository imageViewRepository;

  @Transactional
  @InitializePostgresSession
  public Page<ImageView> findAll(Specification<ImageView> specification, Pageable pageable) {
    return this.imageViewRepository.findAll(specification, pageable);
  }

  @Transactional
  @InitializePostgresSession
  public ImageView findById(UUID id) {
    return this.imageViewRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
  }
}
