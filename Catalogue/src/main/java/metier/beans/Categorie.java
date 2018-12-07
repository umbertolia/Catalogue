/**
 * 
 */
package metier.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Auteur HDN
 * Crée le Nov 21, 2018
 *
 * Cette classe permet de ...

 */

@Entity
@Table(name="CATEGORIES")
public class Categorie extends Bean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1240795518550357862L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CODE_CAT")
	private Long codeCategorie;
	
	@NotEmpty
	private String nomCategorie;
	
	@Lob
	private byte[] photo;	
	
	@OneToMany(mappedBy="categorie",fetch=FetchType.EAGER)
	private List<Produit> produits;


	/**
	 * @return the codeCategorie
	 */
	public Long getCodeCategorie() {
		return this.codeCategorie;
	}

	/**
	 * @param codeCategorie the codeCategorie to set
	 */
	public void setCodeCategorie(Long codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	/**
	 * @return the nomCategorie
	 */
	public String getNomCategorie() {
		return this.nomCategorie;
	}

	/**
	 * @param nomCategorie the nomCategorie to set
	 */
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return this.photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the produits
	 */
	public List<Produit> getProduits() {
		return this.produits;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (this.nomCategorie == null) {
			if (other.nomCategorie != null)
				return false;
		} else if (!this.nomCategorie.equals(other.nomCategorie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nCategorie "+this.getNomCategorie());
		sb.append("\nCode "+this.getCodeCategorie());
		if (this.getProduits() != null) {
			if (this.getProduits().isEmpty()) {
				sb.append("\nAucun Produit");
			}
			else {
				sb.append("\nProduits : ");
				for (Produit prod : this.getProduits()) {
					sb.append(prod);
				}
			}
		}
		return sb.toString();
	}
	
}

