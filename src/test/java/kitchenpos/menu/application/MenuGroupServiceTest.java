package kitchenpos.menu.application;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.dto.MenuGroupRequest;

@SpringBootTest
@Transactional
class MenuGroupServiceTest {

    @Autowired
    private MenuGroupService menuGroupService;

    @Test
    @DisplayName("MenuGroup을 생성한다.")
    void create() {
        //given
        MenuGroupRequest menuGroupRequest = new MenuGroupRequest("test");

        //when
        MenuGroup saved = menuGroupService.create(menuGroupRequest);

        //then
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    @DisplayName("리스트 형태로 조회한다.")
    void findAsList() {
        //when
        List<MenuGroup> menus = menuGroupService.list();

        //when
        MenuGroupRequest menuGroupRequest = new MenuGroupRequest("test");
        menuGroupService.create(menuGroupRequest);

        //then
        assertThat(menuGroupService.list()).hasSize(menus.size() + 1);
    }
}
