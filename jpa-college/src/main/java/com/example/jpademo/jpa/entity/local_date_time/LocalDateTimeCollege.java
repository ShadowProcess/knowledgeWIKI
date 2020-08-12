package com.example.jpademo.jpa.entity.local_date_time;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.*;

/**
 *
 * @JSONField是阿里巴巴下fastjson下的，@JsonFormat是jackson下面的。
 * JSONField 也可用Jackson的@JsonProperty代替【@JsonProperty("name")】
 * 用法：目前最长的用属性是@JSONField(name=”resType”)和 @JSONField(format=”yyyy-MM-dd”)
 * name：@JSONField(name=”resType”)主要用于指定前端传到后台时对应的key值，如果bean中没有这个注解，则默认前端传过来的key是field本身，
 *          即如果是private String name，name前端对应的key就是name才能对应上。
 * format： @JSONField(format=”yyyy-MM-dd”)主要用于格式化日期，比如前台传过来的时间是2018-07-12 17:44:08，
 *          但是通过这个注解，你存到数据库的时间就是2018-07-12 00:00:00.
 *
 *
 * @DatetimeFormat是将String转换成Date    一般前台给后台传值时用
 * @JsonFormat(pattern="yyyy-MM-dd")    将Date转换成String,一般后台传值给前台时使用
 *
 * @DateTimeFormat(pattern) 用于将前端传过来的日期字符串自动转化为日期对象;
 * @JsonFormat(pattern)     用于将数据库中取出来的日期对象自动调整为pattern格式的日期对象。
 *
 *
 *  控制入参的格式，如果不符合将抛出异常
 *  @DateTimeFormat(pattern="yyyy-MM-dd-HH-mm-ss")
 *  private LocalDateTime localDateTime;
 *
 *  控制出参的格式
 *  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 *  private LocalDateTime localDateTime;
 *
 *  注意：二者可以联合使用
 */
@Table(name = "jpa_local_date_time_college")
@Entity
public class LocalDateTimeCollege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime localDateTime;//对应数据库类型 datetime
    private LocalDate localDate;        //对应数据库类型 datetime
    private LocalTime localTime;        //对应数据库类型 time

    private YearMonth ym;   //对应数据库类型 tinyblob
    private Year y;         //对应数据库类型 tinyblob
    private Month m;        //对应数据库类型 int

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public YearMonth getYm() {
        return ym;
    }

    public void setYm(YearMonth ym) {
        this.ym = ym;
    }

    public Year getY() {
        return y;
    }

    public void setY(Year y) {
        this.y = y;
    }

    public Month getM() {
        return m;
    }

    public void setM(Month m) {
        this.m = m;
    }
}
