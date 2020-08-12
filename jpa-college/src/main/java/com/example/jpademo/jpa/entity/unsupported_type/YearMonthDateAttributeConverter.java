package com.example.jpademo.jpa.entity.unsupported_type;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;

/**
 * Jpa不支持类型处理方法，可以写一个转换器
 *
 *
 * 当前使用方法在对应字段写上
 * //    @Column(
 * //            columnDefinition = "date"
 * //    )
 * //    @Convert(
 * //            converter = YearMonthDateAttributeConverter.class
 * //    )
 */
public class YearMonthDateAttributeConverter implements AttributeConverter<YearMonth, Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(YearMonth attribute) {
        if (attribute != null) {
            return java.sql.Date.valueOf(attribute.atDay(1)
            );
        }
        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(java.sql.Date dbData) {
        if (dbData != null) {
            return YearMonth.from(Instant.ofEpochMilli(dbData.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
            );
        }
        return null;
    }
}