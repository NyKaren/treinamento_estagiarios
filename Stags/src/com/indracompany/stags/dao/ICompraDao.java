package com.indracompany.stags.dao;

import java.util.List;

import com.indracompany.stags.model.CompraModel;

public interface ICompraDao {

	public void iserir(CompraModel compra);

	public CompraModel buscar(Long cod);

	public List<CompraModel> listar();

}