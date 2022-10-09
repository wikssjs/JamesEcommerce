package com.ebookfrenzy.projetibj;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Produit implements Comparable<Produit> , Serializable {
    private String nom;
    private String description;
    private String image;
    private double prix;
    private int quantite;
    private String drawable;
    public static ArrayList<Produit> liste = new ArrayList<>();

    private static Context contexte;
    public Produit() {
    }


    @Override
    public int compareTo(Produit o) {
        return 0;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Accesseur de l'attribut drawable.
    public String getDrawable() {
        return drawable;
    }

    // Mutateur de l'attribut drawable.
    public void setdDrawable(String drawable) {
        this.drawable = drawable;
    }

    // Fonction permettant d'insérer l'image dans un
    // ImageView fourni. L'image doit être dans res/drawable.
    public void intoImageView(ImageView iv) {
        if (  this.getDrawable() != null ) {
            String uri = "@drawable/" + this.getDrawable().toLowerCase();

            int imageResource = contexte.getResources().getIdentifier(uri, null, contexte.getPackageName());

            Drawable res = contexte.getDrawable(imageResource);

            iv.setImageDrawable(res);
        }
    }

    // Retourne une chaîne décrivant l'élément chimique.
    @Override
    public String toString() {
        return "" + this.getNom() + " - " + this.getNom();
    }

   /* public static ArrayList<Produit> lireDonnées(Context ctx, String nomFichier) {
        // Sauvegarder le contexte pour la gestion des drawables.
        contexte = ctx;
        try {
            // Charger les données dans la liste.
            String jsonString = lireJson(nomFichier, ctx);
            JSONObject json   = new JSONObject(jsonString);
            JSONArray élément    = json.getJSONArray("elements");

            // Lire chaque élément du fichier.
            for(int i = 0; i < élément.length(); i++){
                Produit p = new Produit();

                p.nom     = élément.getJSONObject(i).getString("nom");
                p.image = élément.getJSONObject(i).getString("image");
                p.prix = élément.getJSONObject(i).getDouble("prix");

                liste.add(p);
            }
        } catch (JSONException e) {
            // Une erreur s'est produite (on la journalise).
            e.printStackTrace();
            return null;
        }

        // Trier la liste.
        Collections.sort(liste);

        return liste;
    }*/

    // Retourne une balise lue d'un fichier JSON.
    private static String lireJson(String nomFichier, Context ctx) {
        String json = null;

        try {
            InputStream is = ctx.getAssets().open(nomFichier);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            // Une erreur s'est produite (on la journalise).
            ex.printStackTrace();
            return null;
        }

        return json;
    }




}
