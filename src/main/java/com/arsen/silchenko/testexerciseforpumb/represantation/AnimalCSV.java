package com.arsen.silchenko.testexerciseforpumb.represantation;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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
}

