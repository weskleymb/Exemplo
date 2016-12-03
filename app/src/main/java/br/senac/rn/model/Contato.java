package br.senac.rn.model;

public class Contato implements Comparable<Contato> {

    private int id;
    private String nome;
    private String fone;

    public Contato() {}

    public Contato(int id, String nome, String fone) {
        this.id = id;
        this.nome = nome;
        this.fone = fone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return id == contato.id;
    }

    @Override
    public int compareTo(Contato contato) {
        return (id < contato.getId() ? -1 : (id > contato.getId() ? +1 : 0));
    }

    @Override
    public String toString() {
        return nome + "\n" + fone;
    }
}
