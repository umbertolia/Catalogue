import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import metier.beans.Categorie;
import metier.beans.Produit;
import metier.impl.CatalogueImpl;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;



/**
 * Auteur HDN Crée le Nov 21, 2018
 *
 * Cette classe permet de ...
 * 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogueServiceTest {

	private EntityManagerFactory entityManagerFactory;
	
	private EntityManager entityManager;
	
	private CatalogueImpl dao;	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("CATALOG");
			entityManager = entityManagerFactory.createEntityManager();
			dao = new CatalogueImpl();
			dao.setEm(entityManager);
			assertTrue("connection a la base OK via EntityManager", true);		} 
		catch (Exception e) {
			fail("connection a la base KO via EntityManager");
		}
		
	}

	//@Ignore("TEST OK")
	@Test
	public void test1_JdbcConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/catalogue","root","admin");  
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("show tables from catalogue");
			assertTrue("connection a la base OK via DriverManager", true);
		} 
		catch (Exception exception) {
			fail("connection a la base KO via DriverManager");
		}		
	}
	
	@Ignore("TEST OK")
	@Test
	public void test2_Categories() {
		try {
			dao.getEm().getTransaction().begin();
			
			Categorie categorie = new Categorie();
			categorie.setNomCategorie("Boissssons");
			dao.addCategorie(categorie);
			
			categorie = new Categorie();
			categorie.setNomCategorie("Legumes");
			dao.addCategorie(categorie);
			
			categorie = new Categorie();
			categorie.setNomCategorie("Fruits");
			dao.addCategorie(categorie);
			
			categorie = new Categorie();
			categorie.setNomCategorie("Mauvaise Categorie");
			dao.addCategorie(categorie);
	
			dao.getEm().getTransaction().commit();
			assertTrue("objets insérés dans la base", true);
			
			dao.getEm().getTransaction().begin();
			dao.deleteCategorie(4L);
			
			categorie = dao.getCategorie("Boissssons");
			if (categorie != null) {
				categorie.setNomCategorie("Boissons");
				dao.updateCategorie(categorie);
			}
			
			dao.getEm().getTransaction().commit();
		}
		catch (Exception exception) {
			dao.getEm().getTransaction().rollback();
			fail(exception.getMessage());
			exception.printStackTrace();
		}
	}

	
	@Ignore("TEST OK")
	@Test
	public void test3_Produits() {
		try {
			dao.getEm().getTransaction().begin();
			
			//boissons
			Produit produit = new Produit("B145", "Eau", 1.70, 55);
			dao.addProduit(produit, 1L);
			produit = new Produit("B475RT", "Lait", 0.65, 15);
			dao.addProduit(produit, 1L);
			produit = new Produit("B875HN", "Vin", 2.20, 30);
			dao.addProduit(produit, 1L);
			produit = new Produit("B985DE", "Jus d'Orange", 1.05, 100);
			dao.addProduit(produit, 1L);
			produit = new Produit("B121DR", "Coca", 1.50, 14);
			dao.addProduit(produit, 1L);
			// legumes
			produit = new Produit("LCA14", "Carottes", 0.95, 145);
			dao.addProduit(produit, 2L);
			produit = new Produit("LBR78", "Brocolis", 2.21, 8);
			dao.addProduit(produit, 2L);
			produit = new Produit("LCO78", "Choux", 4.10, 48);
			dao.addProduit(produit, 2L);
			// fruits
			produit = new Produit("FPO78S", "Pommes", 1.55, 1545);
			dao.addProduit(produit, 3L);
			produit = new Produit("FOR78N", "Oranges", 1.15, 48);
			dao.addProduit(produit, 3L);
			produit = new Produit("FKI78V", "Kiwis", 0.45, 22);
			dao.addProduit(produit, 3L);
			produit = new Produit("FBA87Q", "Bananes", 1.30, 485);
			dao.addProduit(produit, 3L);
			produit = new Produit("FAN756C", "Ananas", 1.95, 88);
			dao.addProduit(produit, 3L);
			produit = new Produit("FPA4Y5", "Pasteque", 5.55, 11);
			dao.addProduit(produit, 3L);
			
			dao.getEm().getTransaction().commit();
			assertTrue("produits insérés en base", dao.produitsParCat(3L).size()==6);
			
			// DELETE PROD et UPDATE PROD
			dao.getEm().getTransaction().begin();
			
			dao.deleteProduit("B121DR");
			produit = dao.getProduitParDesignation("Pommes");
			if (produit != null) {
				produit.setPrix(2.15);
				dao.updateProduit(produit);
			}			
			produit = dao.getProduitParDesignation("existe pas");
			
			dao.getEm().getTransaction().commit();
			
			//  PRODUITS d'UNE CAT
			List<Produit> produits = dao.produitsParCat(3L);
			
			System.out.println("CATEGORIE FRUITS\n");
			System.out.println(produits);		
			
			// PROD CONTENANT ...
			String chaine = "an";
			List produitsParDes = dao.produitsParDesignation(chaine);
			System.out.println("Produits contenant la chaine : "+chaine);
			System.out.println(produitsParDes);		
			
			
		}
		catch (Exception exception) {
			dao.getEm().getTransaction().rollback();
			fail(exception.getMessage());
			exception.printStackTrace();
		}
	}
	
	@Ignore("TEST OK")
	@Test
	public void test4_supprCategorieAvecProduits() {
		Categorie categorie = new Categorie();
		categorie.setCodeCategorie(1L);
		categorie.setNomCategorie("Temp");
		try {
			dao.getEm().getTransaction().begin();
			dao.addCategorie(categorie);
			dao.getEm().getTransaction().commit();
		}
		catch (Exception exception) {
			dao.getEm().getTransaction().rollback();
			System.out.println("Impossible d'ajouter la categorie "+categorie.getNomCategorie());	
			
			dao.getEm().getTransaction().begin();
			categorie.setCodeCategorie(4L);
			dao.addCategorie(categorie);
			
			if (dao.getEm().contains(categorie)) {
				Produit produit = new Produit("PRTEMP1", "PRTEMP1", 1.70, 55);
				dao.addProduit(produit, 4L);
				produit = new Produit("PRTEMP2", "PRTEMP2", 0.65, 15);
				dao.addProduit(produit, 4L);
			}
			try {
				dao.getEm().getTransaction().commit();
			}
			catch (Exception exception2) {
				dao.getEm().getTransaction().rollback();
				fail(exception.getMessage());
			}
		}
		
		// suppr de la categorie avec produits
		Categorie cat = dao.getCategorie(4L);
		System.out.println(cat);
		
		try {
			dao.getEm().getTransaction().begin();
			dao.deleteCategorie(4L);
			dao.getEm().getTransaction().commit();
		}
		catch (PersistenceException persistenceException) {
			System.out.println("Impossible de supprimer la categorie "+cat);
		}
	}
}
