package com.indracompany.stags.dao.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.indracompany.stags.model.ClienteModel;
import com.indracompany.stags.model.ProdutoModel;

public class DataBase {
	static {
		listaCliente = new ArrayList<ClienteModel>();
		mapProduto = new LinkedHashMap<Long, ProdutoModel>();
	}

	private static long sequencialCliente;

	private static long sequencialProduto;
	private static List<ClienteModel> listaCliente;
	private static Map<Long, ProdutoModel> mapProduto;

	public static long getCodigoCliente() {
		return ++sequencialCliente;
	}

	public static long getSequencialCliente() {
		return sequencialCliente;
	}

	public static long getCodigoProduto() {
		return ++sequencialProduto;
	}

	public static long getSequencialProduto() {
		return sequencialProduto;
	}

	public static void setSequencialCliente(long sequencialCliente) {
		DataBase.sequencialCliente = sequencialCliente;
	}

	public static List<ClienteModel> getListaCliente() {
		return listaCliente;
	}

	public static void setSequencialProduto(long sequencialProduto) {
		DataBase.sequencialProduto = sequencialProduto;
	}

	public static Map<Long, ProdutoModel> getMapProduto() {
		return mapProduto;
	}

	public static void setListaCliente(List<ClienteModel> listaCliente) {
		DataBase.listaCliente = listaCliente;
	}

	public static void setListaCliente(Map<Long, ProdutoModel> mapProduto) {
		DataBase.mapProduto = mapProduto;
	}

}
