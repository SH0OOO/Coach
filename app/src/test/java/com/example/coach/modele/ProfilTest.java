package com.example.coach.modele;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfilTest {
    private Profil profil = new Profil(0,67,165,35);
    // résultat de l’img correspondant
    private float img = (float)32.18 ;
    // message correspondant
    private String message = "trop élevé" ;
    @Test
    public void getImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    @Test
    public void getMessage() {
        assertEquals(message, profil.getMessage());
    }
}