package in.co.technoflask.projects.desiwanderer.image.service;

import in.co.technoflask.projects.desiwanderer.image.entity.Image;
import in.co.technoflask.projects.desiwanderer.image.exception.ImageNotFoundException;
import in.co.technoflask.projects.desiwanderer.image.repository.ImageRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageQueryService {

  private final ImageRepository imageRepository;
  private final ImageS3Service imageS3Service;

  public Image findById(UUID id) {
    return this.imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
  }
}
