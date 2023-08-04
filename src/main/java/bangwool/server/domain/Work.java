package bangwool.server.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

import static bangwool.server.domain.Member.STATUS_ACTIVE;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ppomodoro_id")
    private Ppomodoro ppomodoro;
    private int workedHour;
    private int workedMin;
    @CreationTimestamp
    @Column(name = "create_date")
    @Builder.Default
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());

    @UpdateTimestamp
    @Column(name = "update_date")
    @Builder.Default
    private Timestamp updateDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "status")
    @Builder.Default
    private String status = STATUS_ACTIVE;
}
