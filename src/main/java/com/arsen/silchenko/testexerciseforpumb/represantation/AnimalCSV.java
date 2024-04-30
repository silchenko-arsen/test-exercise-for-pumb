package com.arsen.silchenko.testexerciseforpumb.represantation;

import com.opencsv.bean.CsvBindByName;

public class AnimalCSV {
    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Type")
    private String type;

    @CsvBindByName(column = "Sex")
    private String sex;

    @CsvBindByName(column = "Weight")
    private Integer weight;

    @CsvBindByName(column = "Cost")
    private Integer cost;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSex() {
        return sex;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}

