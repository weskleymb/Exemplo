package br.senac.rn.dao;

import java.util.ArrayList;
import java.util.List;

import br.senac.rn.model.Contato;

public class ContatoDAO {

    private List<Contato> contatos;
    private static ContatoDAO singleton;

    public ContatoDAO() {
        this.contatos = new ArrayList();
    }

    public static ContatoDAO getInstance() {
        if (singleton == null) {
            singleton = new ContatoDAO();
        }
        return singleton;
    }

    public void insert(Contato contato) {
        contatos.add(contato);
    }

    public void update(Contato contato) {
        Contato c = findById(contato.getId());
        if (c != null) {
            c = contato;
        }
    }

    public void delete(Contato contato) {
        Contato c = findById(contato.getId());
        if (c != null) {
            contatos.remove(c);
        }
    }

    public Contato findById(int id) {
        for (Contato contato : contatos) {
            if (contato.getId() == id) {
                return contato;
            }
        }
        return null;
    }

    public List<Contato> findAll() {
        return contatos;
    }

}
