package com.example;

import java.util.*;

public class ProduitService {
    private Map<Long, Produit> produits = new HashMap<>();

    // Création d'un produit
    public void create(Produit produit) {
        if (produits.containsKey(produit.getId()) || produitExiste(produit.getNom())) {
            throw new IllegalArgumentException("Un produit avec le même ID ou nom existe déjà.");
        }
        if (produit.getPrix() <= 0 || produit.getQuantité() <= 0) {
            throw new IllegalArgumentException("Le prix et la quantité des produits doivent être positifs.");
        }
        produits.put(produit.getId(), produit);
    }

    // Lecture d'un produit
    public Produit read(Long id) {
        if (!produits.containsKey(id)) {
            throw new NoSuchElementException("Le produit recherché n'existe pas.");
        }
        return produits.get(id);
    }

    // Mise à jour d'un produit
    public void update(Produit produit) {
        if (!produits.containsKey(produit.getId())) {
            throw new NoSuchElementException("Le produit recherché pour la mise à jour n'existe pas.");
        }
      
        produits.put(produit.getId(), produit);
    }

    // Suppression d'un produit
    public void delete(Long id) {
        if (!produits.containsKey(id)) {
            throw new NoSuchElementException("Le produit recherché pour la suppression n'existe pas.");
        }
        produits.remove(id);
    }

    // Vérification de l'existence d'un produit par son nom
    private boolean produitExiste(String nom) {
        return produits.values().stream().anyMatch(produit -> produit.getNom().equals(nom));
    }
}
