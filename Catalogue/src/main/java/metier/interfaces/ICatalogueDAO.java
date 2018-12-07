/**
 * 
 */
package metier.interfaces;

import java.util.List;

import metier.beans.Categorie;
import metier.beans.Produit;

/**
 * Auteur HDN
 * Crée le Nov 21, 2018
 *
 * Cette classe permet de ...

 */
public interface ICatalogueDAO {
	
	public boolean addCategorie(Categorie categorie);
	
	public void addProduit(Produit produit, Long codeCategorie);
	
	public List<Categorie> listCategories();
	
	public List<Produit> produitsParCat(Long codeCat);
	
	public List<Produit> produitsParDesignation(String designation);
	
	public Categorie getCategorie(Long codeCat);
	
	public Produit getProduit(String ref);
	
	public Produit getProduitParDesignation(String nom);
	
	public void updateProduit(Produit p);
	
	public void updateCategorie(Categorie cat);
	
	public void deleteProduit(String ref);
	
	public void deleteCategorie(Long codeCat);
	
	public Categorie getCategorie(String nomCat);

}
