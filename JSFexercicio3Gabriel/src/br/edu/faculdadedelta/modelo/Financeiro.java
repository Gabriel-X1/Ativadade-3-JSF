package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class Financeiro {
private Long id;
private String PacienteDesc;
private String ProcedimentoDesc;
private double valor_procedimento;
private Date DataInicioProcedimento;
private Date DataFimProcedimento;
private int QuantidadeExameProcedimento;

public Financeiro() {
}

public Financeiro(Long id, String pacienteDesc, String procedimentoDesc, double valor_procedimento,
		Date dataInicioProcedimento, Date dataFimProcedimento, int quantidadeExameProcedimento) {
	this.id = id;
	PacienteDesc = pacienteDesc;
	ProcedimentoDesc = procedimentoDesc;
	this.valor_procedimento = valor_procedimento;
	DataInicioProcedimento = dataInicioProcedimento;
	DataFimProcedimento = dataFimProcedimento;
	QuantidadeExameProcedimento = quantidadeExameProcedimento;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getPacienteDesc() {
	return PacienteDesc;
}

public void setPacienteDesc(String pacienteDesc) {
	PacienteDesc = pacienteDesc;
}

public String getProcedimentoDesc() {
	return ProcedimentoDesc;
}

public void setProcedimentoDesc(String procedimentoDesc) {
	ProcedimentoDesc = procedimentoDesc;
}

public double getValor_procedimento() {

	return valor_procedimento;
}

public void setValor_procedimento(double valor_procedimento) {
	this.valor_procedimento = valor_procedimento;
}

public Date getDataInicioProcedimento() {
	return DataInicioProcedimento;
}

public void setDataInicioProcedimento(Date dataInicioProcedimento) {
	DataInicioProcedimento = dataInicioProcedimento;
}

public Date getDataFimProcedimento() {
	return DataFimProcedimento;
}

public void setDataFimProcedimento(Date dataFimProcedimento) {
	DataFimProcedimento = dataFimProcedimento;
}

public int getQuantidadeExameProcedimento() {
	return QuantidadeExameProcedimento;
}

public void setQuantidadeExameProcedimento(int quantidadeExameProcedimento) {
	QuantidadeExameProcedimento = quantidadeExameProcedimento;
}

public double getvalortotal() {
	double desconto = 0;
	double valorTotal = valor_procedimento * QuantidadeExameProcedimento;
			
		Long intervalo = (DataFimProcedimento.getTime() - DataInicioProcedimento.getTime() + 3600000L) / 86400000L ;
		if(intervalo > 2) {
			desconto += valor_procedimento * 0.025;
		}if(valor_procedimento > 2000) {
			desconto += valorTotal * 0.015;
		}
		valorTotal = valorTotal - desconto ;
			
	return valorTotal;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Financeiro other = (Financeiro) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}


}
