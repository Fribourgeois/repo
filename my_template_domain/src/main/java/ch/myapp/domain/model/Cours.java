package ch.myapp.domain.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cours")
public class Cours implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	private Set<Eleve> eleves;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cours [cours=" + name + ", id =" + id + "]";
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "eleve_cours", joinColumns = @JoinColumn(name = "cours_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "eleve_id", referencedColumnName = "id"))
	public Set<Eleve> getEleves() {
		return eleves;
	}

	public void setEleves(Set<Eleve> elevesS) {
		this.eleves = eleves;
	}

}