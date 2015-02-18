//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.02.16 à 01:17:56 AM CET 
//


package fr.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour article complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="article">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codebar" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *         &lt;element name="libelle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prixachat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prixvente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categorie" type="{categorie}categorie"/>
 *         &lt;element name="utilisateur" type="{utilisateur}utilisateur"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "article", namespace = "article", propOrder = {
    "codebar",
    "libelle",
    "prixachat",
    "prixvente",
    "quantite",
    "categorie",
    "utilisateur"
})
public class Article {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String codebar;
    @XmlElement(required = true)
    protected String libelle;
    @XmlElement(required = true)
    protected String prixachat;
    @XmlElement(required = true)
    protected String prixvente;
    @XmlElement(required = true)
    protected String quantite;
    @XmlElement(required = true, nillable = true)
    protected Categorie categorie;
    @XmlElement(required = true, nillable = true)
    protected Utilisateur utilisateur;

    /**
     * Obtient la valeur de la propriété codebar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodebar() {
        return codebar;
    }

    /**
     * Définit la valeur de la propriété codebar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodebar(String value) {
        this.codebar = value;
    }

    /**
     * Obtient la valeur de la propriété libelle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit la valeur de la propriété libelle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLibelle(String value) {
        this.libelle = value;
    }

    /**
     * Obtient la valeur de la propriété prixachat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrixachat() {
        return prixachat;
    }

    /**
     * Définit la valeur de la propriété prixachat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrixachat(String value) {
        this.prixachat = value;
    }

    /**
     * Obtient la valeur de la propriété prixvente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrixvente() {
        return prixvente;
    }

    /**
     * Définit la valeur de la propriété prixvente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrixvente(String value) {
        this.prixvente = value;
    }

    /**
     * Obtient la valeur de la propriété quantite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantite() {
        return quantite;
    }

    /**
     * Définit la valeur de la propriété quantite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantite(String value) {
        this.quantite = value;
    }

    /**
     * Obtient la valeur de la propriété categorie.
     * 
     * @return
     *     possible object is
     *     {@link Categorie }
     *     
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * Définit la valeur de la propriété categorie.
     * 
     * @param value
     *     allowed object is
     *     {@link Categorie }
     *     
     */
    public void setCategorie(Categorie value) {
        this.categorie = value;
    }

    /**
     * Obtient la valeur de la propriété utilisateur.
     * 
     * @return
     *     possible object is
     *     {@link Utilisateur }
     *     
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Définit la valeur de la propriété utilisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link Utilisateur }
     *     
     */
    public void setUtilisateur(Utilisateur value) {
        this.utilisateur = value;
    }

}
