package bangwool.server.service;

import bangwool.server.domain.Ppomodoro;
import bangwool.server.dto.request.PpomodoroRequest;
import bangwool.server.dto.response.PpomodoroResponse;
import bangwool.server.repository.PpomodoroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PpomodoroService {

    private final PpomodoroRepository ppomodoroRepository;

    @Transactional
    public PpomodoroResponse save(PpomodoroRequest request) {
        int hour = request.getTime().getHour();
        int minute = request.getTime().getMinute();

        Ppomodoro ppomodoro = ppomodoroRepository.save(new Ppomodoro(request.getColor(),request.getName() ,request.getTime(), request.getRestTime()));
        return new PpomodoroResponse(ppomodoro.getId());
    }
}
