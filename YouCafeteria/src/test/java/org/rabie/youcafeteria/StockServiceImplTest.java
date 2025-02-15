package org.rabie.youcafeteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rabie.youcafeteria.domain.Stock;
import org.rabie.youcafeteria.exception.exceptions.StockException;
import org.rabie.youcafeteria.repository.StockRepository;
import org.rabie.youcafeteria.service.impl.StockServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setId(1L);
        stock.setName("Tomato Sauce");
        stock.setQuantity(50);
        stock.setCreationDate(LocalDateTime.now().plusDays(1)); // Date future valide
    }

    // 1. Test de sauvegarde d'un stock
    @Test
    void saveStock_Success() {
        when(stockRepository.findByName(stock.getName())).thenReturn(Optional.empty());
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        Stock result = stockService.saveStock(stock);

        assertNotNull(result);
        assertEquals("Tomato Sauce", result.getName());
        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void saveStock_Fail_StockAlreadyExists() {
        when(stockRepository.findByName(stock.getName())).thenReturn(Optional.of(stock));

        StockException exception = assertThrows(
                StockException.class,
                () -> stockService.saveStock(stock)
        );

        assertEquals("Error : Stock already exists", exception.getMessage());
    }

    @Test
    void saveStock_Fail_NullStock() {
        StockException exception = assertThrows(
                StockException.class,
                () -> stockService.saveStock(null)
        );

        assertEquals("Error : Stock is null", exception.getMessage());
    }


    @Test
    void updateStock_Fail_StockNotFound() {
        when(stockRepository.findByName(stock.getName())).thenReturn(Optional.empty());

        StockException exception = assertThrows(
                StockException.class,
                () -> stockService.updateStock(stock)
        );

        assertEquals("Error : Stock does not exist", exception.getMessage());
    }

    @Test
    void updateStock_Fail_StockQuantityNegative() {
        stock.setQuantity(-5);

        StockException exception = assertThrows(
                StockException.class,
                () -> stockService.updateStock(stock)
        );

        assertEquals("Error : Stock quantity is negative", exception.getMessage());
    }

    // 3. Test de suppression du stock
    @Test
    void deleteStock_Success() {
        when(stockRepository.findByName("Tomato Sauce")).thenReturn(Optional.of(stock));

        assertDoesNotThrow(() -> stockService.deleteStock("Tomato Sauce"));
        verify(stockRepository, times(1)).delete(stock);
    }

    @Test
    void deleteStock_Fail_StockNotFound() {
        when(stockRepository.findByName("Tomato Sauce")).thenReturn(Optional.empty());

        StockException exception = assertThrows(
                StockException.class,
                () -> stockService.deleteStock("Tomato Sauce")
        );

        assertEquals("Error : Stock does not exist", exception.getMessage());
    }

    @Test
    void deleteStock_Fail_NullStockName() {
        StockException exception = assertThrows(
                StockException.class,
                () -> stockService.deleteStock(null)
        );

        assertEquals("Error : Stock does not exist", exception.getMessage());
    }

    // 4. Test de récupération du stock par nom
    @Test
    void getStock_Success() {
        when(stockRepository.findByName("Tomato Sauce")).thenReturn(Optional.of(stock));

        Stock result = stockService.getStock("Tomato Sauce");

        assertNotNull(result);
        assertEquals("Tomato Sauce", result.getName());
    }

    @Test
    void getStock_Fail_StockNotFound() {
        when(stockRepository.findByName("Tomato Sauce")).thenReturn(Optional.empty());

        StockException exception = assertThrows(
                StockException.class,
                () -> stockService.getStock("Tomato Sauce")
        );

        assertEquals("Error : Stock does not exist", exception.getMessage());
    }

    @Test
    void getStock_Fail_NullStockName() {
        StockException exception = assertThrows(
                StockException.class,
                () -> stockService.getStock(null)
        );

        assertEquals("Error : Stock does not exist", exception.getMessage());
    }

    // 5. Test de récupération de tous les stocks avec pagination
    @Test
    void getAllStocks_Success() {
        Page<Stock> stocks = new PageImpl<>(Collections.singletonList(stock));
        when(stockRepository.findAll(any(PageRequest.class))).thenReturn(stocks);

        Page<Stock> result = stockService.getAllStocks(0, 10);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
    }



    @Test
    void getAllStocks_Fail_EmptyStockList() {
        Page<Stock> emptyPage = Page.empty();
        when(stockRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);

        Page<Stock> result = stockService.getAllStocks(0, 10);

        assertTrue(result.isEmpty());
    }
}
