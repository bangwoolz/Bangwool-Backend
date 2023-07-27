package bangwool.server.domain;

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

    private LocalTime workTime;

    private int restTime;

    public Ppomodoro(String name, String color, LocalTime workTime, int restTime) {
        this.name = name;
        this.color = color;
        this.workTime = workTime;
        this.restTime = restTime;
    }
}
