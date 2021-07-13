package com.example.documentcollege.service;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.example.documentcollege.entity.Fly;
import com.example.documentcollege.hook.BeanHook;
import com.example.documentcollege.hook.CtlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExcelService {


    public void test() throws IOException {
        List<Fly> flyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Fly fly = new Fly()
                    .setId(String.valueOf(i))
                    .setName("1")
                    .setStartTime(LocalDateTime.now())
                    .setEndTime(LocalDateTime.now())
                    .setObjects(null);
            flyList.add(fly);
        }
        HttpServletResponse response = BeanHook.self().getResponse();
        // 设置响应输出的头类型
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + "test" + ".xls");

        ExportParams exportParams = new ExportParams("标题", "二级标题", "脚名");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Fly.class, flyList);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
    }


}
