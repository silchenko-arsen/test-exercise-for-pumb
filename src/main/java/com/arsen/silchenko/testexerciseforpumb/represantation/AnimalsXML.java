package com.arsen.silchenko.testexerciseforpumb.represantation;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;


@XmlRootElement(name = "animals")
@Getter
@NoArgsConstructor
public class AnimalsXML {
    @XmlElement(name = "animal")
    private List<AnimalXML> animalXMLList;
}
