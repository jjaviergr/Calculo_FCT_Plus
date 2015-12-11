package com.example.usuario.calculo_fct_plus;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Usuario on 11/12/2015.
 */
public class clase_update_ejemplo {
    /*
    * package com.pmdm.ud5_ej6;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Esta clase actualiza los contactos de la Agenda de Contactos.
 * Para hacer esto primero se busca al contacto a actualizar, se muestran sus datos y se permite<br>
 * al usuario modificar estos. Por ultimo el usuario puede pulsar el boton 'Guardar' y los cambios se actualizaran en la BD.
 * @author Jos� Javier Garc�a Romero
 *
 *//*
    public class Update_Contactos extends Activity
    {
        private EditText edNombre_a_buscar;
        private EditText edNombre;
        private EditText edTlf;
        private EditText edTipo;

        private EditText edDireccion;
        private Button btSalir;
        private Button btConsultar;
        private Button btActualizar;
        private Button btVerDireccion;

        private long id;

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.modificar_contactos);

            edNombre_a_buscar = (EditText) findViewById(R.id.ieditText_a_Buscar);
            edNombre=(EditText)findViewById(R.id.ieditTextNombre);
            edTlf = (EditText) findViewById(R.id.iedTelefono);
            edDireccion=(EditText)findViewById(R.id.iedDireccion);
            edTipo=(EditText)findViewById(R.id.iEdTipo);

            btConsultar = (Button) findViewById(R.id.iBotonBuscar);
            btSalir = (Button) findViewById(R.id.iBtSalir);

            btActualizar=(Button)findViewById(R.id.ibtGuardar);
            btVerDireccion=(Button)findViewById(R.id.ibtVerDireccion);

            // Boton Ver Direccion. Este boton invoca a 'Google maps' o una aplicaci�n similar
            // si no tenemos una aplicaci�n en el simulador que pueda interpretar la direcci�n mostrara una
            // tostada con un mensaje de error
            btVerDireccion.setOnClickListener(new ImageButton.OnClickListener() {
                public void onClick(View v) {

                    try
                    {
                        String geo=edDireccion.getText().toString();
                        Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(geo));
                        startActivity(i);
                    }
                    catch(Exception e)
                    {
                        Util.tostada(getApplicationContext(), "Error .Debes seleccionar una direccion valida "+e);
                    }
                }
            });

            // Boton Actualizar . Actualiza la BD conforme a los datos que tenemos en la interface.
            btActualizar.setOnClickListener(new ImageButton.OnClickListener()
            {
                public void onClick(View v)
                {

                    try
                    {
                        long aux=id;
                        Contactos contacto=new Contactos(aux,edNombre.getText().toString(),edTipo.getText().toString(),
                                edTlf.getText().toString(),edDireccion.getText().toString());
                        Actualizar_Contacto(contacto);
                        Util.tostada(getApplicationContext(), "Contacto actualizado con exito");
                    }
                    catch (Exception e)
                    {
                        Util.tostada(getApplicationContext(), "Ha ocurrido un error al actualizar un contacto "+e);
                    }
                }
            });

            // Boton Consultar . Busca a un Contacto y lo imprime en la interface.
            btConsultar.setOnClickListener(new ImageButton.OnClickListener()
            {
                public void onClick(View v)
                {
                    try
                    {
                        Cursor c=BuscarContacto(edNombre_a_buscar.getText().toString());
                        if (c.getCount()>0)
                        {
                            try
                            {
                                id=c.getLong(0);
                                edNombre.setText(c.getString(1).toString());
                                edTipo.setText(c.getString(2).toString());
                                edTlf.setText(c.getString(3).toString());
                                edDireccion.setText(c.getString(4).toString());
                            }
                            catch (Exception e)
                            {
                                Util.tostada(getApplicationContext(), "Error imprimiendo "+e);
                            }
                        }
                        else
                        {
                            Util.tostada(getApplicationContext(), "No hay nadie con ese nombre ");
                        }
                    }
                    catch (Exception e)
                    {
                        Util.tostada(getApplicationContext(), "Error con el cursor "+e);
                    }
                }
            });

            // Bot�n 'Salir'. Si es pulsado hace que se cierre o termine esta acci�n.
            btSalir.setOnClickListener(new ImageButton.OnClickListener()
            {
                public void onClick(View v)
                {
                    finish();
                }
            });
        }

        /**
         *
         * @param contacto Actualiza un Contacto de la BD. Requiere un objeto Contacto.
         * @return Devuelve un Boolean True en caso de exito o un False si no se ha hecho actualizaci�n.
         *//*
        private boolean Actualizar_Contacto(Contactos contacto)
        {
            boolean actualizado=false;
            AdaptadorBD db = new AdaptadorBD(this);

            try
            {
                db.open();
                actualizado=db.actualizarContacto(contacto);

            }
            catch (Exception e)
            {
                Util.tostada(getApplicationContext(), "Error al actualizar "+e);
            }
            finally
            {
                try
                {
                    db.close();
                }
                catch (Exception e)
                {
                    Util.tostada(getApplicationContext(), "Error cerrando la BD al actualizar "+e);
                }
            }
            return actualizado;
        }

        /**
         * Busca un Contacto en la BD
         * @param nombre Nombre de nuestro contacto
         * @return Devuelve un objeto Cursor que contiene al contacto buscado o esta vacio en caso de no encontrarlo.
         */
    /*
        public Cursor BuscarContacto(String nombre)
        {
            Cursor cursor=null;
            if ((nombre.length() != 0) )
            {
                AdaptadorBD db = new AdaptadorBD(this);
                try
                {
                    db.open();
                    cursor =db.getContactoByName(nombre);

                }
                catch (Exception e)
                {
                    Util.tostada(getApplicationContext(), "Error al buscar "+e);
                }
                finally
                {
                    try
                    {
                        db.close();
                    }
                    catch (Exception e)
                    {
                        Util.tostada(getApplicationContext(), "Error cerrando la BD al buscar "+e);
                    }
                }
            }
            return cursor;
        }
    }
    */
}
