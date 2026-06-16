package org.scoula;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class RestaurantTest {
    @Autowired
    private Restaurant restaurant;

    @Test
    void getChef() {
        // 위쪽에서 @Autowired 해주지 않으면 restaurant에 아무 것도 담기지 않아서 Null이 된다.
        assertNotNull(restaurant);  // Null 여부 판단 메서드(Not Null이면 출력 x) - test 전용 메서드
        log.info(restaurant);
        log.info("------------------------------"); log.info(restaurant.getChef());
    } }