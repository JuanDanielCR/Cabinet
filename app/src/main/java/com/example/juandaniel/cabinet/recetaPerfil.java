package com.example.juandaniel.cabinet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public class recetaPerfil extends ActionBarActivity {

    String id, name, time;
    TextView nombre, duracion, ids;

    //Division
    ViewPager pager;
    ViewPagerAdapter2 adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Usuarios", "Medicamentos"};
    int Numboftabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_perfil);
        setToolbar();

        Bundle reciboreceta;
        reciboreceta = this.getIntent().getExtras();

        id = reciboreceta.getString("id_recetas");
        name = reciboreceta.getString("nombre_receta");
        time = reciboreceta.getString("duracion_receta");

        nombre = (TextView) findViewById(R.id.recibonombrereceta);
        duracion = (TextView) findViewById(R.id.reciboduracionreceta);
        ids = (TextView) findViewById(R.id.reciboidreceta);

        nombre.setText(name);
        duracion.setText(time);
        ids.setText(id);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter2(getSupportFragmentManager(), Titles, Numboftabs,id);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.darks);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("CABINET");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Receta");
        toolbar.setLogo(R.mipmap.logo);
        setSupportActionBar(toolbar);
    }
    public void Asocia(View view){
        Intent personas= new Intent(this,asociarReceta.class);
        Bundle envio= new Bundle();
        envio.putString("id_receta",id);
        personas.putExtras(envio);
        startActivity(personas);
    }
    public void Medicamento(View view){
        Intent personas= new Intent(this,MedicamentoAlta.class);
        Bundle envio= new Bundle();
        envio.putString("id_receta",id);
        personas.putExtras(envio);
        startActivity(personas);
    }
}
