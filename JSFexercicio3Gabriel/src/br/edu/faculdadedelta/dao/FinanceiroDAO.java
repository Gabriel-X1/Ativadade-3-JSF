package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.Financeiro;
import br.edu.faculdadedelta.util.Conexao;

public class FinanceiroDAO {

	public void incluir (Financeiro financeiro) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
	    Connection conn = conexao.conectarNoBanco();
	    String sql = "INSERT INTO procedimentos ( paciente_desc, procedimento_desc, valor_procedimento, data_inicio_procedimento, data_fim_procedimento, quantidade_exame_procedimento)"
	    		+ "	VALUES ( ?, ?, ?, ?, ?, ?)";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, financeiro.getPacienteDesc());
	    ps.setString(2, financeiro.getProcedimentoDesc());
	    ps.setDouble(3, financeiro.getValor_procedimento());
	    ps.setDate(4, new java.sql.Date(financeiro.getDataInicioProcedimento().getTime()));
	    ps.setDate(5, new java.sql.Date(financeiro.getDataFimProcedimento().getTime()));
	    ps.setInt(6, financeiro.getQuantidadeExameProcedimento());
	    
	    ps.executeUpdate();
	    ps.close();
	    conn.close();
	}
public void alterar(Financeiro financeiro) throws ClassNotFoundException, SQLException {
	Conexao conexao = new Conexao();
    Connection conn = conexao.conectarNoBanco();
    String sql = "UPDATE procedimentos SET paciente_desc = ?, "
    		+ "procedimento_desc = ?,"
    		+ "valor_procedimento = ?,"
    		+ " data_inicio_procedimento = ?, "
    		+ "data_fim_procedimento = ?,"
    		+ " quantidade_exame_procedimento = ?"
    		+ "	WHERE id_procedimento = ? ";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, financeiro.getPacienteDesc());
    ps.setString(2, financeiro.getProcedimentoDesc());
    ps.setDouble(3, financeiro.getValor_procedimento());
    ps.setDate(4, new java.sql.Date(financeiro.getDataInicioProcedimento().getTime()));
    ps.setDate(5, new java.sql.Date(financeiro.getDataFimProcedimento().getTime()));
    ps.setInt(6, financeiro.getQuantidadeExameProcedimento());
    ps.setLong(7, financeiro.getId());
    ps.executeUpdate();
    ps.close();
    conn.close();
	
}
public void excluir(Financeiro financeiro) throws ClassNotFoundException, SQLException {
	Conexao conexao = new Conexao();
    Connection conn = conexao.conectarNoBanco();
    String sql = "DELETE FROM procedimentos WHERE id_procedimento = ? ";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setLong(1, financeiro.getId());
    ps.executeUpdate();
    ps.close();
    conn.close();
}
public List<Financeiro> listar() throws ClassNotFoundException, SQLException{
	Conexao conexao = new Conexao();
    Connection conn = conexao.conectarNoBanco();
    String sql = " SELECT id_procedimento, paciente_desc, procedimento_desc, valor_procedimento, data_inicio_procedimento, data_fim_procedimento, quantidade_exame_procedimento FROM procedimentos";
    PreparedStatement ps = conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    List<Financeiro> listaRetorno = new ArrayList<Financeiro>();
    
    while(rs.next()) {
    	Financeiro financeiro = new Financeiro();
    	financeiro.setId(rs.getLong("id_procedimento"));
    	financeiro.setPacienteDesc(rs.getString("paciente_desc").trim());
    	financeiro.setProcedimentoDesc(rs.getString("procedimento_desc"));
    	financeiro.setValor_procedimento(rs.getDouble("valor_procedimento"));
    	financeiro.setDataInicioProcedimento(rs.getDate("data_inicio_procedimento"));
    	financeiro.setDataFimProcedimento(rs.getDate("data_fim_procedimento"));
    	financeiro.setQuantidadeExameProcedimento(rs.getInt("quantidade_exame_procedimento"));
    	listaRetorno.add(financeiro);
    
   
    }
	rs.close();
	 ps.close();
	 conn.close();
	return listaRetorno; 
}

	 
}
  