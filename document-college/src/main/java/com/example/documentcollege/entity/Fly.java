package com.example.documentcollege.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Data
@ExcelTarget(value = "billExcel")
public class Fly {

    @Excel(name = "唯一标识", orderNum = "1", needMerge = true)
    private String id;

    @Excel(name = "名称", orderNum = "2", needMerge = true)
    private String name;

    @Excel(name = "开始时间", orderNum = "3", width = 20, format = "yyyy-MM-dd", needMerge = true)
    private LocalDateTime startTime;

    @Excel(name = "结束时间", orderNum = "4", width = 20, format = "yyyy-MM-dd", needMerge = true)
    private LocalDateTime endTime;

    @ExcelIgnore
    @ExcelCollection(name = "集合楼", orderNum = "5")
    private List<Object> objects = new ArrayList<>();
}
