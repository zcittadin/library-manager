package tcc.esucri.library.dao;

import java.util.List;

import tcc.esucri.library.model.Autor;

public class AutorDAO extends DAO<Autor> {

	public List<Autor> getAll() {
		return super.getAll("select au from Autor au order by au.id desc");
	}

}
