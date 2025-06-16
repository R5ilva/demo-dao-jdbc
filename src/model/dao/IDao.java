package model.dao;

import java.util.List;

import model.entity.EntidadeDominio;

public interface IDao {
	void insert(EntidadeDominio obj);
	void update(EntidadeDominio obj);
	void deleteById(Integer id);
	EntidadeDominio findById(Integer id);
	List<EntidadeDominio> findAll();
}
