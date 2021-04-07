package com.example.demo.model;

import javax.persistence.*;

@Table(name = "coupondetail")
public class Coupondetail {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String storeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer posno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.transactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer transactionnumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.ticketId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String ticketid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.payId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String payid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.memCouponPayId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String memcouponpayid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.payName
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String payname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.ticketFaceValue
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String ticketfacevalue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.systemDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String systemdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.ticketType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String tickettype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String uploadflag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.tradeNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String tradeno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.pluNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String pluno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.itemSeq
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer itemseq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.couponType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String coupontype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.barcode
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String barcode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.goodsId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String goodsid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.discountPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String discountprice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupondetail.sellKbn
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer sellkbn;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.id
     *
     * @return the value of coupondetail.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.id
     *
     * @param id the value for coupondetail.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.storeId
     *
     * @return the value of coupondetail.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.storeId
     *
     * @param storeid the value for coupondetail.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.posNo
     *
     * @return the value of coupondetail.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getPosno() {
        return posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.posNo
     *
     * @param posno the value for coupondetail.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.transactionNumber
     *
     * @return the value of coupondetail.transactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getTransactionnumber() {
        return transactionnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.transactionNumber
     *
     * @param transactionnumber the value for coupondetail.transactionNumber
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTransactionnumber(Integer transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.ticketId
     *
     * @return the value of coupondetail.ticketId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getTicketid() {
        return ticketid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.ticketId
     *
     * @param ticketid the value for coupondetail.ticketId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.payId
     *
     * @return the value of coupondetail.payId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getPayid() {
        return payid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.payId
     *
     * @param payid the value for coupondetail.payId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPayid(String payid) {
        this.payid = payid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.memCouponPayId
     *
     * @return the value of coupondetail.memCouponPayId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getMemcouponpayid() {
        return memcouponpayid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.memCouponPayId
     *
     * @param memcouponpayid the value for coupondetail.memCouponPayId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMemcouponpayid(String memcouponpayid) {
        this.memcouponpayid = memcouponpayid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.payName
     *
     * @return the value of coupondetail.payName
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getPayname() {
        return payname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.payName
     *
     * @param payname the value for coupondetail.payName
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPayname(String payname) {
        this.payname = payname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.ticketFaceValue
     *
     * @return the value of coupondetail.ticketFaceValue
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getTicketfacevalue() {
        return ticketfacevalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.ticketFaceValue
     *
     * @param ticketfacevalue the value for coupondetail.ticketFaceValue
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTicketfacevalue(String ticketfacevalue) {
        this.ticketfacevalue = ticketfacevalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.systemDate
     *
     * @return the value of coupondetail.systemDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getSystemdate() {
        return systemdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.systemDate
     *
     * @param systemdate the value for coupondetail.systemDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setSystemdate(String systemdate) {
        this.systemdate = systemdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.ticketType
     *
     * @return the value of coupondetail.ticketType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getTickettype() {
        return tickettype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.ticketType
     *
     * @param tickettype the value for coupondetail.ticketType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTickettype(String tickettype) {
        this.tickettype = tickettype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.uploadFlag
     *
     * @return the value of coupondetail.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getUploadflag() {
        return uploadflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.uploadFlag
     *
     * @param uploadflag the value for coupondetail.uploadFlag
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.tradeNo
     *
     * @return the value of coupondetail.tradeNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getTradeno() {
        return tradeno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.tradeNo
     *
     * @param tradeno the value for coupondetail.tradeNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setTradeno(String tradeno) {
        this.tradeno = tradeno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.pluNo
     *
     * @return the value of coupondetail.pluNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getPluno() {
        return pluno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.pluNo
     *
     * @param pluno the value for coupondetail.pluNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPluno(String pluno) {
        this.pluno = pluno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.itemSeq
     *
     * @return the value of coupondetail.itemSeq
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getItemseq() {
        return itemseq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.itemSeq
     *
     * @param itemseq the value for coupondetail.itemSeq
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setItemseq(Integer itemseq) {
        this.itemseq = itemseq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.couponType
     *
     * @return the value of coupondetail.couponType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getCoupontype() {
        return coupontype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.couponType
     *
     * @param coupontype the value for coupondetail.couponType
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setCoupontype(String coupontype) {
        this.coupontype = coupontype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.barcode
     *
     * @return the value of coupondetail.barcode
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.barcode
     *
     * @param barcode the value for coupondetail.barcode
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.goodsId
     *
     * @return the value of coupondetail.goodsId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getGoodsid() {
        return goodsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.goodsId
     *
     * @param goodsid the value for coupondetail.goodsId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.discountPrice
     *
     * @return the value of coupondetail.discountPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getDiscountprice() {
        return discountprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.discountPrice
     *
     * @param discountprice the value for coupondetail.discountPrice
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupondetail.sellKbn
     *
     * @return the value of coupondetail.sellKbn
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getSellkbn() {
        return sellkbn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupondetail.sellKbn
     *
     * @param sellkbn the value for coupondetail.sellKbn
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setSellkbn(Integer sellkbn) {
        this.sellkbn = sellkbn;
    }
}