package in.co.technoflask.projects.desiwanderer.comment.repository;

import in.co.technoflask.projects.desiwanderer.comment.entity.CommentReportView;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReportViewRepository
    extends JpaRepository<CommentReportView, UUID>, JpaSpecificationExecutor<CommentReportView> {}
