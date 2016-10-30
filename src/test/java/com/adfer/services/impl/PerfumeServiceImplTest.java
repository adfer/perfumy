package com.adfer.services.impl;

import com.adfer.domain.Perfume;
import com.adfer.enums.PerfumeCategory;
import com.adfer.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.StringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@SpringBootTest
@ActiveProfiles("test")
public class PerfumeServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PerfumeServiceImpl perfumeService;

    @Autowired
    private PerfumeRepository repository;

    @BeforeMethod
    public void setUp() throws Exception {
        Perfume perfume1 = new Perfume();
        perfume1.setBrand("Giorgio Armani");
        perfume1.setName("Acqua di Gioia");
        perfume1.setCapacity("50 ml");
        perfume1.setCategory(PerfumeCategory.WOMEN.toString());
        perfume1.setPrice("200.20 zł");
        perfume1.setPicture("http://f8ni.com/assets/images/acqua-di%20gioia-50ml-1.jpg");

        Perfume perfume2 = new Perfume();
        perfume2.setBrand("Chanel");
        perfume2.setName("Coco Mademoiselle");
        perfume2.setCapacity("150 ml");
        perfume2.setCategory(PerfumeCategory.WOMEN.toString());
        perfume2.setPrice("120.00 zł");

        Perfume perfume3 = new Perfume();
        perfume3.setBrand("Giorgio Armani");
        perfume3.setName("Acqua di Gioia");
        perfume3.setCapacity("50 ml");
        perfume3.setCategory(PerfumeCategory.WOMEN.toString());
        perfume3.setPrice("200.20 zł");
        perfume3.setPicture("http://f8ni.com/assets/images/acqua-di%20gioia-50ml-1.jpg");

        Perfume perfume4 = new Perfume();
        perfume4.setBrand("Chanel");
        perfume4.setName("Coco Mademoiselle");
        perfume4.setCapacity("150 ml");
        perfume4.setCategory(PerfumeCategory.WOMEN.toString());
        perfume4.setPrice("120.00 zł");

        Perfume perfume5 = new Perfume();
        perfume5.setBrand("Giorgio Armani");
        perfume5.setName("Acqua di Gioia");
        perfume5.setCapacity("50 ml");
        perfume5.setCategory(PerfumeCategory.WOMEN.toString());
        perfume5.setPrice("200.20 zł");
        perfume5.setPicture("http://f8ni.com/assets/images/acqua-di%20gioia-50ml-1.jpg");

        Perfume perfume6 = new Perfume();
        perfume6.setBrand("Chanel");
        perfume6.setName("Coco Mademoiselle");
        perfume6.setCapacity("150 ml");
        perfume6.setCategory(PerfumeCategory.WOMEN.toString());
        perfume6.setPrice("120.00 zł");

        repository.save(Arrays.asList(perfume1, perfume2, perfume3, perfume4, perfume5, perfume6));
    }

    @AfterMethod
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void shouldReturnListWithSixPerfumes() {
        List<Perfume> result = perfumeService.getAll(PerfumeCategory.WOMEN, new PageRequest(0, 10)).getContent();
        assertEquals(result.size(), 6);
    }

    @Test
    public void shouldReturnEmptyList() {
        List<Perfume> result = perfumeService.getAll(PerfumeCategory.WOMEN, new PageRequest(10, 10)).getContent();
        assertEquals(result.size(), 0);
    }

    @Test
    public void shouldCreateNewPerfume() {
        Perfume perfume = new Perfume();
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");

        Perfume persPerfume = perfumeService.add(perfume);

        assertFalse(StringUtils.isEmpty(persPerfume.getId()));
        assertEquals(persPerfume.getBrand(), perfume.getBrand());
        assertEquals(persPerfume.getCapacity(), perfume.getCapacity());
        assertEquals(persPerfume.getCategory(), perfume.getCategory());
        assertEquals(persPerfume.getName(), perfume.getName());
        assertEquals(persPerfume.getPicture(), perfume.getPicture());
        assertEquals(persPerfume.getPrice(), perfume.getPrice());
    }

    @Test
    public void shouldUpdatePerfume() {
        Perfume perfume = new Perfume();
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");


        Perfume persPerfume = perfumeService.add(perfume);

        persPerfume.setBrand("new brand");
        persPerfume.setCapacity("new capacity");
        persPerfume.setCategory("new category");
        persPerfume.setName("new name");
        persPerfume.setPrice("new price");
        persPerfume.setPicture("new picture");

        Perfume changedPerfume = perfumeService.update(persPerfume);

        assertEquals(changedPerfume.getId(), persPerfume.getId());
        assertEquals(changedPerfume.getBrand(), "new brand");
        assertEquals(changedPerfume.getCapacity(), "new capacity");
        assertEquals(changedPerfume.getCategory(), "new category");
        assertEquals(changedPerfume.getName(), "new name");
        assertEquals(changedPerfume.getPicture(), "new picture");
        assertEquals(changedPerfume.getPrice(), "new price");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void shouldThrowRuntimeExceptionPerfumeWithoutId() {
        Perfume perfume = new Perfume();
        perfume.setCapacity("100 ml");
        perfume.setBrand("Azzaro");
        perfume.setCategory(PerfumeCategory.MEN.toString());
        perfume.setName("Chrome");
        perfume.setPrice("111,90 zł");
        perfume.setPicture("http://www.nailthedeal.com/static/team/2013/1209/13865767006972.jpg");

        perfumeService.update(perfume);
    }


}