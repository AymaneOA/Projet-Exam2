Package com.example;
import java.util.List;
import java.util.stream.Collectors;

public class ProduitService {
    private List<Produit> produits;

    public ProduitService(List<Produit> produits) {
        this.produits = produits;
    }

    public void createProduit(Produit produit) {
        if (!checkUnicity(produit.getId(), produit.getNom())) {
            if (validateData(produit.getPrix(), produit.getQuantite())) {
                produits.add(produit);
            } else {
                throw new IllegalArgumentException("Prix et quantité doivent être positifs.");
            }
        } else {
            throw new IllegalArgumentException("Un produit avec le même ID ou nom existe déjà.");
        }
    }

    public Produit readProduit(Long id) {
        return produits.stream()
                .filter(produit -> produit.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'ID : " + id));
    }

    public void updateProduit(Produit produit) {
        Produit existingProduit = readProduit(produit.getId());
        if (validateData(produit.getPrix(), produit.getQuantite())) {
            existingProduit.setNom(produit.getNom());
            existingProduit.setPrix(produit.getPrix());
            existingProduit.setQuantite(produit.getQuantite());
        } else {
            throw new IllegalArgumentException("Prix et quantité doivent être positifs.");
        }
    }

    public void deleteProduit(Long id) {
        produits.removeIf(produit -> produit.getId().equals(id));
    }

    public boolean checkUnicity(Long id, String nom) {
        return produits.stream().anyMatch(produit -> produit.getId().equals(id) || produit.getNom().equals(nom));
    }

    public boolean validateData(double prix, int quantite) {
        if (prix < 0 || quantite < 0) {
            throw new IllegalArgumentException("Prix et quantité doivent être positifs.");
        }
        return true;
    }
}
