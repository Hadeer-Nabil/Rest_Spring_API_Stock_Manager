//package com.hadeer.nabil.stockmanger.dao;
//
//import com.hadeer.nabil.stockmanger.model.Stock;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class StockRepositoryTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private StockRepository stockRepository;
//
//
//    @Test
//    public void whenFindAllById() {
//        //given
//        Stock stock = new Stock();
//        stock.setProductId("test_123");
//        entityManager.persist(stock);
//        entityManager.flush();
//
//        //when
//        List<Stock> testStock = stockRepository.findByProductIdOrderByTimestampDesc(stock.getProductId());
//
//        //then
//        assertTrue(testStock.get(0).getProductId().toString().equalsIgnoreCase(stock.getProductId()));
//    }
//
//}
//
