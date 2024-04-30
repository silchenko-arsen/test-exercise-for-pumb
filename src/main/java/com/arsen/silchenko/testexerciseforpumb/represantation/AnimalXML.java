package com.arsen.silchenko.testexerciseforpumb.represantation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "animal")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
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
}

