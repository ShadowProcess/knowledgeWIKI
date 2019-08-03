package com.example.demo.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "zpayment")
public class Zpayment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String storeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer posno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer znumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.payNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String payno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.payAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal payamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.payCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer paycount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.payCat
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String paycat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.returnAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal returnamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zpayment.returnCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer returncount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.id
     *
     * @return the value of zpayment.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.id
     *
     * @param id the value for zpayment.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.storeId
     *
     * @return the value of zpayment.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.storeId
     *
     * @param storeid the value for zpayment.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.posNo
     *
     * @return the value of zpayment.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getPosno() {
        return posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.posNo
     *
     * @param posno the value for zpayment.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.zNumber
     *
     * @return the value of zpayment.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getZnumber() {
        return znumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.zNumber
     *
     * @param znumber the value for zpayment.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setZnumber(Integer znumber) {
        this.znumber = znumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.payNo
     *
     * @return the value of zpayment.payNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getPayno() {
        return payno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.payNo
     *
     * @param payno the value for zpayment.payNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPayno(String payno) {
        this.payno = payno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.payAmount
     *
     * @return the value of zpayment.payAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getPayamount() {
        return payamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.payAmount
     *
     * @param payamount the value for zpayment.payAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPayamount(BigDecimal payamount) {
        this.payamount = payamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.payCount
     *
     * @return the value of zpayment.payCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getPaycount() {
        return paycount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.payCount
     *
     * @param paycount the value for zpayment.payCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPaycount(Integer paycount) {
        this.paycount = paycount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.payCat
     *
     * @return the value of zpayment.payCat
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getPaycat() {
        return paycat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.payCat
     *
     * @param paycat the value for zpayment.payCat
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPaycat(String paycat) {
        this.paycat = paycat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.returnAmount
     *
     * @return the value of zpayment.returnAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getReturnamount() {
        return returnamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.returnAmount
     *
     * @param returnamount the value for zpayment.returnAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setReturnamount(BigDecimal returnamount) {
        this.returnamount = returnamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zpayment.returnCount
     *
     * @return the value of zpayment.returnCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getReturncount() {
        return returncount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zpayment.returnCount
     *
     * @param returncount the value for zpayment.returnCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setReturncount(Integer returncount) {
        this.returncount = returncount;
    }
}