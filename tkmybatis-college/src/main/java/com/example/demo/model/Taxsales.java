package com.example.demo.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "taxsales")
public class Taxsales {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxsales.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxsales.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String storeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxsales.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer posno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxsales.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer znumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxsales.accountDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String accountdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxsales.taxId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String taxid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxsales.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal taxamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column taxsales.netSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal netsaleamount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxsales.id
     *
     * @return the value of taxsales.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxsales.id
     *
     * @param id the value for taxsales.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxsales.storeId
     *
     * @return the value of taxsales.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxsales.storeId
     *
     * @param storeid the value for taxsales.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxsales.posNo
     *
     * @return the value of taxsales.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getPosno() {
        return posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxsales.posNo
     *
     * @param posno the value for taxsales.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxsales.zNumber
     *
     * @return the value of taxsales.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getZnumber() {
        return znumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxsales.zNumber
     *
     * @param znumber the value for taxsales.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setZnumber(Integer znumber) {
        this.znumber = znumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxsales.accountDate
     *
     * @return the value of taxsales.accountDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getAccountdate() {
        return accountdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxsales.accountDate
     *
     * @param accountdate the value for taxsales.accountDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setAccountdate(String accountdate) {
        this.accountdate = accountdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxsales.taxId
     *
     * @return the value of taxsales.taxId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getTaxid() {
        return taxid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxsales.taxId
     *
     * @param taxid the value for taxsales.taxId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxsales.taxAmount
     *
     * @return the value of taxsales.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getTaxamount() {
        return taxamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxsales.taxAmount
     *
     * @param taxamount the value for taxsales.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTaxamount(BigDecimal taxamount) {
        this.taxamount = taxamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column taxsales.netSaleAmount
     *
     * @return the value of taxsales.netSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getNetsaleamount() {
        return netsaleamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column taxsales.netSaleAmount
     *
     * @param netsaleamount the value for taxsales.netSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setNetsaleamount(BigDecimal netsaleamount) {
        this.netsaleamount = netsaleamount;
    }
}