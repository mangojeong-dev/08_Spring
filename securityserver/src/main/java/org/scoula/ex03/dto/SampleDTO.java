package org.scoula.ex03.dto;

import lombok.Data;
import lombok.ToString;

// @ToString
@Data
// 기본 생성자, Getter, Setter, toString(), hashCode(), equals()
public class SampleDTO {
    private String name;
    private int age;
    private double height;
}