package br.anhembi.projeto01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //indica que esta classe será persistida no DB
@Table(name = "user") // nome da table no DB referente a este entidade
public class User {

    @Id // Indica que este atributo será chave primária na table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Será gerado sequencialmente 1, 2, 3 ...
    private long code;

    @Column(name = "nome", length = 200, nullable = false)
    private String name;
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "senha", length = 30, nullable = false)
    private String password;

    public long getCode() {
        return code;
    }
    public void setCode(long code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    

}
