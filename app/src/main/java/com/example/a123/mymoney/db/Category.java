package com.example.a123.mymoney.db;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by KudzuLabyrinth on 2017/11/19.
 */

public class Category extends DataSupport {
    /**
     * 增加至類別:食衣住行育樂
     */
    private List<String> dieting;

    private List<String> clothing;

    private List<String> accommodation;

    private List<String> transportation;

    private List<String> education;

    private List<String> recreation;

    public List<String> getDieting() {
        return dieting;
    }

    public void setDieting(List<String> dieting) {
        this.dieting = dieting;
    }

    public List<String> getClothing() {
        return clothing;
    }

    public void setClothing(List<String> clothing) {
        this.clothing = clothing;
    }

    public List<String> getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(List<String> accommodation) {
        this.accommodation = accommodation;
    }

    public List<String> getTransportation() {
        return transportation;
    }

    public void setTransportation(List<String> transportation) {
        this.transportation = transportation;
    }

    public List<String> getEducation() {
        return education;
    }

    public void setEducation(List<String> education) {
        this.education = education;
    }

    public List<String> getRecreation() {
        return recreation;
    }

    public void setRecreation(List<String> recreation) {
        this.recreation = recreation;
    }





}
