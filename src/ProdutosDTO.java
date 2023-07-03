/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class ProdutosDTO {

    private int id;
    private String nome;
    private int valor;
    private String status;

    public ProdutosDTO() {

    }

    public ProdutosDTO(int id, String nome, int valor, String status) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
