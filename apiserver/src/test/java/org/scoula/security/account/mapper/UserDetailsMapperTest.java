package org.scoula.security.account.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        RootConfig.class, SecurityConfig.class })
@Log4j2
class UserDetailsMapperTest {

    @Autowired
    private UserDetailsMapper mapper;

    @Test
    void get() {
        MemberVO member = mapper.get("admin");
        System.out.println(member);

        List<AuthVO> list = member.getAuthList();
        for(AuthVO auth : list){
            System.out.println(auth);
        }
    }
}