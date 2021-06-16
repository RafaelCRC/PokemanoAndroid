package com.example.testapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.fragments.AboutusFragment;
import com.example.testapi.fragments.ComparerFragment;
import com.example.testapi.fragments.StatsFragment;
import com.example.testapi.pokeapi.JsonPlaceHolderApi;
import com.example.testapi.pokeapi.Post;
import com.example.testapi.pokeapi.Species;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public boolean front = true;
    public boolean shiny = false;
    private int tempid;
    String a = "failure";
    int aa = 0;
    public int globalid = 1;
    public String hp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        //setMainFragment();
        getSupportActionBar().hide();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState != null){
            //Do whatever you need with the string here, like assign it to variable.
            globalid = savedInstanceState.getInt("myglobalid");
            front = savedInstanceState.getBoolean("myfrontbol");
            shiny = savedInstanceState.getBoolean("myshinybol");

        }
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull  MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new MainFragment(), "HOME").commit();
                break;
            case R.id.nav_stats:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new StatsFragment(tempid), "STATS").commit();

                break;
            case R.id.nav_comp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new ComparerFragment(), "COMP").commit();

                break;
            case R.id.nav_abtus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new AboutusFragment()).commit();
                break;
        }

        return true;
    }


    public void addData(String poke) {
        ProgressBar circ = findViewById(R.id.circprog);
        circ.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi apiService = retrofit.create(JsonPlaceHolderApi.class);
        Call<Post> call = apiService.getPokemon(poke);
        Call<Species> callsp = apiService.getSpecies(poke);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {

                    int i;
                    Post post = response.body();
                    TextView text = findViewById(R.id.pokeName);
                    TextView teext = findViewById(R.id.pokeID);
                    String capname = post.getName();
                    capname = capname.substring(0, 1).toUpperCase() + capname.substring(1);

                    text.setText(capname);
                    i = Integer.parseInt(post.getId());
                    teext.setText(post.getId());
                    String spriteurl = spriter(i);
                    ImageView iv_result = findViewById(R.id.iv_result);
                    LoadImage loadImage = new LoadImage(iv_result);
                    loadImage.execute(spriteurl);
                    TextView textpeso = findViewById(R.id.pesotext);
                    double pesotemp = Double.parseDouble(post.getWeight());
                    pesotemp = pesotemp / 10;
                    textpeso.setText("Weight:......." + pesotemp + "Kg");
                    TextView textaltura = findViewById(R.id.alturatext);
                    double alturatemp = Double.parseDouble(post.getHeight());
                    alturatemp = alturatemp / 10;
                    textaltura.setText("Height.:......" + alturatemp + "m");
                    tempid = i;
                    circ.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Este Pokemon não existe!",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });


        callsp.enqueue(new Callback<Species>() {
            @Override
            public void onResponse(Call<Species> call, Response<Species> response) {
                if(response.isSuccessful()){
                    Species species = response.body();
                    TextView gr = findViewById(R.id.genderratio);
                    double grtemp = Double.parseDouble(species.getGender_rate());
                    double fem = ((grtemp/8)*100);
                    double mal = 100 - fem;
                    String female;
                    String male;
                    if (grtemp < 0){
                        female = "..............F:N/A";
                        male = "M:N/A";
                    }else{
                        female = "..............F"+fem+"%";
                        male = "M"+mal+"%";
                    }
                    gr.setText("Gender Ratio: "+male+"\n"+female);
                    TextView hab = findViewById(R.id.habitatText);
                    if (species.getHabitat() == null){
                        hab.setText("Habitat: N/A");
                    }else{
                        String habitat = species.getHabitat().getName();
                        hab.setText("Habitat: "+habitat);
                    }



                }

            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {

            }
        });


    }

    private String spriter(int spriteid){
        String base = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
        if (front == false){
            base = base + "back/";
        }
        if (shiny == true){
            base = base + "shiny/";
        }
        base = base + spriteid + ".png";
        return base;
    }

    public void go(View view){

        globalid++;
        if (globalid > 898){
            globalid = 1;
        }
        addData(""+globalid);
    }

    public void leftgo(View view){

        globalid--;
        if (globalid <= 0){
            globalid = 898;
        }
        addData(""+globalid);
    }

    public void setBack(View view){
        if (front == false){
            front = true;
        }else{
            front = false;
        }
        String spriteurl = spriter(tempid);
        ImageView iv_result = findViewById(R.id.iv_result);
        Drawer.LoadImage loadImage = new Drawer.LoadImage(iv_result);
        loadImage.execute(spriteurl);
    }



    public void setShiny(View view){
        if (shiny == false){
            shiny = true;
        }else{
            shiny = false;
        }
        String spriteurl = spriter(tempid);
        ImageView iv_result = findViewById(R.id.iv_result);
        Drawer.LoadImage loadImage = new Drawer.LoadImage(iv_result);
        loadImage.execute(spriteurl);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int id = globalid;
        outState.putInt("myglobalid", id);
        outState.putBoolean("myfrontbol", front);
        outState.putBoolean("myshinybol", shiny);
        super.onSaveInstanceState(outState);
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView ivResult){

            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urLink).openStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void mailtorafa(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:rafael.2019107852@unicap.br"));
        startActivity(i);
    }

    public void mailtojoao(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:joao.2019107600@unicap.br"));
        startActivity(i);
    }


}