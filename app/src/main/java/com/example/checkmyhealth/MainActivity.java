package com.example.checkmyhealth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Click_Home();
        Click_Button();
    }

    // Propriétés
    private EditText txtWeight;
    private EditText txtHeight;
    private EditText txtAge;
    private RadioButton rdMale;
    private TextView labelIMG;
    private ImageView img;
    private double Index;
    private String message;
    //Constants
    private static final Integer minFemale=15;
    private static final Integer maxFemale=30;
    private static final Integer minMale=10;
    private static final Integer maxMale=25;



    /**
     * initilazation
     */
    private void init() {
        txtWeight = (EditText) findViewById(R.id.txtWeight);
        txtHeight = (EditText) findViewById(R.id.txtHeight);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        labelIMG = (TextView) findViewById(R.id.labelMG);
        img = (ImageView) findViewById(R.id.img);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Click_Home() {
        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                Integer Weight=0;
                Integer Height=0;
                Integer age=0;
                Integer sex=0;
                //recuperation des donnees saisies
                try{
                    Weight=Integer.parseInt(txtWeight.getText().toString());
                    Height=Integer.parseInt(txtHeight.getText().toString());
                    age=Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){};
                if(rdMale.isChecked()){
                    sex=1;
                }
                // controle des données saisies
                if(Weight==0 || Height==0 || age==0)
                {
                    Toast.makeText(MainActivity.this,"Incorrect Input",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    IndexC(Weight,Height,age,sex);


                }

            }

        });
       
    }

    /**
     * Calculate Fat Mass Index
     * @param Weight
     * @param Height
     * @param age
     * @param sexe
     */
    public void  IndexC(Integer Weight, Integer Height ,Integer age,Integer sexe)
    {
        Log.i("hi",String.valueOf(Weight));
        Log.i("hi",String.valueOf(Height));
        Log.i("hi",String.valueOf(Weight*Weight));
        Log.i("hi",String.valueOf(Weight*12000/(Height*Height)+(0.23*age)-(10.83*sexe)-5.4));
        Index=Weight*12000/(Height*Height)+(0.23*age)-(10.83*sexe)-5.4;
        MessageC(Index,sexe);
    }
    public void MessageC(double IMG , Integer sexe)
    {
        Integer min;
        Integer max;
        if(sexe==0) //Female
        {
            min=minFemale;
            max=maxFemale;
        }
        else //Male
        {
            min=minMale;
            max=maxMale;
        }
        if(Index<min)
        {
            message="your Index is: "+Index+". And it's Too low. Try to eat more";
            img.setImageResource(R.drawable.low);
            labelIMG.setTextColor(Color.RED);
        }
        else if(Index<max) {
            message="your Index is: "+Index+". Congratulation, it's Normal";
            img.setImageResource(R.drawable.normal);
            labelIMG.setTextColor(Color.GREEN);
        }
        else
        {
            message="your Index is: "+Index+". And that's too hight , try to do some sport";
            img.setImageResource(R.drawable.hight);
            labelIMG.setTextColor(Color.RED);
        }
        labelIMG.setText(message);
    }

    public void Click_Button()
    {
        ImageButton button=(ImageButton) findViewById(R.id.imageButtonAcceuil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(i);
            }
        });
    }
}