package com.example.jorge.tadm_udad2jorgedanielrubiorodriguez;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultarCliente extends AppCompatActivity {
    Button btnConsultar, btnRegresar;
    EditText txtIdCliente;
    TextView textNombre, textDomicilio, textColonia;
    BaseDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);


        txtIdCliente = findViewById(R.id.IdClientes);

        textNombre = findViewById(R.id.Nombre);
        textDomicilio = findViewById(R.id.Domicilio);
        textColonia = findViewById(R.id.Colonia);

        btnConsultar = findViewById(R.id.Consultar);
        btnRegresar = findViewById(R.id.Regresar);

        bd = new BaseDatos(this,"BASE1", null,1);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultarClientes();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void ConsultarClientes(){
        InsertarCliente a = new InsertarCliente();
        try{
            if(a.vacio(txtIdCliente)){
                Toast.makeText(this, "Introduce el id del cliente", Toast.LENGTH_SHORT).show();
            }
            else{
                SQLiteDatabase databse = bd.getReadableDatabase();
                String SQL = "SELECT NOMBRE, DOMICILIO, COLONIA FROM CLIENTE WHERE IDCLIENTE="+a.cad(txtIdCliente);

                Cursor c = databse.rawQuery(SQL,null);

                if(c.moveToFirst()){
                    textNombre.setText("NOMBRE: "+c.getString(0));
                    textDomicilio.setText("DOMICILIO: "+c.getString(1));
                    textColonia.setText("COLONIA: "+c.getString(2));
                }
                else{
                    Toast.makeText(this, "No existe ese registro", Toast.LENGTH_SHORT).show();
                }
            }

        }   catch (SQLiteException e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

}
