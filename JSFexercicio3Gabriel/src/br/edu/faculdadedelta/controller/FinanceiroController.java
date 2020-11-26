package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.FinanceiroDAO;
import br.edu.faculdadedelta.modelo.Financeiro;
@SessionScoped
@ManagedBean
public class FinanceiroController {

	   private Financeiro financeiro = new Financeiro();
	   private FinanceiroDAO dao = new FinanceiroDAO();
	   
	   private Date dataInicial;
	   private Date dataFinal;
	   
	   
	    
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	
	public Financeiro getFinanceiro() {
		return financeiro;
	}
	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}
	  
	public void limparCampos() {
		financeiro = new Financeiro();
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public String Salvar() {
		try 
		{
			if(financeiro.getDataInicioProcedimento().after(new Date())) 
			{
				if(financeiro.getDataFimProcedimento().after(financeiro.getDataInicioProcedimento()))
				{
		            if(financeiro.getId() == null)
		            {
				    dao.incluir(financeiro);
	                FacesMessage mensagem = new FacesMessage("Inclusao realizada com sucesso!!");	
	                FacesContext.getCurrentInstance().addMessage(null, mensagem);
	                limparCampos();
			
		            }
		            else
		            {
			        dao.alterar(financeiro);
			        FacesMessage mensagem = new FacesMessage("Alteracao realizada com sucesso!!");	
	                FacesContext.getCurrentInstance().addMessage(null, mensagem);
	                limparCampos();
		                }
		            }
				else
				{
		            	exibirMensagem("A data inicial deve ser maior que a data final!");
		            }
			}
				else
				{
		            
		             exibirMensagem("A data inicial deve ser maior que a data atual!");
		            }      
			}
		catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
					+ " tente novamente mais tarde" + e.getMessage());	
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
				e.printStackTrace();
			}
		return "CadastroFinanceiro.xhmtl";
	}
	
	public List<Financeiro> getLista(){
		List<Financeiro> listaRetorno = new ArrayList<Financeiro>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
					+ " tente novamente mais tarde" + e.getMessage());	
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		
		return listaRetorno;
	}
	   
	public String editar() {
		return "CadastroFinanceiro.xhtml";
	}
	public String excluir() {
		try {
			dao.excluir(financeiro);
			FacesMessage mensagem = new FacesMessage("Exclusao realizada com sucesso!!");	
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro a realizar operaçao"
					+ " tente novamente mais tarde" + e.getMessage());	
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "ListaFinanceiro.xhtml";
	}
	}