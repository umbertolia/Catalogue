/**
 * 
 */
package metier.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Auteur HDN Crée le Nov 21, 2018
 *
 * Cette classe permet de ...
 * 
 */

@Entity
@Table(name="PRODUITS")
public class Produit extends Bean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8503052713386704840L;

	@Id
	@Column(name="REF")
	@NotEmpty @Size(min=4,max=12)
	private String reference;

	@Column()
	private String designation;

	@DecimalMin(value="10")
	private double prix;

	@Min(1)
	private int quantite;

	private boolean disponible;

	@ManyToOne @JoinColumn(name="CODE_CAT")
	private Categorie categorie;

	public Produit() {
		
	}
	
	public Produit(String reference, String nomProduit, double prix, int quantite) {
		this.reference = reference;
		this.designation = nomProduit;
		this.prix = prix;
		this.quantite = quantite;
		this.disponible = true;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return this.reference;
	}

	/**
	 * @param reference
	 *           the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return this.designation;
	}

	/**
	 * @param designation
	 *           the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return this.prix;
	}

	/**
	 * @param prix
	 *           the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return this.quantite;
	}

	/**
	 * @param quantite
	 *           the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	/**
	 * @return the disponible
	 */
	public boolean isDisponible() {
		return this.disponible;
	}

	/**
	 * @param disponible the disponible to set
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return this.categorie;
	}

	/**
	 * @param categorie
	 *           the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (this.reference == null) {
			if (other.reference != null)
				return false;
		} else if (!this.reference.equals(other.reference)) {
			if (this.designation == null) {
				if (other.designation != null)
					return false;
			} else if (!this.designation.equals(other.designation))
				return false;
		}
		return true;
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n"+this.getDesignation());
		sb.append("\nREFERENCE : "+this.getReference());
		sb.append("\nPrix : "+this.getPrix());
		sb.append("\nQuantite : "+this.getQuantite()+ "\n");
		
		return sb.toString();
	}
}
