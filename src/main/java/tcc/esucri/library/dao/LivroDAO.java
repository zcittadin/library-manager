package tcc.esucri.library.dao;

import java.util.List;

import tcc.esucri.library.model.Autor;
import tcc.esucri.library.model.Livro;

public class LivroDAO extends DAO<Livro> {

	public List<Livro> getAll() {
		return super.getAll("select l from Livro l order by l.id desc");
	}

	public List<Livro> getByAutor(Autor autor) {
		return super.getAll("select l from Livro l where l.autor = ?1 order by l.id desc", autor);
	}

}
