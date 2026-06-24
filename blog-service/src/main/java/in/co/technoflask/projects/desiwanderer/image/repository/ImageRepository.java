package in.co.technoflask.projects.desiwanderer.image.repository;

import in.co.technoflask.projects.desiwanderer.image.entity.Image;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository
    extends JpaRepository<Image, UUID> {}
