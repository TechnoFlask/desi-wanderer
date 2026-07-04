package in.co.technoflask.projects.desiwanderer.comment.service;

import in.co.technoflask.projects.desiwanderer.comment.entity.CommentReport;
import in.co.technoflask.projects.desiwanderer.comment.repository.CommentReportRepository;
import in.co.technoflask.projects.desiwanderer.postgres.session.annotation.InitializePostgresSession;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentReportDeleteService {

  private final CommentReportRepository commentReportRepository;
  private final CommentReportQueryService commentReportQueryService;

  @Transactional
  @InitializePostgresSession(additionalAuthorities = "SCOPE_comment_reports:cdc")
  public void deleteById(UUID id) {
    CommentReport commentReport = this.commentReportQueryService.findById(id);
    this.commentReportRepository.delete(commentReport);
    log.info("Comment Report deleted: report={}", commentReport);
  }

  @Transactional
  @InitializePostgresSession(additionalAuthorities = "SCOPE_comment_reports:cdc")
  public void deleteAll() {
    this.commentReportRepository.deleteAllInBatch();
    log.info("Comment Reports truncated");
  }
}
