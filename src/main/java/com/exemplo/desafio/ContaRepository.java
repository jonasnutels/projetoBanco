package com.exemplo.desafio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ContaRepository extends JpaRepository<Conta, Long>{
    Conta findById(long id);
    @Query(value ="SELECT * FROM TB_CONTA WHERE CONTA =:numConta", nativeQuery = true)
    public String findByConta(@Param("numConta") String numConta);

}
