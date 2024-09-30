package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    private static AccesLocal instance;
    //propriétés
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * constructeur
     * @param contexte
     */
    private AccesLocal(Context contexte) {
        accesBD = new MySQLiteOpenHelper(contexte,nomBase,versionBase);
    }

    public static AccesLocal getInstance(Context contexte){
        if(instance == null){
            instance = new AccesLocal(contexte);
        }
        return instance;
    }

    /**
     * ajout d'un profil dans la base de données
     * @param profil
     */
    public void ajout (Profil profil){
        bd = accesBD.getWritableDatabase();
        String req = "insert into profil (datemesure, poids, taille, age, sexe) values ";
        req += "(\"" + profil.getDateMesure() + "\","+ profil.getPoids() + "," + profil.
                getTaille() + "," + profil.getAge() + "," + profil.getSexe() + ")";
        bd.execSQL(req);
        ContentValues values = new ContentValues();

        values.put("poids",profil.getPoids());
        values.put("taille",profil.getTaille());
        values.put("age",profil.getAge());
        values.put("sexe",profil.getSexe());
        values.put("datemesure",profil.getDateMesure().toString());
         bd.insert("profil",null,values);
         bd.close();

    }

    public Profil recupDernier(){
        Profil profil = null;
        bd= accesBD.getReadableDatabase();
        String req = "select * from profil";
        Cursor curseur = bd.rawQuery(req,null);
        curseur.moveToLast();
        if (!curseur.isAfterLast()){
            Date datemesure = MesOutils.convertStringToDate(curseur.getString(0));
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(poids,taille,age,sexe,datemesure);
        }
        curseur.close();
        bd.close();
        return profil;
    }
}
