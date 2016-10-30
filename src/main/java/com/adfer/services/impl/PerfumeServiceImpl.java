package com.adfer.services.impl;

import com.adfer.domain.Perfume;
import com.adfer.enums.PerfumeCategory;
import com.adfer.repository.PerfumeRepository;
import com.adfer.services.PerfumeService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Perf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adrianferenc on 05.10.2016.
 */
@Service
public class PerfumeServiceImpl implements PerfumeService {

    private Logger LOGGER = LoggerFactory.getLogger(PerfumeServiceImpl.class);

    private PerfumeRepository repository;

    @Autowired
    public PerfumeServiceImpl(PerfumeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Perfume> getAll(PerfumeCategory category, Pageable pageable) {
        return repository.findAllByCategory(category.toString(), pageable);
    }

    public Page<Perfume> findByNameContaining(PerfumeCategory perfumeCategory, String name, Pageable pageable){
        return repository.findByCategoryAndNameContaining(perfumeCategory.toString(), name, pageable);
    }

    @Override
    public Perfume add(Perfume perfume) {
        return repository.save(perfume);
    }

    @Override
    public Perfume update(Perfume perfume) {
        if (StringUtils.isEmpty(perfume.getId())) {
            throw new RuntimeException("Perfume's ID must not be null");
        }
        return repository.save(perfume);
    }

    @Override
    public Perfume find(Integer perfumeId) {
        return repository.findOne(perfumeId);
    }

    @Override
    public void loadPerfumes(MultipartFile file) {
        List<Perfume> perfumes = new ArrayList<>();
        Workbook workbook;
        try {
                workbook = WorkbookFactory.create(file.getInputStream());
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() < 2) {
                    continue;
                }
                String capacity = "20 ml";
                perfumes.add(createPerfume(nextRow.getCell(1), PerfumeCategory.WOMEN.toString(), capacity));
                perfumes.add(createPerfume(nextRow.getCell(4), PerfumeCategory.WOMEN.toString(), capacity));
                perfumes.add(createPerfume(nextRow.getCell(7), PerfumeCategory.MEN.toString(), capacity));
                perfumes.add(createPerfume(nextRow.getCell(10), PerfumeCategory.MEN.toString(), capacity));
            }

            workbook.close();
        } catch (InvalidFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        repository.save(perfumes.stream().filter(p -> p != null).collect(Collectors.toList()));
    }

    @Override
    public long countByCategory(String perfumeCategory) {
        return repository.countByCategory(perfumeCategory);
    }

    private Perfume createPerfume(Cell cell, String perfumeCategory, String capacity) {
        String name = "";
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                name = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                name = String.valueOf(cell.getNumericCellValue());
            default:
                LOGGER.warn("No string or numeric value at cell in column " + cell.getColumnIndex() + " row " + cell.getRowIndex());
                break;
        }
        if(StringUtils.isEmpty(name)){
            return null;
        }
        Perfume perfume = new Perfume();
        perfume.setName(name);
        perfume.setCategory(perfumeCategory);
        perfume.setCapacity(capacity);
        return perfume;
    }


}
