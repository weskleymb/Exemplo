package br.senac.rn.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import br.senac.rn.dao.ContatoDAO;
import br.senac.rn.model.Contato;

public class ContatoVIEW extends AppCompatActivity {

    private TextView tvNome, tvFone;
    private EditText etNome, etFone;
    private Button btCadastrar, btLimpar;
    private ListView lvContatos;
    private ArrayAdapter<Contato> adapter;
    private ContatoDAO dao = new ContatoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contato_view);
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvFone = (TextView) findViewById(R.id.tvFone);

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
                display(adapter.getItem(position).getId());
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar(view);
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar(view);
            }
        });

    }

    private void display(int id) {
        Contato contato = dao.findById(id);
        String texto = "Id:\t\t\t\t\t" + contato.getId() + "\n";
        texto += "Nome:\t" + contato.getNome() + "\n";
        texto += "Fone:\t\t" + contato.getFone();
        new AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle(contato.getNome())
            .setMessage(texto)
            .create()
            .show();
    }

    private void cadastrar(View view) {
        Contato contato = new Contato(
            dao.findAll().size()+1,
            etNome.getText().toString(),
            etFone.getText().toString()
        );
        dao.insert(contato);
        adapter.notifyDataSetChanged();
        limpar(view);
    }

    private void limpar(View view) {
        etNome.setText("");
        etFone.setText("");
        etNome.requestFocus();
    }

}
