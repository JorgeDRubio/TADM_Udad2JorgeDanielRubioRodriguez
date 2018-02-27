package com.example.jorge.tadm_udad2jorgedanielrubiorodriguez;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarCliente extends AppCompatActivity {
    Button btnModificar, btnRegresar;
    EditText txtNombre, txtDomicilio, txtColonia, txtIdClientes;
    BaseDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cliente);

        txtNombre = findViewById(R.id.Nombre);
        txtColonia = findViewById(R.id.Colonia);
        txtDomicilio = findViewById(R.id.Domicilio);
        txtIdClientes = findViewById(R.id.IdClientes);
        btnModificar = findViewById(R.id.Modificar);
        btnRegresar = findViewById(R.id.Regresar);

        bd = new BaseDatos(this,"BASE1", null,1);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModificarClientes(txtNombre,txtDomicilio,txtColonia,txtIdClientes);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void ModificarClientes(EditText nom, EditText dom, EditText col, EditText id){
        InsertarCliente a = new InsertarCliente();
        try{
            if(a.vacio(nom) || a.vacio(dom) || a.vacio(col) || a.vacio(id)){
                Toast.makeText(ModificarCliente.this, "Uno o mas no se han llenado", Toast.LENGTH_SHORT).show();
            }
            else{
                if(!idFound(txtIdClientes)){
                    Toast.makeText(this, "El id no ha sido registrado", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase database = bd.getWritableDatabase();
                    String SQL ="UPDATE CLIENTE " +
                            "SET NOMBRE='"+a.cad(nom)+"', " +
                            "DOMICILIO='"+a.cad(dom)+"', " +
                            "COLONIA='"+a.cad(col)+"'"+
                            "WHERE IDCLIENTE="+a.cad(id);
                    database.execSQL(SQL);
                    Toast.makeText(ModificarCliente.this, "REGISTRO MODIFICADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (SQLiteException e){
            Toast.makeText(ModificarCliente.this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean idFound(EditText edt){
        try{
            SQLiteDatabase databse = bd.getReadableDatabase();
            String SQL = "SELECT IDCLIENTE FROM CLIENTE WHERE IDCLIENTE="+edt.getText().toString();

            Cursor c = databse.rawQuery(SQL,null);

            if(c.moveToFirst()){
                return true;
            }
            else{
                return false;
            }

        }catch(SQLiteException e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
