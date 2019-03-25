package aplicacion.cano.ignacio.proyecto;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActiviry extends AppCompatActivity {

     private EditText EditTextWeb = (EditText) findViewById(R.id.editTextWeb);
    private EditText EditTextPhone;
    private ImageButton imageButtonFin;
    private ImageButton imageButtonWeb;
    private ImageButton imageButtonPhone;
    private final int CALL_CODE_NUMBER= 100;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        EditTextPhone=(EditText) findViewById(R.id.editTextPhone);
        imageButtonFin=(ImageButton) findViewById(R.id.imageButtonFin);
        imageButtonWeb=(ImageButton) findViewById(R.id.imageButtonWeb);
        imageButtonPhone=(ImageButton) findViewById(R.id.imageButtonPhone);


        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber= EditTextPhone.getText().toString();

                if(phoneNumber!=null){

                    //comprobar version actual de android que estamos utilizando

                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},CALL_CODE_NUMBER);

                    }else{
                        olderVersions(phoneNumber);
                    }

                }
            }

            private void olderVersions(String phoneNumber){

                Intent intentCall= new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneNumber));
                if(checkPermission(Manifest.permission.CALL_PHONE)){
                    startActivity(intentCall);
                }else{
                    Toast.makeText(ThirdActiviry.this,"You declined the permission",Toast.LENGTH_SHORT).show();
                }


            }


        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //estamos en el caso del telefono

        switch (requestCode){
            case CALL_CODE_NUMBER:
                String permission= permissions[0];
                int result=grantResults[0];

                if(permission.equals(Manifest.permission.CALL_PHONE)){

                    //comprobar si a sido aceptado o denegado el permiso
                    if(result==PackageManager.PERMISSION_GRANTED){
                        //concedio su permiso
                        String phoneNumber=EditTextPhone.getText().toString();
                        Intent intentCall=new Intent(Intent.ACTION_CALL,Uri.parse("tel: " + phoneNumber));
                    }else{
                        //no concedio permiso
                        Toast.makeText(ThirdActiviry.this,"You declined the permission",Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            default:


                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }


    }

    private boolean checkPermission(String permiso){
        int result=this.checkCallingOrSelfPermission(permiso);
        return result==PackageManager.PERMISSION_GRANTED;
    }
}
