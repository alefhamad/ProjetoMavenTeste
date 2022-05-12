package br.anhembi.projeto01.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.projeto01.model.User;
import br.anhembi.projeto01.repository.IUserRepo;

@RestController // Esta tag define a classe como uma classe de controle REST
@CrossOrigin("*") // aceita requisições de qualquer origem (server)
@RequestMapping("/user") // Tuda requisição chegada no /user virá para esse controller
public class UserController {

    @Autowired // impolementa a interface com os métodos e reotrna um objeto
    private IUserRepo repo;

    @GetMapping
    public ArrayList<User> listUsers() {
        ArrayList<User> listUsers = (ArrayList<User>) repo.findAll();
        return listUsers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable long id) {
        // procura um usuário e se não encontrar retorna null
        User userFound = repo.findById(id).orElse(null);

        if (userFound != null) {
            return ResponseEntity.ok(userFound); // ok = status 200;
        }
        return ResponseEntity.notFound().build(); // notFound = status 404
    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user) {

        User newUser = repo.save(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping
    public ResponseEntity<User> updateSUser(@RequestBody User user) {
        
        User userFound = repo.findById(user.getCode()).orElse(null);

        if(userFound == null){
            return ResponseEntity.notFound().build();
        }
        User newUser = repo.save(user);
        
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        
        User userFound = repo.findById(id).orElse(null);

        if(userFound == null){
            return ResponseEntity.notFound().build();
        }
       repo.deleteById(id);

       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/email")
    public ResponseEntity<User> findUserUsingEmail(@RequestBody User user){
        User userFound = repo.findByEmail(user.getEmail());

        if(userFound == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userFound);
    }
}