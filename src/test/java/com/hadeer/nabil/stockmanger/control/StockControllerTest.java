//package com.hadeer.nabil.stockmanger.control;
//
//import com.hadeer.nabil.stockmanger.dto.StockQueryDTO;
//import com.hadeer.nabil.stockmanger.model.Stock;
//import com.hadeer.nabil.stockmanger.service.StockService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static javax.swing.UIManager.get;
//import static javax.xml.transform.OutputKeys.VERSION;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(StockController.class)
//public class StockControllerTest{
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private StockController stockController;
//
//
//    @Test
//    public void findAll_Stocks_By_ProductId_ShouldReturnFoundStockEntries() throws Exception {
//        StockQueryDTO query = StockQueryDTO.builder().productId("Milk_123").build();
//
//    }
//
//
//    //ToDO rest of the tests
//}