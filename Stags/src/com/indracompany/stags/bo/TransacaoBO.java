package com.indracompany.stags.bo;

import java.util.List;

import com.indracompany.stags.bo.ab.TransacaoBOIf;
import com.indracompany.stags.dao.memory.TransacaoDAOMemory;
import com.indracompany.stags.model.TransacaoModel;

public class TransacaoBO implements TransacaoBOIf{
	private TransacaoDAOMemory transacaoDAO; 
	
	public TransacaoBO(){
		transacaoDAO = new TransacaoDAOMemory();
	}

	@Override
	public void inserir(TransacaoModel pModel) throws Exception {
		try {
			validate(pModel);
			transacaoDAO.inserir(pModel);
		} catch (Exception e) {
			throw new Exception("Erro ao tentar realizar transa��o -> "+e.getMessage());
		}
	}

	@Override
	public List<TransacaoModel> listar() throws Exception {
		try {
			return transacaoDAO.listar();
		} catch (Exception e) {
			throw new Exception("Erro ao tentar listar transa��es -> "+e.getMessage());
		}
	}
	
	@Override
	public void validate(TransacaoModel pModel)throws Exception {
		if(pModel != null){
			try {
				if(pModel.getCliente() == null || pModel.getCliente().equals("")){
					throw new Exception("Cliente � obrigat�rio");
				}
				if(pModel.getListaMidia() == null || pModel.getListaMidia().equals("")){
					throw new Exception("N�o foram estabelecidas m�dias");
				}
				if(pModel.getValorTotal() == null || pModel.getValorTotal().equals("")){
					throw new Exception("N�o foram estabelecidos valores");
				}
			} catch (Exception e) {
				throw new Exception("Erro ao tentar validar uma transa��o -> "+e.getMessage());				
			}
		}
	}

	@Override
	public Double getValorTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValorTotal(Double acumular) {
		// TODO Auto-generated method stub
		
	}
}