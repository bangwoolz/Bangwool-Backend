package bangwool.server.service;

import bangwool.server.domain.Member;
import bangwool.server.domain.Ppomodoro;
import bangwool.server.dto.request.PpomodoroRequest;
import bangwool.server.dto.response.PpomodoroFindResponse;
import bangwool.server.dto.response.PpomodoroResponse;
import bangwool.server.dto.response.PpomodorosResponse;
import bangwool.server.exception.notfound.NotFoundMemberException;
import bangwool.server.exception.notfound.NotFoundPpomodororException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.repository.PpomodoroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PpomodoroService {

    private final PpomodoroRepository ppomodoroRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PpomodoroResponse save(Long memberId, PpomodoroRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
        Ppomodoro ppomodoro = ppomodoroRepository.save(new Ppomodoro(request.getName(), request.getColor(), request.getWorkHour(), request.getWorkMin(), request.getRestTime(), member));
        return new PpomodoroResponse(ppomodoro.getId());
    }

    @Transactional
    public PpomodoroResponse update(Long ppomodoroId, PpomodoroRequest ppomodoroRequest) {
        Ppomodoro ppomodoro = ppomodoroRepository.findById(ppomodoroId).orElseThrow(NotFoundPpomodororException::new);

        ppomodoro.update(ppomodoroRequest);
        return new PpomodoroResponse(ppomodoro.getId());
    }

    @Transactional
    public void delete(Long ppomodoroId) {
        Ppomodoro ppomodoro = ppomodoroRepository.findById(ppomodoroId).orElseThrow(NotFoundPpomodororException::new);

        ppomodoroRepository.delete(ppomodoro);
    }

    public PpomodorosResponse findAll(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);

        return new PpomodorosResponse(ppomodoroRepository.findAllByMember(member).stream().map(PpomodoroFindResponse::of).toList());
    }
}
