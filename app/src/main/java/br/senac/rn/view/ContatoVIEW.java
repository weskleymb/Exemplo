package br.senac.rn.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import br.senac.rn.dao.ContatoDAO;
import br.senac.rn.model.Contato;

public class ContatoVIEW extends Activity {

    private EditText etNome, etFone;
    private Button btCadastrar, btLimpar;
    private ListView lvContatos;
    private ArrayAdapter<Contato> adapter;
    private ContatoDAO dao;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.contato_view);
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {

        dao = ContatoDAO.getInstance();

        etNome = (EditText) findViewById(R.id.etNome);
        etFone = (EditText) findViewById(R.id.etFone);

        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btLimpar = (Button) findViewById(R.id.btLimpar);

        lvContatos = (ListView) findViewById(R.id.lvContatos);
        adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, dao.findAll());
        lvContatos.setAdapter(adapter);

    }

    private void definirEventos() {

        lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//              mostrar(adapter.getItem(position).getId());
                Contato contato = adapter.getItem(position);
                enviar(contato);
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar();
            }
        });

    }

    private void mostrar(int id) {
        Contato contato = dao.findById(id);
        String texto = "";
        texto += "Id: " + contato.getId() + "\n";
        texto += "Nome: " + contato.getNome() + "\n";
        texto += "Fone: " + contato.getFone();
        new AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle(contato.getNome())
            .setMessage(texto)
            .create()
            .show();
    }

    private void cadastrar() {
        Contato contato = new Contato(
            dao.findAll().size()+1,
            etNome.getText().toString(),
            etFone.getText().toString()
        );
        dao.insert(contato);
        adapter.notifyDataSetChanged();
        limpar();
    }

    private void limpar() {
        etNome.setText("");
        etFone.setText("");
        etNome.requestFocus();
    }

    private void enviar(Contato contato) {
        Intent intent = new Intent(ContatoVIEW.this, Teste.class);
        intent.putExtra("contato", contato);
        startActivity(intent);
    }

}
