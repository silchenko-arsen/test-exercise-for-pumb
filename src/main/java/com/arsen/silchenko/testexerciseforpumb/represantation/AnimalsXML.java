package com.arsen.silchenko.testexerciseforpumb.represantation;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "animals")
public class AnimalsXML {
    @XmlElement(name = "animal")
    private List<AnimalXML> animalXMLList;

    public List<AnimalXML> getAnimalXMLList() {
        return animalXMLList;
    }
}
