package bangwool.server.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @CreatedDate
    @Column(name = "create_date")
    private String createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private String updateDate;

}
