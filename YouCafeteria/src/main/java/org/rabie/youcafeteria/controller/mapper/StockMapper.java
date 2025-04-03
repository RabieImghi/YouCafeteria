package org.rabie.youcafeteria.controller.mapper;

import org.mapstruct.Mapper;
import org.rabie.youcafeteria.domain.Stock;
import org.rabie.youcafeteria.dto.stock.CreateAndUpdateStockDTO;
import org.rabie.youcafeteria.vm.stock.StockResponseVM;

@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock toStockFromStockDTO(CreateAndUpdateStockDTO createAndUpdateStockDTO);
    StockResponseVM toStockResponseVM(Stock stock);
}
