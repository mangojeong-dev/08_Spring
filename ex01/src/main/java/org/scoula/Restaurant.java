package org.scoula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.Setter;

@Component      // Chef 새로운 객체 만들지 않아도 자동으로 생성해주는 어느테이션
@Data
public class Restaurant {
    @Autowired  // spring이 자동으로 ram의 어딘가에서 찾아서 주소를 아래 변수에 넣어준다 <- @Component 하위
    private Chef chef;  // Chef가 ram 어디에 있는지 주소만 알면 찾을 수 있다
}
