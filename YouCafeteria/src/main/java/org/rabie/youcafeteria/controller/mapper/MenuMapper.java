package org.rabie.youcafeteria.controller.mapper;

import org.mapstruct.Mapper;
import org.rabie.youcafeteria.domain.Menu;
import org.rabie.youcafeteria.dto.menu.CreateAndUpdateMenu;
import org.rabie.youcafeteria.vm.menu.MenuResponse;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuResponse fromMenuToResponse(Menu menu);
    Menu toMenuFromCreateAndUpdateDto(CreateAndUpdateMenu createAndUpdateMenu);


}
