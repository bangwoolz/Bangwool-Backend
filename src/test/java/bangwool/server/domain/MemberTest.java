package bangwool.server.domain;

import bangwool.server.exception.RegexException;
import jakarta.validation.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import static bangwool.server.domain.MemberTest.Type.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest
@DisplayName("멤버 테스트")
class MemberTest {

    enum Type{
        EMAIL, PASSWORD, NICKNAME
    }

    @Autowired
    private Validator validator;

    @Test
    @DisplayName("이메일 정규 표현식이 적용이 되는지?")
    void valid_correct_email() {
        // given
        String[] corrects = {"ttt@gmail.com", "ttt123@gmail.co.kr", "123@gmail.com"};
        String[] incorrects = {"@gmail.com", "ttt", "ttt@gmailcom"};

        //when
        validRegex(corrects, RegexException.EMPTY_EXCEPTION, EMAIL);
        validRegex(incorrects, RegexException.EMAIL_EXCEPTION, EMAIL);

        //then

    }

    @Test
    @DisplayName("닉네임 글자 제한이 적용 되는지?")
    void valid_correct_nickname() {
        //given
        String[] corrects = {"아", "야야야야야", "야호", "test", "t", "2", "2222"};
        String[] incorrects = {"", "ㅇㅇ", "21글자채우기용으로뭐라도적어보는중입니다아", "rmfwkcodnrldyddlqslek"};

        //when
        validRegex(corrects , RegexException.EMPTY_EXCEPTION, NICKNAME);
        validRegex(incorrects , RegexException.NIKCNAME_EXCEPTION, NICKNAME);

        //then

    }

    @Test
    @DisplayName("비밀번호가 암호화가 되어있는지?")
    void encoding_password() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("비밀번호가 정규 표현식이 적용 되는지?")
    void valid_correct_password() {
        //given
        String[] corrects = {"Password!", "password!", "!!!PP123", "!!!pp123"};
        String[] incorrects = {"PPPPPPP", "!!!!!!!!", "password1", "!!!p123",
                "p", "PPPassword", "PPPaaassssswoorld!!"};

        //when
        validRegex(corrects , RegexException.EMPTY_EXCEPTION, PASSWORD);
        validRegex(incorrects, RegexException.PASSWORD_EXCEPTION, PASSWORD);

        //then


    }

    private void validRegex(String[] tests, final String expected, Type type) {

        //given
        List<String> messages = new ArrayList<>();
        Member member;

        //when
        for(String test : tests) {
            switch (type) {
                case EMAIL -> member = Member.builder().email(test).build();
                case NICKNAME -> member = Member.builder().nickname(test).build();
                case PASSWORD -> member = Member.builder().password(test).build();
                default -> member = Member.builder().build();
            }

            Set<ConstraintViolation<Member>> validate = validator.validate(member);

            Iterator<ConstraintViolation<Member>> iterator = validate.iterator();

            iterator.forEachRemaining(iter -> messages.add(iter.getMessage()));
        }

        //then
        if(expected.equals(RegexException.EMPTY_EXCEPTION)) {
            assertThat(messages).isEmpty();
        } else {
            assertEquals(tests.length, messages.size());
            for(String message : messages) {
                assertEquals(message, expected);
            }
        }
    }

}