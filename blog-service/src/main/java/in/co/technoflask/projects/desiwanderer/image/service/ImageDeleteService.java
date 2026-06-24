package in.co.technoflask.projects.desiwanderer.image.service;

import in.co.technoflask.projects.desiwanderer.image.entity.Image;
import in.co.technoflask.projects.desiwanderer.image.repository.ImageRepository;
import in.co.technoflask.projects.desiwanderer.postgres.session.annotation.InitializePostgresSession;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageDeleteService {

  private final ImageRepository imageRepository;
  private final ImageQueryService imageQueryService;
  private final ImageS3Service imageS3Service;

  @Transactional
  @InitializePostgresSession
  public Image deleteById(UUID id) {
    Image image = this.imageQueryService.findById(id);
    this.imageS3Service.deleteFromS3(image);
    this.imageRepository.delete(image);
    log.info("Image deleted: image={}", image);
    return image;
  }
}
