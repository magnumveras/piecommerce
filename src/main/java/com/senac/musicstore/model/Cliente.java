package com.senac.musicstore.model;

public class Cliente {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String sexo;
    private String cpf;
    private String rg;
    private String idade;
    private String datanascimento;
    private String telefone;
    private String email;
    private String bairro;
    private String cep;
    private String complemento;
    private String endereco;
    private String numero;
    private String cidade;
    private String estado;
    private int codigousuario; 
    private boolean ofertas;

    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome, String sobrenome, String sexo, String cpf, String rg, String idade, String datanascimento, String telefone, String email, String bairro, String cep, String complemento, String endereco, String numero, String cidade, String estado, int codigousuario, boolean ofertas) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.rg = rg;
        this.idade = idade;
        this.datanascimento = datanascimento;
        this.telefone = telefone;
        this.email = email;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.endereco = endereco;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.codigousuario = codigousuario;
        this.ofertas = ofertas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(int codigousuario) {
        this.codigousuario = codigousuario;
    }

    public boolean getOfertas() {
        return ofertas;
    }

    public void setOfertas(boolean ofertas) {
        this.ofertas = ofertas;
    }
   
}
