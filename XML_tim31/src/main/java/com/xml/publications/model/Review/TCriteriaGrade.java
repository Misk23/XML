//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.07 at 08:08:36 PM CEST 
//


package com.xml.publications.model.Review;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TCriteriaGrade.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TCriteriaGrade">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="excellent"/>
 *     &lt;enumeration value="very good"/>
 *     &lt;enumeration value="good"/>
 *     &lt;enumeration value="small weaknesses"/>
 *     &lt;enumeration value="great weaknesses"/>
 *     &lt;enumeration value="absolutely insufficient"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TCriteriaGrade")
@XmlEnum
public enum TCriteriaGrade {

    @XmlEnumValue("excellent")
    EXCELLENT("excellent"),
    @XmlEnumValue("very good")
    VERY_GOOD("very good"),
    @XmlEnumValue("good")
    GOOD("good"),
    @XmlEnumValue("small weaknesses")
    SMALL_WEAKNESSES("small weaknesses"),
    @XmlEnumValue("great weaknesses")
    GREAT_WEAKNESSES("great weaknesses"),
    @XmlEnumValue("absolutely insufficient")
    ABSOLUTELY_INSUFFICIENT("absolutely insufficient");
    private final String value;

    TCriteriaGrade(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TCriteriaGrade fromValue(String v) {
        for (TCriteriaGrade c: TCriteriaGrade.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}