package com.example.demo.model;

import javax.persistence.*;

@Table(name = "version")
public class Version {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column version.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column version.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String storeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column version.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private Integer posno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column version.version
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column version.masterVersion
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String masterversion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column version.updateDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    private String updatedate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column version.id
     *
     * @return the value of version.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column version.id
     *
     * @param id the value for version.id
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column version.storeId
     *
     * @return the value of version.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column version.storeId
     *
     * @param storeid the value for version.storeId
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column version.posNo
     *
     * @return the value of version.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public Integer getPosno() {
        return posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column version.posNo
     *
     * @param posno the value for version.posNo
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setPosno(Integer posno) {
        this.posno = posno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column version.version
     *
     * @return the value of version.version
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column version.version
     *
     * @param version the value for version.version
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column version.masterVersion
     *
     * @return the value of version.masterVersion
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getMasterversion() {
        return masterversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column version.masterVersion
     *
     * @param masterversion the value for version.masterVersion
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setMasterversion(String masterversion) {
        this.masterversion = masterversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column version.updateDate
     *
     * @return the value of version.updateDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public String getUpdatedate() {
        return updatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column version.updateDate
     *
     * @param updatedate the value for version.updateDate
     *
     * @mbg.generated Mon May 18 09:42:44 CST 2020
     */
    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }
}