package TripAmi.backend.app.report.domain;

import TripAmi.backend.app.util.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report")
@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    private String content;

    @Enumerated(EnumType.STRING)
    private ReportType type;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Embedded
    private BaseEntity baseEntity = new BaseEntity();
    @Builder
    public Report(Long authorId, String content, ReportType type, ReportStatus status) {
        this.authorId = authorId;
        this.content = content;
        this.type = type;
        this.status = status;
    }

    public void updateStatus(ReportStatus status) {
        this.status = status;
    }

    public void delete() {
        this.baseEntity.delete();
    }
}
