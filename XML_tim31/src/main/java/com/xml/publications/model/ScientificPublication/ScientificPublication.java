//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.20 at 04:12:38 PM CEST 
//


package com.xml.publications.model.ScientificPublication;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadata">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="authors">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="authorUsername" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="chapter" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="paragraph" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="citation">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="authorNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                                       &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *                                       &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/choice>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="reference" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="authorNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                   &lt;element name="publicationTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="citationId" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="publicationId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="submissionDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="status" default="submitted">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="reviewing"/>
 *             &lt;enumeration value="revising"/>
 *             &lt;enumeration value="withdrawn"/>
 *             &lt;enumeration value="rejected"/>
 *             &lt;enumeration value="accepted"/>
 *             &lt;enumeration value="submitted"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "metadata",
    "chapter",
    "reference"
})
@XmlRootElement(name = "scientificPublication")
public class ScientificPublication {

    @XmlElement(required = true)
    protected Metadata metadata;
    @XmlElement(required = true)
    protected List<Chapter> chapter;
    protected List<Reference> reference;
    @XmlAttribute(name = "publicationId")
    protected String publicationId;
    @XmlAttribute(name = "submissionDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar submissionDate;
    @XmlAttribute(name = "language")
    protected String language;
    @XmlAttribute(name = "status")
    protected String status;

    /**
     * Gets the value of the metadata property.
     *
     * @return
     *     possible object is
     *     {@link Metadata }
     *
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * Sets the value of the metadata property.
     *
     * @param value
     *     allowed object is
     *     {@link Metadata }
     *
     */
    public void setMetadata(Metadata value) {
        this.metadata = value;
    }

    /**
     * Gets the value of the chapter property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chapter property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChapter().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Chapter }
     *
     *
     */
    public List<Chapter> getChapter() {
        if (chapter == null) {
            chapter = new ArrayList<Chapter>();
        }
        return this.chapter;
    }

    /**
     * Gets the value of the reference property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reference }
     *
     *
     */
    public List<Reference> getReference() {
        if (reference == null) {
            reference = new ArrayList<Reference>();
        }
        return this.reference;
    }

    /**
     * Gets the value of the publicationId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPublicationId() {
        return publicationId;
    }

    /**
     * Sets the value of the publicationId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPublicationId(String value) {
        this.publicationId = value;
    }

    /**
     * Gets the value of the submissionDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getSubmissionDate() {
        return submissionDate;
    }

    /**
     * Sets the value of the submissionDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setSubmissionDate(XMLGregorianCalendar value) {
        this.submissionDate = value;
    }

    /**
     * Gets the value of the language property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        if (status == null) {
            return "submitted";
        } else {
            return status;
        }
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="paragraph" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="citation">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="authorNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                             &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
     *                             &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/choice>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "title",
        "paragraph"
    })
    public static class Chapter {

        @XmlElement(required = true)
        protected String title;
        @XmlElement(required = true)
        protected List<Paragraph> paragraph;

        /**
         * Gets the value of the title property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets the value of the title property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Gets the value of the paragraph property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the paragraph property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParagraph().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Paragraph }
         *
         *
         */
        public List<Paragraph> getParagraph() {
            if (paragraph == null) {
                paragraph = new ArrayList<Paragraph>();
            }
            return this.paragraph;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;choice>
         *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="citation">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="authorNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
         *                   &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
         *                   &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/choice>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "text",
            "citation"
        })
        public static class Paragraph {

            protected String text;
            protected Citation citation;

            /**
             * Gets the value of the text property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getText() {
                return text;
            }

            /**
             * Sets the value of the text property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setText(String value) {
                this.text = value;
            }

            /**
             * Gets the value of the citation property.
             *
             * @return
             *     possible object is
             *     {@link Citation }
             *
             */
            public Citation getCitation() {
                return citation;
            }

            /**
             * Sets the value of the citation property.
             *
             * @param value
             *     allowed object is
             *     {@link Citation }
             *
             */
            public void setCitation(Citation value) {
                this.citation = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="authorNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
             *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
             *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "authorNames",
                "year",
                "text"
            })
            public static class Citation {

                @XmlElement(required = true)
                protected List<String> authorNames;
                @XmlElement(required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger year;
                @XmlElement(required = true)
                protected String text;
                @XmlAttribute(name = "id")
                protected BigInteger id;

                /**
                 * Gets the value of the authorNames property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the authorNames property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getAuthorNames().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 *
                 *
                 */
                public List<String> getAuthorNames() {
                    if (authorNames == null) {
                        authorNames = new ArrayList<String>();
                    }
                    return this.authorNames;
                }

                /**
                 * Gets the value of the year property.
                 *
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *
                 */
                public BigInteger getYear() {
                    return year;
                }

                /**
                 * Sets the value of the year property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *
                 */
                public void setYear(BigInteger value) {
                    this.year = value;
                }

                /**
                 * Gets the value of the text property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getText() {
                    return text;
                }

                /**
                 * Sets the value of the text property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setText(String value) {
                    this.text = value;
                }

                /**
                 * Gets the value of the id property.
                 *
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *
                 */
                public BigInteger getId() {
                    return id;
                }

                /**
                 * Sets the value of the id property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *
                 */
                public void setId(BigInteger value) {
                    this.id = value;
                }

            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="authors">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="authorUsername" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "title",
        "authors",
        "keywords"
    })
    public static class Metadata {

        @XmlElement(required = true)
        protected String title;
        @XmlElement(required = true)
        protected Authors authors;
        protected List<String> keywords;

        /**
         * Gets the value of the title property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets the value of the title property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Gets the value of the authors property.
         *
         * @return
         *     possible object is
         *     {@link Authors }
         *
         */
        public Authors getAuthors() {
            return authors;
        }

        /**
         * Sets the value of the authors property.
         *
         * @param value
         *     allowed object is
         *     {@link Authors }
         *
         */
        public void setAuthors(Authors value) {
            this.authors = value;
        }

        /**
         * Gets the value of the keywords property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the keywords property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKeywords().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getKeywords() {
            if (keywords == null) {
                keywords = new ArrayList<String>();
            }
            return this.keywords;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="authorUsername" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "authorUsername"
        })
        public static class Authors {

            @XmlElement(required = true)
            protected List<String> authorUsername;

            /**
             * Gets the value of the authorUsername property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the authorUsername property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAuthorUsername().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getAuthorUsername() {
                if (authorUsername == null) {
                    authorUsername = new ArrayList<String>();
                }
                return this.authorUsername;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="authorNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *         &lt;element name="publicationTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
     *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="citationId" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "authorNames",
        "publicationTitle",
        "year",
        "url"
    })
    public static class Reference {

        @XmlElement(required = true)
        protected List<String> authorNames;
        @XmlElement(required = true)
        protected String publicationTitle;
        @XmlElement(required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger year;
        @XmlElement(required = true)
        protected String url;
        @XmlAttribute(name = "citationId")
        protected BigInteger citationId;

        /**
         * Gets the value of the authorNames property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the authorNames property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAuthorNames().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAuthorNames() {
            if (authorNames == null) {
                authorNames = new ArrayList<String>();
            }
            return this.authorNames;
        }

        /**
         * Gets the value of the publicationTitle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPublicationTitle() {
            return publicationTitle;
        }

        /**
         * Sets the value of the publicationTitle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPublicationTitle(String value) {
            this.publicationTitle = value;
        }

        /**
         * Gets the value of the year property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getYear() {
            return year;
        }

        /**
         * Sets the value of the year property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setYear(BigInteger value) {
            this.year = value;
        }

        /**
         * Gets the value of the url property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUrl() {
            return url;
        }

        /**
         * Sets the value of the url property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUrl(String value) {
            this.url = value;
        }

        /**
         * Gets the value of the citationId property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getCitationId() {
            return citationId;
        }

        /**
         * Sets the value of the citationId property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setCitationId(BigInteger value) {
            this.citationId = value;
        }

    }

}
