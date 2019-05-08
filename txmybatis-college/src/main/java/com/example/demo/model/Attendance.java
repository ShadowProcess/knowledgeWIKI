package com.example.demo.model;

import javax.persistence.*;

@Table(name = "attendance")
public class Attendance {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attendance.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attendance.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer znumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attendance.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer posno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attendance.storeID
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String storeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attendance.cashierNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String cashierno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attendance.shiftType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String shifttype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attendance.signOnDateTime
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String signondatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column attendance.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String uploadflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attendance.id
     *
     * @return the value of attendance.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attendance.id
     *
     * @param id the value for attendance.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attendance.zNumber
     *
     * @return the value of attendance.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getZnumber() {
        return znumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attendance.zNumber
     *
     * @param znumber the value for attendance.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setZnumber(Integer znumber) {
        this.znumber = znumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attendance.posNo
     *
     * @return the value of attendance.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getPosno() {
        return posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attendance.posNo
     *
     * @param posno the value for attendance.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attendance.storeID
     *
     * @return the value of attendance.storeID
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attendance.storeID
     *
     * @param storeid the value for attendance.storeID
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attendance.cashierNo
     *
     * @return the value of attendance.cashierNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getCashierno() {
        return cashierno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attendance.cashierNo
     *
     * @param cashierno the value for attendance.cashierNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCashierno(String cashierno) {
        this.cashierno = cashierno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attendance.shiftType
     *
     * @return the value of attendance.shiftType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getShifttype() {
        return shifttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attendance.shiftType
     *
     * @param shifttype the value for attendance.shiftType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setShifttype(String shifttype) {
        this.shifttype = shifttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attendance.signOnDateTime
     *
     * @return the value of attendance.signOnDateTime
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getSignondatetime() {
        return signondatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attendance.signOnDateTime
     *
     * @param signondatetime the value for attendance.signOnDateTime
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setSignondatetime(String signondatetime) {
        this.signondatetime = signondatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column attendance.uploadFlag
     *
     * @return the value of attendance.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getUploadflag() {
        return uploadflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column attendance.uploadFlag
     *
     * @param uploadflag the value for attendance.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }
}