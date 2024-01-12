package com.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class ProduitServiceTest {
    private ProduitService produitService;
    private Produit produit;

    @BeforeEach
    public void setUp() {
        produitService = new ProduitService();
        produit = new Produit(1L, "Produit1", 100.0, 10);
        produitService.create(produit);
    }

    @Test
    public void testCreate() {
        assertEquals(produit, produitService.read(1L));
    }

    @Test
    public void testUpdate() {
        Produit updatedProduit = new Produit(1L, "Produit1", 200.0, 20);
        produitService.update(updatedProduit);
        assertEquals(updatedProduit, produitService.read(1L));
    }

    @Test
    public void testDelete() {
        produitService.delete(1L);
        assertThrows(NoSuchElementException.class, () -> produitService.read(1L));
    }

    @Test
    public void testGetId() {
        assertEquals(1L, produit.getId());
    }

    @Test
    public void testSetId() {
        produit.setId(2L);
        assertEquals(2L, produit.getId());
    }

    @Test
    public void testGetNom() {
        assertEquals("Produit1", produit.getNom());
    }

    @Test
    public void testSetNom() {
        produit.setNom("Produit2");
        assertEquals("Produit2", produit.getNom());
    }

    @Test
    public void testGetPrix() {
        assertEquals(100.0, produit.getPrix());
    }

    @Test
    public void testSetPrix() {
        produit.setPrix(200.0);
        assertEquals(200.0, produit.getPrix());
    }

    @Test
    public void testGetQuantité() {
        assertEquals(10, produit.getQuantité());
    }

    @Test
    public void testSetQuantité() {
        produit.setQuantité(20);
        assertEquals(20, produit.getQuantité());
    }
    @Test
    public void testCreateWithExistingId() {
        Produit produit2 = new Produit(1L, "Produit2", 200.0, 20);
        assertThrows(IllegalArgumentException.class, () -> produitService.create(produit2));
    }

    @Test
    public void testCreateWithExistingNom() {
        Produit produit2 = new Produit(2L, "Produit1", 200.0, 20);
        assertThrows(IllegalArgumentException.class, () -> produitService.create(produit2));
    }

    

    @Test
    public void testCreateWithZeroQuantité() {
        Produit produit2 = new Produit(2L, "Produit2", 200.0, 0);
        assertThrows(IllegalArgumentException.class, () -> produitService.create(produit2));
    }

    @Test
    public void testUpdateNonExistentProduit() {
        Produit produit2 = new Produit(2L, "Produit2", 200.0, 20);
        assertThrows(NoSuchElementException.class, () -> produitService.update(produit2));
    }

    @Test
    public void testDeleteNonExistentProduit() {
        assertThrows(NoSuchElementException.class, () -> produitService.delete(2L));
    }
    @Test
    public void testCreateWithNegativePrix() {
        Produit produit2 = new Produit(2L, "Produit2", -100.0, 20);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> produitService.create(produit2));
        String expectedMessage = "Le prix et la quantité des produits doivent être positifs.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    
}
