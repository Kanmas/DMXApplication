package com.example.quentinlehmann.dmxv2;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class ParametreGlobaux extends AppCompatActivity {

    private static ParametreGlobaux currentInstance;
    private static void setCurrentInstance (ParametreGlobaux parametreGlobaux) {
        ParametreGlobaux.currentInstance = parametreGlobaux;
    }
    private static ParametreGlobaux getCurrentInstance () {
        return currentInstance;
    }

    private ModeleParametreGlobaux model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_parametre_globaux);
        setCurrentInstance(this);
        model = new ModeleParametreGlobaux();
        try{
            ((Spinner)findViewById(R.id.SpinerTypeCibleParaGlobo)).setAdapter(new ArrayAdapter<String>(getCurrentInstance(), android.R.layout.simple_spinner_item, getCurrentInstance().getModel().getTargetType()));
        }catch (Exception ex){
            Toast.makeText(getCurrentInstance(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        model.setOnPropertyChanged(new ModeleParametreGlobaux.PropertyChangedListener() {
            @Override
            public void OnPropertyChanged(String propertyName) {
                switch (propertyName) {
                    case "Type":
                        break;
                    case "Address":
                        break;
                    case "Port":
                        break;
                    case "Hostname":
                        break;
                    default:
                        break;
                }
            }
        });

        ((EditText)findViewById(R.id.editTextAddrIP)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getCurrentInstance().getModel().setHostname(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText) findViewById(R.id.editTextAddrCible)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getCurrentInstance().getModel().setAddress(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText)findViewById(R.id.editTextPort)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getCurrentInstance().getModel().setPort(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((Spinner)findViewById(R.id.SpinerTypeCibleParaGlobo)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    getCurrentInstance().getModel().setType(getCurrentInstance().getModel().getTargetType()[i]);
                }
                catch (Exception ex)
                {
                    Toast.makeText(getCurrentInstance(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public ModeleParametreGlobaux getModel() {
        return model;
    }

    public void setModel(ModeleParametreGlobaux model) {
        this.model = model;
    }


}
