package aplicacion.cano.ignacio.proyecto;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
   private TextView textView;
   private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView=(TextView) findViewById(R.id.textViewMain2);
        btnNext=(Button) findViewById(R.id.buttonSharing);

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null && bundle.getString("saludo")!=null){
            String saludo=bundle.getString("saludo");
            Toast.makeText(SecondActivity.this,saludo,Toast.LENGTH_SHORT).show();
            textView.setText(saludo);
        }else{
            Toast.makeText(SecondActivity.this,"esta vacio ",Toast.LENGTH_SHORT).show();
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent(SecondActivity.this,ThirdActiviry.class);
                startActivity(intent2);
            }
        });


    }
}
