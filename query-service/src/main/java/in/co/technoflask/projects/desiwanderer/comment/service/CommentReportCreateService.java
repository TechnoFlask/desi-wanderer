package in.co.technoflask.projects.desiwanderer.comment.service;

import in.co.technoflask.projects.desiwanderer.comment.dto.CommentReportEventValue;
import in.co.technoflask.projects.desiwanderer.comment.entity.CommentReport;
import in.co.technoflask.projects.desiwanderer.comment.repository.CommentReportRepository;
import in.co.technoflask.projects.desiwanderer.postgres.session.annotation.InitializePostgresSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentReportCreateService {

  private final CommentReportRepository commentReportRepository;
  private final CommentQueryService commentQueryService;

  @Transactional
  @InitializePostgresSession(additionalAuthorities = "SCOPE_comment_reports:cdc")
  public void create(CommentReportEventValue event) {
    CommentReport commentReport =
        this.commentReportRepository.save(
            CommentReport.builder()
                .id(event.id())
                .isApproved(event.isApproved())
                .disapprovalReason(event.disapprovalReason())
                .lastProcessedLsn(event.lsn())
                .comment(this.commentQueryService.findById(event.commentId()))
                .createdAt(event.createdAt())
                .updatedAt(event.updatedAt())
                .build());
    log.info("Comment Report created: report={}", commentReport);
  }
}
