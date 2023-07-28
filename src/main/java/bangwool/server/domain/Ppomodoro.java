package bangwool.server.domain;

import bangwool.server.dto.request.PpomodoroRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ppomodoro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    private String name;

    private String color;

    private int workHour;

    private int workMin;

    private int restTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Ppomodoro(String name, String color, int workHour, int workMin, int restTime, Member member) {
        this.name = name;
        this.color = color;
        this.workHour = workHour;
        this.workMin = workMin;
        this.restTime = restTime;
        this.member = member;
    }

    public void update(PpomodoroRequest ppomodoroRequest) {
        this.name = ppomodoroRequest.getName();
        this.color = ppomodoroRequest.getColor();
        this.workHour = ppomodoroRequest.getWorkHour();
        this.workMin = ppomodoroRequest.getWorkMin();
        this.restTime = ppomodoroRequest.getRestTime();
    }
}
