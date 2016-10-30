package com.adfer.services.impl;

import com.adfer.domain.Perfume;
import com.adfer.domain.ShoppingCart;
import com.adfer.enums.PerfumeCategory;
import com.adfer.services.PerfumeService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * Created by adrianferenc on 06.10.2016.
 */
@SpringBootTest
@ActiveProfiles("test")
public class ShoppingCartServiceImplTest extends AbstractTestNGSpringContextTests {

    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private PerfumeService perfumeService;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        shoppingCartService = new ShoppingCartServiceImpl(perfumeService);
    }

    @Test
    public void shouldAddOnePerfume() throws Exception {
        Perfume perfume = new Perfume();
        perfume.setId(100);
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");

        ShoppingCart shoppingCart = shoppingCartService.add(perfume, 1);

        assertTrue(shoppingCart.getPerfumes().containsKey(perfume.getId()));
        assertEquals(shoppingCart.getPerfumes().size(), 1);
    }

    @Test
    public void shouldAddThreePiecesOfPerfume() throws Exception {
        Perfume perfume = new Perfume();
        perfume.setId(100);
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");

        ShoppingCart shoppingCart = shoppingCartService.add(perfume, 3);

        assertTrue(shoppingCart.getPerfumes().containsKey(perfume.getId()));
        assertEquals(shoppingCart.getPerfumes().get(perfume.getId()), new Integer(3));
    }

    @Test
    public void shouldRemoveAllFromShoppingCart() throws Exception {
        Perfume perfume = new Perfume();
        perfume.setId(100);
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");

        shoppingCartService.add(perfume, 3);
        ShoppingCart shoppingCart = shoppingCartService.clear();

        assertEquals(shoppingCart.getPerfumes().size(), 0);
    }

    @Test
    public void shouldReturnTwoPerfumesFromShoppingCart() {
        Perfume perfume = new Perfume();
        perfume.setId(100);
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");

        Perfume perfume2 = new Perfume();
        perfume2.setId(200);
        perfume2.setCapacity("200 ml");
        perfume2.setBrand("Chanel");
        perfume2.setCategory(PerfumeCategory.WOMEN.toString());
        perfume2.setName("No 5");
        perfume2.setPrice("345,00 zł");

        shoppingCartService.add(perfume, 3);
        shoppingCartService.add(perfume2, 1);

        when(perfumeService.find(eq(100))).thenReturn(perfume);
        when(perfumeService.find(eq(200))).thenReturn(perfume2);

        Map<Perfume, Integer> perfumes = shoppingCartService.getOrderedPerfumes();

        assertEquals(perfumes.size(), 2);
    }

    @Test
    public void shouldRemovePerfumeFromShoppingCart() {
        Perfume perfume = new Perfume();
        perfume.setId(100);
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");

        Perfume perfume2 = new Perfume();
        perfume2.setId(200);
        perfume2.setCapacity("200 ml");
        perfume2.setBrand("Chanel");
        perfume2.setCategory(PerfumeCategory.WOMEN.toString());
        perfume2.setName("No 5");
        perfume2.setPrice("345,00 zł");

        shoppingCartService.add(perfume, 3);
        shoppingCartService.add(perfume2, 1);

        when(perfumeService.find(eq(100))).thenReturn(perfume);
        when(perfumeService.find(eq(200))).thenReturn(perfume2);

        shoppingCartService.remove(100);

        assertFalse(shoppingCartService.getOrderedPerfumes().keySet().contains(100));
        assertEquals(shoppingCartService.countOrderedQuantity(), 1);
    }

    @Test
    public void shouldChangeOrderedQuantity() {
        Perfume perfume = new Perfume();
        perfume.setId(100);
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");

        Perfume perfume2 = new Perfume();
        perfume2.setId(200);
        perfume2.setCapacity("200 ml");
        perfume2.setBrand("Chanel");
        perfume2.setCategory(PerfumeCategory.WOMEN.toString());
        perfume2.setName("No 5");
        perfume2.setPrice("345,00 zł");

        shoppingCartService.add(perfume, 3);
        shoppingCartService.add(perfume2, 1);

        when(perfumeService.find(eq(100))).thenReturn(perfume);
        when(perfumeService.find(eq(200))).thenReturn(perfume2);

        shoppingCartService.updateQuantity(100, 10);

        assertEquals(shoppingCartService.getOrderedPerfumes().get(perfume), new Integer(10));
        assertEquals(shoppingCartService.countOrderedQuantity(), 11);
    }

}