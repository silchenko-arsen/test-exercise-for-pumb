package com.arsen.silchenko.testexerciseforpumb.represantation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "animals")
public class AnimalXMLRepresentation {
    @XmlElement
    private String name;
    @XmlElement
    private String type;
    @XmlElement
    private String sex;
    @XmlElement
    private Integer weight;
    @XmlElement
    private Integer cost;
}

