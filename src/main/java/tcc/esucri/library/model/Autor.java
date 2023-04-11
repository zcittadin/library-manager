package tcc.esucri.library.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTOR")
public class Autor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 695252792303117841L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private int id;
	@Column(name = "NOME", length = 100, nullable = false)
	private String nome;
	@OneToMany(mappedBy = "autor", cascade = CascadeType.REMOVE)
	private List<Livro> livros;

	public Autor() {

	}

	public Autor(int id, String nome, List<Livro> livros) {
		this.id = id;
		this.nome = nome.toUpperCase();
		this.livros = livros;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome.toUpperCase();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setRegistros(List<Livro> livros) {
		this.livros = livros;
	}

	@Override
	public String toString() {
		return nome.toUpperCase();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return id == other.id;
	}

}
