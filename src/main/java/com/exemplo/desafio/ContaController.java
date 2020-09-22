package com.exemplo.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class ContaController {

    @Autowired
    ContaRepository contaRepository;

    //LISTAR TODAS AS CONTAS CRIADAS
    @GetMapping("/contas")
    public List<Conta> listaProdutos() {
        return contaRepository.findAll();
    }

    //LISTAR POR ID
    @GetMapping("/contas/{id}")
    public Optional<Conta> listaConta(@PathVariable(value = "id") Long id) {
        return contaRepository.findById(id);
    }

    //CRIAR CONTA
    @PostMapping("/conta")
    public Conta salvaConta(@RequestBody @Valid Conta conta) {
        return contaRepository.save(conta);
    }

    //EDITAR CONTA
    @PutMapping("/conta/{id}")
    public Conta salvaConta(@PathVariable (value ="id") Long id,
                            @RequestBody @Valid Conta conta) {
        return contaRepository.save(conta);
    }

    //DEPOSITAR EM CONTA
    @PutMapping("/conta/deposito")
    public String deposito(@RequestParam(value = "id") long id,
                           @RequestParam(value = "valor") double valor) {
        Conta contaDeposito = contaRepository.findById(id);
        double saldoInicial = contaDeposito.getSaldo();
        double novoSaldo = saldoInicial + valor;
        contaDeposito.setSaldo(novoSaldo);
        contaRepository.save(contaDeposito);
        return "Saldo anterior é: " + saldoInicial + " \n Seu novo Saldo é: " + novoSaldo;
    }

    //SAQUE DE CONTA
    @PutMapping("/conta/saque")
    public String saque(@RequestParam(value = "id") long id,
                        @RequestParam(value = "valor") double valor) {
        Conta contaSaque = contaRepository.findById(id);
        double saldoIncial = contaSaque.getSaldo();
        if (valor > 500) {
            return "Operação de transferência tem um limite máximo de 500 por operação." +
                    "\n O Saldo da conta " + contaSaque.getConta() + " é de " + saldoIncial;
        }
        if (saldoIncial < valor) {
            return "saldo insuficiente";
        }
        if (saldoIncial >= valor) {
            double novoSaldo = saldoIncial - valor;
            contaSaque.setSaldo(novoSaldo);
            contaRepository.save(contaSaque);
            return "Saque realizado com sucesso!\n" + "O Novo saldo da conta: " + contaSaque.getConta() + " é de R$" + novoSaldo;
        }
        return "Operação finalizada";
    }

    @PutMapping("/trans")
    public String trans(@RequestParam(value = "idOrigem") long idOrigem,
                        @RequestParam(value = "idDestino") long idDestino,
                        @RequestParam(value = "valor") double valor) {
        Conta contaOrigem = contaRepository.findById(idOrigem);
        double saldoInicialOrigem = contaOrigem.getSaldo();
        Conta contaDestino = contaRepository.findById(idDestino);
        double saldoInicialDestino = contaDestino.getSaldo();
        if (valor > 500) {
            return "Operação de transferência tem um limite máximo de 500 por operação.";
        }
        if (valor > saldoInicialOrigem) {
            return "Saldo insuficiente para a operação.";
        }
        if (saldoInicialOrigem >= valor) {
            double novoSaldoOrigem = saldoInicialOrigem - valor;
            contaOrigem.setSaldo(novoSaldoOrigem);
            contaRepository.save(contaOrigem);
            double novoSaldoDestino = saldoInicialDestino + valor;
            contaDestino.setSaldo(novoSaldoDestino);
            contaRepository.save(contaDestino);
            return "Transferencia concluída" + "\n O Saldo da conta " + contaOrigem.getConta() + " é de "
                    + "R$" + contaOrigem.getSaldo() + "\n O Saldo da conta " + contaDestino.getConta() + " é de R$" +
                    contaDestino.getSaldo();
        }
        return "salvo";
    }
}
//Jonas Nutels Junior