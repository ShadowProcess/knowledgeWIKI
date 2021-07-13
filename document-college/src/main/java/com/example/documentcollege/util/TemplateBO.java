package com.example.documentcollege.util;


import java.util.List;


public class TemplateBO {

    private String templateName;

    private String freeMarkerUrl;

    private String ITEXTUrl;

    private String JFreeChartUrl;

    private List<String> scores;

    private String imageUrl;

    private String picUrl;

    private String scatterUrl;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFreeMarkerUrl() {
        return freeMarkerUrl;
    }

    public void setFreeMarkerUrl(String freeMarkerUrl) {
        this.freeMarkerUrl = freeMarkerUrl;
    }

    public String getITEXTUrl() {
        return ITEXTUrl;
    }

    public void setITEXTUrl(String ITEXTUrl) {
        this.ITEXTUrl = ITEXTUrl;
    }

    public String getJFreeChartUrl() {
        return JFreeChartUrl;
    }

    public void setJFreeChartUrl(String JFreeChartUrl) {
        this.JFreeChartUrl = JFreeChartUrl;
    }
//
//    public List<String> getScores() {
//        return scores;
//    }
//
//    public void setScores(List<String> scores) {
//        this.scores = scores;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getScatterUrl() {
        return scatterUrl;
    }

//    public void setScatterUrl(String scatterUrl) {
//        this.scatterUrl = scatterUrl;
//    }

//    @Override
//    public String toString() {
//        return "TemplateBO{" +
//                "templateName='" + templateName + '\'' +
//                ", freeMarkerUrl='" + freeMarkerUrl + '\'' +
//                ", ITEXTUrl='" + ITEXTUrl + '\'' +
//                ", JFreeChartUrl='" + JFreeChartUrl + '\'' +
//                ", scores=" + scores +
//                ", imageUrl='" + imageUrl + '\'' +
//                ", picUrl='" + picUrl + '\'' +
//                ", scatterUrl='" + scatterUrl + '\'' +
//                '}';
//    }
}
