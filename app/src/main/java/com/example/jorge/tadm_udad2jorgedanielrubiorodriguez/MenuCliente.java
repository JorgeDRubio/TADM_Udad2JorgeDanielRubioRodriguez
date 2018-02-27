package com.example.jorge.tadm_udad2jorgedanielrubiorodriguez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCliente extends AppCompatActivity {
    Button btnInsertar,btnModificar,btnConsultar,btnRregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);
        btnInsertar = findViewById(R.id.Insertar);
        btnModificar=findViewById(R.id.Modificar);
        btnConsultar = findViewById(R.id.Consultar);
        btnRregresar = findViewById(R.id.Regresar);


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insert = new Intent(MenuCliente.this,InsertarCliente.class);
                startActivity(insert);
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(MenuCliente.this,ModificarCliente.class);
                startActivity(update);
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent select = new Intent(MenuCliente.this,ConsultarCliente.class);
                startActivity(select);
            }
        });

        btnRregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
