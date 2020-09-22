package com.exemplo.desafio;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name="TB_CONTA")
public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String nome;
	@NotNull
	@NotBlank
	private String conta;
	@NotNull
	@NotBlank
	@Min(11)
	@Max(11)
	private String cpf;
	@NotNull
	private double saldo;

	public Conta(long id, @NotNull String nome, @NotNull @NotBlank String conta, @NotNull @NotBlank @Min(11) @Max(11) String cpf, @NotNull double saldo) {
		this.id = id;
		this.nome = nome;
		this.conta = conta;
		this.cpf = cpf;
		this.saldo = saldo;
	}

	public Conta (){

	}

	public class soma{

	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

}
