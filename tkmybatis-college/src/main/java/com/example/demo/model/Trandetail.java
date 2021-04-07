package com.example.demo.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "trandetail")
public class Trandetail {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String storeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer posno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.transactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer transactionnumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.itemSeq
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer itemseq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.systemDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String systemdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.detailCode
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String detailcode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.itemVoid
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String itemvoid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.catNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String catno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.midCatNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String midcatno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.microCatNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String microcatno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.pluNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String pluno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.itemNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String itemnumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.pluName
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String pluname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.origPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal origprice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.unitPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal unitprice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.qty
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal qty;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.amount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.afterAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal afteramount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.taxType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String taxtype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal taxamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.discountNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String discountno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.memo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String memo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.mmType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String mmtype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.mmType2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String mmtype2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.mmBeforeAmtount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal mmbeforeamtount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.mmAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal mmamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.mmAfterAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal mmafteramount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.mmType3
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String mmtype3;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.inv
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer inv;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trandetail.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String uploadflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.id
     *
     * @return the value of trandetail.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.id
     *
     * @param id the value for trandetail.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.storeId
     *
     * @return the value of trandetail.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.storeId
     *
     * @param storeid the value for trandetail.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.posNo
     *
     * @return the value of trandetail.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getPosno() {
        return posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.posNo
     *
     * @param posno the value for trandetail.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.transactionNumber
     *
     * @return the value of trandetail.transactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getTransactionnumber() {
        return transactionnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.transactionNumber
     *
     * @param transactionnumber the value for trandetail.transactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTransactionnumber(Integer transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.itemSeq
     *
     * @return the value of trandetail.itemSeq
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getItemseq() {
        return itemseq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.itemSeq
     *
     * @param itemseq the value for trandetail.itemSeq
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setItemseq(Integer itemseq) {
        this.itemseq = itemseq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.systemDate
     *
     * @return the value of trandetail.systemDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getSystemdate() {
        return systemdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.systemDate
     *
     * @param systemdate the value for trandetail.systemDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.detailCode
     *
     * @return the value of trandetail.detailCode
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getDetailcode() {
        return detailcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.detailCode
     *
     * @param detailcode the value for trandetail.detailCode
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setDetailcode(String detailcode) {
        this.detailcode = detailcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.itemVoid
     *
     * @return the value of trandetail.itemVoid
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getItemvoid() {
        return itemvoid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.itemVoid
     *
     * @param itemvoid the value for trandetail.itemVoid
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setItemvoid(String itemvoid) {
        this.itemvoid = itemvoid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.catNo
     *
     * @return the value of trandetail.catNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getCatno() {
        return catno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.catNo
     *
     * @param catno the value for trandetail.catNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCatno(String catno) {
        this.catno = catno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.midCatNo
     *
     * @return the value of trandetail.midCatNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getMidcatno() {
        return midcatno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.midCatNo
     *
     * @param midcatno the value for trandetail.midCatNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMidcatno(String midcatno) {
        this.midcatno = midcatno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.microCatNo
     *
     * @return the value of trandetail.microCatNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getMicrocatno() {
        return microcatno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.microCatNo
     *
     * @param microcatno the value for trandetail.microCatNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMicrocatno(String microcatno) {
        this.microcatno = microcatno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.pluNo
     *
     * @return the value of trandetail.pluNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getPluno() {
        return pluno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.pluNo
     *
     * @param pluno the value for trandetail.pluNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPluno(String pluno) {
        this.pluno = pluno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.itemNumber
     *
     * @return the value of trandetail.itemNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getItemnumber() {
        return itemnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.itemNumber
     *
     * @param itemnumber the value for trandetail.itemNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setItemnumber(String itemnumber) {
        this.itemnumber = itemnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.pluName
     *
     * @return the value of trandetail.pluName
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getPluname() {
        return pluname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.pluName
     *
     * @param pluname the value for trandetail.pluName
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPluname(String pluname) {
        this.pluname = pluname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.origPrice
     *
     * @return the value of trandetail.origPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getOrigprice() {
        return origprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.origPrice
     *
     * @param origprice the value for trandetail.origPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setOrigprice(BigDecimal origprice) {
        this.origprice = origprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.unitPrice
     *
     * @return the value of trandetail.unitPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getUnitprice() {
        return unitprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.unitPrice
     *
     * @param unitprice the value for trandetail.unitPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.qty
     *
     * @return the value of trandetail.qty
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.qty
     *
     * @param qty the value for trandetail.qty
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.amount
     *
     * @return the value of trandetail.amount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.amount
     *
     * @param amount the value for trandetail.amount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.afterAmount
     *
     * @return the value of trandetail.afterAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getAfteramount() {
        return afteramount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.afterAmount
     *
     * @param afteramount the value for trandetail.afterAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setAfteramount(BigDecimal afteramount) {
        this.afteramount = afteramount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.taxType
     *
     * @return the value of trandetail.taxType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getTaxtype() {
        return taxtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.taxType
     *
     * @param taxtype the value for trandetail.taxType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTaxtype(String taxtype) {
        this.taxtype = taxtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.taxAmount
     *
     * @return the value of trandetail.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getTaxamount() {
        return taxamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.taxAmount
     *
     * @param taxamount the value for trandetail.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTaxamount(BigDecimal taxamount) {
        this.taxamount = taxamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.discountNo
     *
     * @return the value of trandetail.discountNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getDiscountno() {
        return discountno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.discountNo
     *
     * @param discountno the value for trandetail.discountNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setDiscountno(String discountno) {
        this.discountno = discountno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.memo
     *
     * @return the value of trandetail.memo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.memo
     *
     * @param memo the value for trandetail.memo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.mmType
     *
     * @return the value of trandetail.mmType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getMmtype() {
        return mmtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.mmType
     *
     * @param mmtype the value for trandetail.mmType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMmtype(String mmtype) {
        this.mmtype = mmtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.mmType2
     *
     * @return the value of trandetail.mmType2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getMmtype2() {
        return mmtype2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.mmType2
     *
     * @param mmtype2 the value for trandetail.mmType2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMmtype2(String mmtype2) {
        this.mmtype2 = mmtype2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.mmBeforeAmtount
     *
     * @return the value of trandetail.mmBeforeAmtount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getMmbeforeamtount() {
        return mmbeforeamtount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.mmBeforeAmtount
     *
     * @param mmbeforeamtount the value for trandetail.mmBeforeAmtount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMmbeforeamtount(BigDecimal mmbeforeamtount) {
        this.mmbeforeamtount = mmbeforeamtount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.mmAmount
     *
     * @return the value of trandetail.mmAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getMmamount() {
        return mmamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.mmAmount
     *
     * @param mmamount the value for trandetail.mmAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMmamount(BigDecimal mmamount) {
        this.mmamount = mmamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.mmAfterAmount
     *
     * @return the value of trandetail.mmAfterAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getMmafteramount() {
        return mmafteramount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.mmAfterAmount
     *
     * @param mmafteramount the value for trandetail.mmAfterAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMmafteramount(BigDecimal mmafteramount) {
        this.mmafteramount = mmafteramount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.mmType3
     *
     * @return the value of trandetail.mmType3
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getMmtype3() {
        return mmtype3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.mmType3
     *
     * @param mmtype3 the value for trandetail.mmType3
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMmtype3(String mmtype3) {
        this.mmtype3 = mmtype3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.inv
     *
     * @return the value of trandetail.inv
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getInv() {
        return inv;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.inv
     *
     * @param inv the value for trandetail.inv
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setInv(Integer inv) {
        this.inv = inv;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trandetail.uploadFlag
     *
     * @return the value of trandetail.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getUploadflag() {
        return uploadflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trandetail.uploadFlag
     *
     * @param uploadflag the value for trandetail.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }
}