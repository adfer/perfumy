package com.adfer.services.impl;

import com.adfer.domain.Customer;
import com.adfer.domain.OrderHeader;
import com.adfer.domain.Perfume;
import com.adfer.domain.ShoppingCart;
import com.adfer.enums.PerfumeCategory;
import com.adfer.repository.OrderHeaderRepository;
import com.adfer.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.StringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Created by adrianferenc on 07.10.2016.
 */
@SpringBootTest
@ActiveProfiles("test")
public class OrderHeaderServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @AfterMethod
    public void tearDown() throws Exception {
        orderHeaderRepository.deleteAll();
    }

//    @Test
//    public void shouldCreateOneOrder() {
//        Perfume perfume1 = new Perfume();
//        perfume1.setId("100");
//        perfume1.setBrand("Giorgio Armani");
//        perfume1.setName("Acqua di Gioia");
//        perfume1.setCapacity("50 ml");
//        perfume1.setCategory(PerfumeCategory.WOMEN.toString());
//        perfume1.setPrice("200.20 zł");
//        perfume1.setPicture("http://f8ni.com/assets/images/acqua-di%20gioia-50ml-1.jpg");
//
//        Perfume perfume2 = new Perfume();
//        perfume2.setId("200");
//        perfume2.setBrand("Chanel");
//        perfume2.setName("Coco Mademoiselle");
//        perfume2.setCapacity("150 ml");
//        perfume2.setCategory(PerfumeCategory.WOMEN.toString());
//        perfume2.setPrice("120.00 zł");
//
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.getPerfumes().put(perfume1.getId(), 1);
//        shoppingCart.getPerfumes().put(perfume2.getId(), 2);
//
//        Customer customer = new Customer();
//        customer.setFirstName("Adrian");
//        customer.setLastName("Ferenc");
//
//        OrderHeader orderHeader = OrderHeader.builder()
//                .customer(customer)
//                .shoppingCart(shoppingCart)
//                .build();
//
//
//        orderService.makeOrder(customer);
//
//        long orderCount = orderHeaderRepository.count();
//        assertEquals(orderCount, 1);
//    }
//
//    @Test
//    public void shouldGetOneOrder() {
//        Perfume perfume1 = new Perfume();
//        perfume1.setId("100");
//        perfume1.setBrand("Giorgio Armani");
//        perfume1.setName("Acqua di Gioia");
//        perfume1.setCapacity("50 ml");
//        perfume1.setCategory(PerfumeCategory.WOMEN.toString());
//        perfume1.setPrice("200.20 zł");
//        perfume1.setPicture("http://f8ni.com/assets/images/acqua-di%20gioia-50ml-1.jpg");
//
//        Perfume perfume2 = new Perfume();
//        perfume2.setId("200");
//        perfume2.setBrand("Chanel");
//        perfume2.setName("Coco Mademoiselle");
//        perfume2.setCapacity("150 ml");
//        perfume2.setCategory(PerfumeCategory.WOMEN.toString());
//        perfume2.setPrice("120.00 zł");
//
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.getPerfumes().put(perfume1.getId(), 1);
//        shoppingCart.getPerfumes().put(perfume2.getId(), 2);
//
//        Customer customer = new Customer();
//        customer.setFirstName("Adrian");
//        customer.setLastName("Ferenc");
//
//        OrderHeader orderHeader = OrderHeader.builder()
//                .customer(customer)
//                .shoppingCart(shoppingCart)
//                .build();
//
//        OrderHeader persOrderHeader = orderService.makeOrder(orderHeader);
//
//        assertFalse(StringUtils.isEmpty(persOrderHeader.getId()));
//        assertEquals(persOrderHeader.getCustomer().getFirstName(), "Adrian");
//        assertEquals(persOrderHeader.getCustomer().getLastName(), "Ferenc");
//        assertEquals(persOrderHeader.getShoppingCart().getPerfumes().size(), 2);
//        assertEquals(persOrderHeader.getShoppingCart().getPerfumes().get("100"), new Integer(1));
//        assertEquals(persOrderHeader.getShoppingCart().getPerfumes().get("200"), new Integer(2));
//
//    }

}
