package professorangoti.com.jogodaforca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] palavras;
    String palavra;
    TextView campo;
    int indiceImagem = 0;
    String letra;
    ArrayList<View> listadeBotoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campo = (TextView) findViewById(R.id.campo);
        palavras = new String[20];
        palavras[0] = "ABACATE";
        palavras[1] = "AZEITE";
        palavras[2] = "AMARELO";
        palavras[3] = "LEITE";
        palavras[4] = "ARROZ";
        palavras[5] = "FEIJOADA";
        palavras[6] = "BICICLETA";
        palavras[7] = "TESTE";
        palavras[8] = "COMPUTADOR";
        palavras[9] = "LIVRO";
        palavras[10] = "MESA";
        palavras[11] = "MOUSE";
        palavras[12] = "LARANJA";
        palavras[13] = "ZICO";
        palavras[14] = "CRUZEIRO";
        palavras[15] = "BRASIL";
        palavras[16] = "INSTITUTO";
        palavras[17] = "FEDERAL";
        palavras[18] = "MINEIRO";
        palavras[19] = "FIM";

        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_main);
        listadeBotoes = layout.getTouchables();

        inicio();
    }

    private void inicio() {
        palavra = palavras[(int) (Math.random() * 20)];
        //palavra = palavras[0];
        StringBuffer underline = new StringBuffer(palavra.length());
        for (int i = 0; i < palavra.length(); i++) {
            underline.append("_");
        }
        campo.setText(underline);

        //Habilita todos os botões
        for (View touchable : listadeBotoes) {
            if (touchable instanceof Button)
                ((Button) touchable).setEnabled(true);
        }
        ((LinearLayout) findViewById(R.id.activity_main)).setBackgroundResource(R.drawable.forca);
        indiceImagem=0;
    }

    public void escolheLetra(View v) {
        Button b = (Button) v;
        b.setEnabled(false);
        letra = b.getText().toString();
        int i = palavra.indexOf(letra);
        if (i == -1) mudarForca();
        else insereLetras(i);
        verificaFim();
    }

    private void verificaFim() {
        String texto = campo.getText().toString();
        if (texto.indexOf("_") == -1) {
            Toast.makeText(this, "Fim de partida.\nVocê ganhou", Toast.LENGTH_LONG).show();
            inicio();
        }
    }

    private void insereLetras(int pos) {
        StringBuffer underline = new StringBuffer(campo.getText());
        for (int i = 0; i < palavra.length(); i++) {
            if (i == pos) underline.replace(i, i + 1, letra);
        }
        campo.setText(underline);
        int i2 = palavra.indexOf(letra, pos + 1);
        //Toast.makeText(this,"pos:"+i2,Toast.LENGTH_LONG).show();
        if (i2 != -1) insereLetras(i2);
    }

    private void mudarForca() {
        switch (indiceImagem) {
            case 0:
                ((LinearLayout) findViewById(R.id.activity_main)).setBackgroundResource(R.drawable.cabeca);
                indiceImagem++;
                break;
            case 1:
                ((LinearLayout) findViewById(R.id.activity_main)).setBackgroundResource(R.drawable.cabecacorpo);
                indiceImagem++;
                break;
            case 2:
                ((LinearLayout) findViewById(R.id.activity_main)).setBackgroundResource(R.drawable.cabecacorpoumbraco);
                indiceImagem++;
                break;
            case 3:
                ((LinearLayout) findViewById(R.id.activity_main)).setBackgroundResource(R.drawable.cabecacorpodoisbracos);
                indiceImagem++;
                break;
            case 4:
                ((LinearLayout) findViewById(R.id.activity_main)).setBackgroundResource(R.drawable.cabecacorpodoisbracosumaperna);
                indiceImagem++;
                break;
            case 5:
                ((LinearLayout) findViewById(R.id.activity_main)).setBackgroundResource(R.drawable.cabecacorpodoisbracosduaspernas);
                Toast.makeText(this, "Fim de partida.\nA palavra era " + palavra, Toast.LENGTH_LONG).show();
                inicio();
                break;
        }
    }
}
