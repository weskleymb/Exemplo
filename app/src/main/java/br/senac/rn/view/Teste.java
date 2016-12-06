package br.senac.rn.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.senac.rn.model.Contato;

public class Teste extends Activity {

    private TextView tvId, tvNome, tvFone;
    private Button btVoltar;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        tvId = (TextView) findViewById(R.id.tvId);
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvFone = (TextView) findViewById(R.id.tvFone);
        btVoltar = (Button) findViewById(R.id.btVoltar);

        intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");

        tvId.setText(Integer.toString(contato.getId()));
        tvNome.setText(contato.getNome());
        tvFone.setText(contato.getFone());
    }

    private void definirEventos() {
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Teste.this, ContatoVIEW.class));
            }
        });
    }

}
