package com.example.demo.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "yhead")
public class Yhead {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String storeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer posno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer znumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.yNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer ynumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.beginDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String begindate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.endDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.beginTransactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer begintransactionnumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.endTransactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer endtransactionnumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.accountDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String accountdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.cashierNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String cashiernumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.grossSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal grosssaleamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.discountAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal discountamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.netSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal netsaleamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.tranCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer trancount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.returnAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal returnamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.returnCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer returncount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.overAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal overamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.paidInAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal paidinamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.paidOutAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal paidoutamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String uploadflag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.cashAmount1
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal cashamount1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.cashAmount2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal cashamount2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.cashAmount3
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal cashamount3;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal taxamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.daiShouAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal daishouamount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.daiShouAmount2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal daishouamount2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.customerCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer customercount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.cashOutAmt
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal cashoutamt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yhead.roundDownAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private BigDecimal rounddownamount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.id
     *
     * @return the value of yhead.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.id
     *
     * @param id the value for yhead.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.storeId
     *
     * @return the value of yhead.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.storeId
     *
     * @param storeid the value for yhead.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.posNo
     *
     * @return the value of yhead.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getPosno() {
        return posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.posNo
     *
     * @param posno the value for yhead.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.zNumber
     *
     * @return the value of yhead.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getZnumber() {
        return znumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.zNumber
     *
     * @param znumber the value for yhead.zNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setZnumber(Integer znumber) {
        this.znumber = znumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.yNumber
     *
     * @return the value of yhead.yNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getYnumber() {
        return ynumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.yNumber
     *
     * @param ynumber the value for yhead.yNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setYnumber(Integer ynumber) {
        this.ynumber = ynumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.beginDate
     *
     * @return the value of yhead.beginDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getBegindate() {
        return begindate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.beginDate
     *
     * @param begindate the value for yhead.beginDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.endDate
     *
     * @return the value of yhead.endDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.endDate
     *
     * @param enddate the value for yhead.endDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.beginTransactionNumber
     *
     * @return the value of yhead.beginTransactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getBegintransactionnumber() {
        return begintransactionnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.beginTransactionNumber
     *
     * @param begintransactionnumber the value for yhead.beginTransactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setBegintransactionnumber(Integer begintransactionnumber) {
        this.begintransactionnumber = begintransactionnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.endTransactionNumber
     *
     * @return the value of yhead.endTransactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getEndtransactionnumber() {
        return endtransactionnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.endTransactionNumber
     *
     * @param endtransactionnumber the value for yhead.endTransactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setEndtransactionnumber(Integer endtransactionnumber) {
        this.endtransactionnumber = endtransactionnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.accountDate
     *
     * @return the value of yhead.accountDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getAccountdate() {
        return accountdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.accountDate
     *
     * @param accountdate the value for yhead.accountDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setAccountdate(String accountdate) {
        this.accountdate = accountdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.cashierNumber
     *
     * @return the value of yhead.cashierNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getCashiernumber() {
        return cashiernumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.cashierNumber
     *
     * @param cashiernumber the value for yhead.cashierNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCashiernumber(String cashiernumber) {
        this.cashiernumber = cashiernumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.grossSaleAmount
     *
     * @return the value of yhead.grossSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getGrosssaleamount() {
        return grosssaleamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.grossSaleAmount
     *
     * @param grosssaleamount the value for yhead.grossSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setGrosssaleamount(BigDecimal grosssaleamount) {
        this.grosssaleamount = grosssaleamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.discountAmount
     *
     * @return the value of yhead.discountAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getDiscountamount() {
        return discountamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.discountAmount
     *
     * @param discountamount the value for yhead.discountAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setDiscountamount(BigDecimal discountamount) {
        this.discountamount = discountamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.netSaleAmount
     *
     * @return the value of yhead.netSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getNetsaleamount() {
        return netsaleamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.netSaleAmount
     *
     * @param netsaleamount the value for yhead.netSaleAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setNetsaleamount(BigDecimal netsaleamount) {
        this.netsaleamount = netsaleamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.tranCount
     *
     * @return the value of yhead.tranCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getTrancount() {
        return trancount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.tranCount
     *
     * @param trancount the value for yhead.tranCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTrancount(Integer trancount) {
        this.trancount = trancount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.returnAmount
     *
     * @return the value of yhead.returnAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getReturnamount() {
        return returnamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.returnAmount
     *
     * @param returnamount the value for yhead.returnAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setReturnamount(BigDecimal returnamount) {
        this.returnamount = returnamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.returnCount
     *
     * @return the value of yhead.returnCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getReturncount() {
        return returncount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.returnCount
     *
     * @param returncount the value for yhead.returnCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setReturncount(Integer returncount) {
        this.returncount = returncount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.overAmount
     *
     * @return the value of yhead.overAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getOveramount() {
        return overamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.overAmount
     *
     * @param overamount the value for yhead.overAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setOveramount(BigDecimal overamount) {
        this.overamount = overamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.paidInAmount
     *
     * @return the value of yhead.paidInAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getPaidinamount() {
        return paidinamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.paidInAmount
     *
     * @param paidinamount the value for yhead.paidInAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPaidinamount(BigDecimal paidinamount) {
        this.paidinamount = paidinamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.paidOutAmount
     *
     * @return the value of yhead.paidOutAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getPaidoutamount() {
        return paidoutamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.paidOutAmount
     *
     * @param paidoutamount the value for yhead.paidOutAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPaidoutamount(BigDecimal paidoutamount) {
        this.paidoutamount = paidoutamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.uploadFlag
     *
     * @return the value of yhead.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getUploadflag() {
        return uploadflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.uploadFlag
     *
     * @param uploadflag the value for yhead.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.cashAmount1
     *
     * @return the value of yhead.cashAmount1
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getCashamount1() {
        return cashamount1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.cashAmount1
     *
     * @param cashamount1 the value for yhead.cashAmount1
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCashamount1(BigDecimal cashamount1) {
        this.cashamount1 = cashamount1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.cashAmount2
     *
     * @return the value of yhead.cashAmount2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getCashamount2() {
        return cashamount2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.cashAmount2
     *
     * @param cashamount2 the value for yhead.cashAmount2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCashamount2(BigDecimal cashamount2) {
        this.cashamount2 = cashamount2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.cashAmount3
     *
     * @return the value of yhead.cashAmount3
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getCashamount3() {
        return cashamount3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.cashAmount3
     *
     * @param cashamount3 the value for yhead.cashAmount3
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCashamount3(BigDecimal cashamount3) {
        this.cashamount3 = cashamount3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.taxAmount
     *
     * @return the value of yhead.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getTaxamount() {
        return taxamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.taxAmount
     *
     * @param taxamount the value for yhead.taxAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTaxamount(BigDecimal taxamount) {
        this.taxamount = taxamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.daiShouAmount
     *
     * @return the value of yhead.daiShouAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getDaishouamount() {
        return daishouamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.daiShouAmount
     *
     * @param daishouamount the value for yhead.daiShouAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setDaishouamount(BigDecimal daishouamount) {
        this.daishouamount = daishouamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.daiShouAmount2
     *
     * @return the value of yhead.daiShouAmount2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getDaishouamount2() {
        return daishouamount2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.daiShouAmount2
     *
     * @param daishouamount2 the value for yhead.daiShouAmount2
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setDaishouamount2(BigDecimal daishouamount2) {
        this.daishouamount2 = daishouamount2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.customerCount
     *
     * @return the value of yhead.customerCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getCustomercount() {
        return customercount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.customerCount
     *
     * @param customercount the value for yhead.customerCount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCustomercount(Integer customercount) {
        this.customercount = customercount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.cashOutAmt
     *
     * @return the value of yhead.cashOutAmt
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getCashoutamt() {
        return cashoutamt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.cashOutAmt
     *
     * @param cashoutamt the value for yhead.cashOutAmt
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCashoutamt(BigDecimal cashoutamt) {
        this.cashoutamt = cashoutamt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yhead.roundDownAmount
     *
     * @return the value of yhead.roundDownAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public BigDecimal getRounddownamount() {
        return rounddownamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yhead.roundDownAmount
     *
     * @param rounddownamount the value for yhead.roundDownAmount
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setRounddownamount(BigDecimal rounddownamount) {
        this.rounddownamount = rounddownamount;
    }
}