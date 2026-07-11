package in.co.technoflask.projects.desiwanderer.post.repository;

import in.co.technoflask.projects.desiwanderer.post.entity.PostReportView;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportViewRepository
    extends JpaRepository<PostReportView, UUID>, JpaSpecificationExecutor<PostReportView> {}
