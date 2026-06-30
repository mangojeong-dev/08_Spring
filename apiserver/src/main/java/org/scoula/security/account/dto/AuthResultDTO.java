//○   로그인 성공 결과를 나타내는 응답
//○   인증 token과 UserInfoDTO로 구성
package org.scoula.security.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResultDTO {
    String token;
    UserInfoDTO user;
}