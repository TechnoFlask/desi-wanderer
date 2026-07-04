package in.co.technoflask.projects.desiwanderer.post.repository;

import in.co.technoflask.projects.desiwanderer.post.entity.PostReport;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportRepository extends JpaRepository<PostReport, UUID> {}
