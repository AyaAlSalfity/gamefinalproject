package com.example.gamefinalproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gamefinalproject1.databinding.ActivityEditeBinding;

public class EditeActivity extends AppCompatActivity {
ActivityEditeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBar);
        setTitle("game");
        String username= getIntent().getStringExtra("username");
        String pass=  getIntent().getStringExtra("userpass");
        String email= getIntent().getStringExtra("useremail");
        int key=getIntent().getIntExtra("userid",-1);
        String country= getIntent().getStringExtra("usercountry");
        String birthdate=  getIntent().getStringExtra("userbirthdate");
        boolean gender= Boolean.parseBoolean(getIntent().getStringExtra("usergender"));
        boolean gender2= Boolean.parseBoolean(getIntent().getStringExtra("usergender2"));
        String   selected = binding.spCountry.getSelectedItem().toString();
        binding.usernameId.setText(username);
        binding.password.setText(pass);
        //   binding.spCountry.setText(selected);
        binding.emailId.setText(email);
        binding.etBirthdate.setText(birthdate);
        // binding.rbGender.setText(gender);
        binding.btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=binding.usernameId.getText().toString();
                String p=binding.password.getText().toString();
                String e=binding.emailId.getText().toString();
                // String c=binding.spCountry.getText().toString();
                String b=binding.etBirthdate.getText().toString();
                UserTable user =new UserTable();
                user.setUsername(username);
                user.setPassword(pass);
                user.setEmail(email);
                user.setBirthdate(birthdate);
                user.setCountry(country);
                user.setKey(key);
                ViewModel viewHolder=new ViewModelProvider(EditeActivity.this).get(ViewModel.class);
                viewHolder.updateUser(user);
                finish();
                Toast.makeText(EditeActivity.this, "update done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditeActivity.this,ProfileActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusettings,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:

                Intent intent=new Intent(getBaseContext(),HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}