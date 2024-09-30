package com.example.coach.controleur;

import android.content.Context;
import java.util.Date;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {

    private static Controle instance;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    private static AccesLocal  accesLocal;


    private Controle(Context contexte) {

        accesLocal = AccesLocal.getInstance(contexte);
        profil = accesLocal.recupDernier();
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(Context contexte) {
        if(instance == null){
            instance = new Controle(contexte);


            //recupSerialize(contexte);
        }
        return instance;
    }

    /**
     * Création du profil par rapport aux informations saisies
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte) {
        profil = new Profil(poids, taille, age, sexe,new Date());
        accesLocal.ajout(profil);
        //Serializer.serialize(nomFic,profil, contexte);
    }

    /**
     * getter sur le résultat du calcul de l'IMG pour le profil
     * @return img du profil
     */
    public float getImg() {
        if(profil != null) {
            return profil.getImg();
        }else{
            return 0;
        }
    }

    /**
     * getter sur le message correspondant à l'img du profil
     * @return message du profil
     */
    public String getMessage() {
        if(profil != null) {
            return profil.getMessage();
        }else{
            return "";
        }
    }

    /**
     * recuperation de l'objet serialiser(le profil)
     * @param contexte
     */
    /**
    private static void recupSerialize(Context contexte){
        profil = (Profil)Serializer.deSerialize(nomFic,contexte);
    }
    */


    public Integer getPoids(){
        if (profil == null){
            return null;
        }else {
            return profil.getPoids();
        }

    }
    public Integer getTaille() {
        if (profil == null) {
            return null;
        } else {
            return profil.getTaille();
        }
    }
    public Integer getAge() {
        if (profil == null) {
            return null;
        } else {
            return profil.getAge();
        }
    }
    public Integer getSexe() {
        if (profil == null) {
            return null;
        } else {
            return profil.getSexe();
        }
    }

}