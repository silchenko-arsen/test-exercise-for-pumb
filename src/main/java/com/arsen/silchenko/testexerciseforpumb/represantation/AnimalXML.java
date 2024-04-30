package com.arsen.silchenko.testexerciseforpumb.represantation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animal")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimalXML {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "type")
    private String type;

    @XmlElement(name = "sex")
    private String sex;

    @XmlElement(name = "weight")
    private Integer weight;

    @XmlElement(name = "cost")
    private Integer cost;

    public String getName() {
        return name;
    }

    public AnimalXML() {
    }

    public AnimalXML(String name, String type, String sex, Integer weight, Integer cost) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.weight = weight;
        this.cost = cost;
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
}

