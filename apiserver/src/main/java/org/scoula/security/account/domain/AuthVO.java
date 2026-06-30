package org.scoula.security.account.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

//role에 따른 권한을 저장할 수 있는 vo생성함.
//GrantedAuthority 인터페이스를 반드시 구현해야함.
@Data // get/set메서드
public class AuthVO implements GrantedAuthority {
    private String username;
    private String auth; //role

    @Override
    public String getAuthority() {
        return auth;
    }
}
