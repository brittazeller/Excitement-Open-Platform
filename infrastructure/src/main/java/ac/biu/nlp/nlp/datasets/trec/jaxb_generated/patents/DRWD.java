//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.28 at 10:37:56 AM GMT+02:00 
//


package ac.biu.nlp.nlp.datasets.trec.jaxb_generated.patents;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{}PA1"/>
 *         &lt;element ref="{}PAC"/>
 *         &lt;element ref="{}PAR"/>
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
    "pa1OrPACOrPAR"
})
@XmlRootElement(name = "DRWD")
public class DRWD {

    @XmlElementRefs({
        @XmlElementRef(name = "PA1", type = JAXBElement.class),
        @XmlElementRef(name = "PAC", type = JAXBElement.class),
        @XmlElementRef(name = "PAR", type = JAXBElement.class)
    })
    protected List<JAXBElement<String>> pa1OrPACOrPAR;

    /**
     * Gets the value of the pa1OrPACOrPAR property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pa1OrPACOrPAR property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPA1OrPACOrPAR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<String>> getPA1OrPACOrPAR() {
        if (pa1OrPACOrPAR == null) {
            pa1OrPACOrPAR = new ArrayList<JAXBElement<String>>();
        }
        return this.pa1OrPACOrPAR;
    }

}
