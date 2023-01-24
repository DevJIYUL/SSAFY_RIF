package team.a501.rif.domain.tmp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team.a501.rif.repository.tmp.TempMemberRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class TempMemberTest {

    @Autowired
    private TempMemberRepository tempMemberRepository;

    @DisplayName("임시 멤버 추가하기")
    @Test
    void createTempMember(){

        String uid = UUID.randomUUID().toString();
        String id = "0847836";
        String password = id;
        String name = "강승곤";
        Integer point = 0;
        Integer exp = 0;
        String profileImgPath = "/profile/default.png";

        TempMember tempMember = TempMember.builder()
                .uid(uid)
                .id(id)
                .password(password)
                .point(point)
                .exp(exp)
                .profileImgPath(profileImgPath)
                .build();

        tempMemberRepository.save(tempMember);
    }
}