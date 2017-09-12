package com.simonzc3.sesion1layouts;

import android.app.DatePickerDialog;
import android.app.Dialog;
//import android.app.DialogFragment;
import android.app.TimePickerDialog;

import android.icu.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CheckBox;
import android.support.v4.app.DialogFragment;

import static android.support.constraint.R.id.parent;


public class MainActivity extends AppCompatActivity {

    private EditText eLogin, ePass, eConfpass,eLugar, eCorreo; //No todos los nombres deben ser iguales
    private TextView tinfo;
    private Button bRegistrar,bFecha;
    private String login, pass,pass2, correo, sexo = "Masculino", texto,ciudad, hobbies;
    private CheckBox cCine, cComer, cDeporte, cDormir;
    private Spinner sCiudad;
    private int year=0,month=0,day=0;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eCorreo = (EditText) findViewById(R.id.eCorreo);
        eLogin = (EditText) findViewById(R.id.eNombre);
        ePass = (EditText) findViewById(R.id.eApellido);
        eConfpass = (EditText) findViewById(R.id.eConfirmp);
        bRegistrar = (Button) findViewById(R.id.bRegistrar);
        cCine = (CheckBox) findViewById(R.id.cCine);
        cComer = (CheckBox) findViewById(R.id.cComer);
        cDeporte = (CheckBox) findViewById(R.id.cDeporte);
        cDormir = (CheckBox) findViewById(R.id.cDormir);
        tinfo = (TextView) findViewById(R.id.tInfo);
        sCiudad = (Spinner) findViewById(R.id.sCiudad);
        bFecha = (Button) findViewById(R.id.bFecha);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudad_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sCiudad.setAdapter(adapter);



        sCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // perform click event on edit text
        bFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR); // current year
                month = c.get(Calendar.MONTH); // current month
                day = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                bFecha.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

    }


    public void Registrar(View view) {
      /*  nombre= Integer.parseInt(eNombre.getText().toString());
        apellido= Integer.parseInt(eApellido.getText().toString());
        res=nombre + apellido;
        tinfo.setText(Integer.toString(res));*/


        login = eLogin.getText().toString();
        pass = ePass.getText().toString();
        pass2=eConfpass.getText().toString();
        correo=eCorreo.getText().toString();
        hobbies = "";
        if (cCine.isChecked()) {
            hobbies = hobbies + " " + " Cine\n ";
        }
        if (cDeporte.isChecked()) {
            hobbies = hobbies + " " + " Deporte\n ";
        }
        if (cComer.isChecked()) {
            hobbies = hobbies + " " + " Comer \n";
        }
        if (cDormir.isChecked()) {
            hobbies = hobbies + " " + " Dormir \n";
        }

        if(((login.isEmpty())||(pass.isEmpty())||(pass2.isEmpty())||(hobbies.isEmpty())||(correo.isEmpty())||(ciudad.isEmpty()))||(!pass.equals(pass2))||(day==0&&month==0&&year==0)){
            tinfo.setTextColor(getResources().getColor(R.color.red));
            texto="";
           if((login.isEmpty())||(pass.isEmpty())||(pass2.isEmpty())||(hobbies.isEmpty())||(correo.isEmpty())||(ciudad.isEmpty())||(day==0&&month==0&&year==0)){
              texto= "Complete todos los campos";
               tinfo.setText(texto);
            }
           if(!pass.equals(pass2)){
                texto=texto + "\nLas contraseñas no coinciden";
                tinfo.setText(texto );
            }
        } else {
            tinfo.setTextColor(getResources().getColor(R.color.black));
            tinfo.setText("Información ingresada:\n Login: "
                    + login + "\n Contraseña:"+ pass + "\n Correo:"+ correo + "\n Lugar de nacimiento: " + ciudad + "\nFecha de nacimiento:"
            + day +"/"+ month +"/"+year+ "\nHobbies:\n"+ hobbies);
        }



       // tinfo.setText("Nombre: " + nombre + "\nApellido: " + apellido + "\nCelular:" + cel +
        //        "\nCiudad:" + "\nhobbies:" + hobbies);

    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();

        if (id == R.id.rMasculino) {
            sexo = "Masculino";

        }
        if (id == R.id.rFemenino) {
            sexo = "Femenino";
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);



            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user


        }
    }



}