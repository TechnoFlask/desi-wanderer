package in.co.technoflask.projects.desiwanderer.image.service;

import in.co.technoflask.projects.desiwanderer.image.dto.ImageEventValue;
import in.co.technoflask.projects.desiwanderer.image.entity.Image;
import in.co.technoflask.projects.desiwanderer.image.repository.ImageRepository;
import in.co.technoflask.projects.desiwanderer.postgres.session.annotation.InitializePostgresSession;
import in.co.technoflask.projects.desiwanderer.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageCreateService {

  private final ImageRepository imageRepository;
  private final UserQueryService userQueryService;

  @Transactional
  @InitializePostgresSession(additionalAuthorities = "SCOPE_images:cdc")
  public void create(ImageEventValue event) {
    Image image =
        this.imageRepository.save(
            Image.builder()
                .id(event.id())
                .fileName(event.fileName())
                .key(event.key())
                .mimeType(event.mimeType())
                .title(event.title())
                .altText(event.altText())
                .width(event.width())
                .height(event.height())
                .lastProcessedLsn(event.lsn())
                .owner(this.userQueryService.findById(event.ownerId()))
                .createdAt(event.createdAt())
                .updatedAt(event.updatedAt())
                .build());
    log.info("Image created: image={}", image);
  }
}
