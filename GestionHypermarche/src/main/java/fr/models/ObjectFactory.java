//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.02.16 à 01:17:56 AM CET 
//


package fr.models;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.models package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.models
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Articles }
     * 
     */
    public Articles createArticles() {
        return new Articles();
    }

    /**
     * Create an instance of {@link Article }
     * 
     */
    public Article createArticle() {
        return new Article();
    }

    /**
     * Create an instance of {@link Utilisateurs }
     * 
     */
    public Utilisateurs createUtilisateurs() {
        return new Utilisateurs();
    }

    /**
     * Create an instance of {@link Utilisateur }
     * 
     */
    public Utilisateur createUtilisateur() {
        return new Utilisateur();
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link Categories }
     * 
     */
    public Categories createCategories() {
        return new Categories();
    }

    /**
     * Create an instance of {@link Categorie }
     * 
     */
    public Categorie createCategorie() {
        return new Categorie();
    }

    /**
     * Create an instance of {@link Rayons }
     * 
     */
    public Rayons createRayons() {
        return new Rayons();
    }

    /**
     * Create an instance of {@link Rayon }
     * 
     */
    public Rayon createRayon() {
        return new Rayon();
    }

}
