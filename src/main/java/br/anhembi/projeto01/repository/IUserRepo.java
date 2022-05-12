package br.anhembi.projeto01.repository;

import org.springframework.data.repository.CrudRepository;

import br.anhembi.projeto01.model.User;

public interface IUserRepo extends CrudRepository <User, Long>{
    public User findByEmail(String email);
    
}
