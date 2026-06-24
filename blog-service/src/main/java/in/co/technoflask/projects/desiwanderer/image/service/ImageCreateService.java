package in.co.technoflask.projects.desiwanderer.image.service;

import in.co.technoflask.projects.desiwanderer.image.dto.ImageMetadata;
import in.co.technoflask.projects.desiwanderer.image.dto.ImageUploadPayload;
import in.co.technoflask.projects.desiwanderer.image.entity.Image;
import in.co.technoflask.projects.desiwanderer.image.repository.ImageRepository;
import in.co.technoflask.projects.desiwanderer.postgres.session.annotation.InitializePostgresSession;
import in.co.technoflask.projects.desiwanderer.user.service.UserQueryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageCreateService {

  private final ImageRepository imageRepository;
  private final ImageFileService imageFileService;
  private final ImageS3Service imageS3Service;
  private final UserQueryService userQueryService;

  @Transactional
  @InitializePostgresSession
  public Image create(ImageUploadPayload payload) {
    ImageMetadata metadata = this.imageFileService.extractMetadata(payload.file());
    UUID key = UUID.randomUUID();

    this.imageS3Service.upload(payload.file(), key, metadata.mimeType());

    Image image =
        this.imageRepository.save(
            Image.builder()
                .fileName(payload.file().getOriginalFilename())
                .key(key)
                .mimeType(metadata.mimeType())
                .title(payload.title())
                .altText(payload.altText())
                .width(metadata.width())
                .height(metadata.height())
                .owner(this.userQueryService.findByAuthentication())
                .build());

    log.info("Image created: image={}", image);
    return image;
  }
}
