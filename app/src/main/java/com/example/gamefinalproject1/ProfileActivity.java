package com.example.gamefinalproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gamefinalproject1.databinding.ActivityProfileBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {
ActivityProfileBinding binding;
ViewModel viewModel;
UserTable user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBar);
        setTitle("game");
        viewModel=new ViewModelProvider(this).get(ViewModel.class);
//      UserTable userTable=new UserTable();
//      String username="aya";
//      userTable.setUsername(username);
//      viewModel.insertUser(userTable);
        binding.emailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Patterns.EMAIL_ADDRESS.matcher(binding.emailId.getText().toString()).matches()) {
                    binding.emailId.setEnabled(true);

                } else {
                    binding.emailId.setError("invalid email address");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        binding.etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                final int year =calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                final  int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog pickerDialog=new DatePickerDialog(view.getContext(),datapikerListiner,year,month,day);
                pickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                pickerDialog.show();
            }});
        binding.saveId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.emailId.getText().toString().length() == 0) {
                    binding.emailId.setError("Enter your email");
                } else if (binding.usernameId.getText().toString().length() == 0) {
                    binding.usernameId.setError("Enter your username");
                } else if (binding.password.getText().toString().length() == 0) {
                    binding.password.setError("enter your pass");

                } else if (binding.etBirthdate.getText().toString().length() == 0) {
                    binding.etBirthdate.setError("enter your birthdate");


                } else if (!binding.rbGender.isChecked() && !binding.rbGender2.isChecked()) {
                    Toast.makeText(ProfileActivity.this, "please enter your gender", Toast.LENGTH_SHORT).show();
                } else {
                    String username=  binding.usernameId.getText().toString();
                    String pass=   binding.password.getText().toString();
                    String email=    binding.emailId.getText().toString();
                    String birthdate= binding.etBirthdate.getText().toString();
                    boolean gender= Boolean.parseBoolean(binding.rbGender.getText().toString());
                    boolean gender2=Boolean.parseBoolean(binding.rbGender2.getText().toString());
                    Spinner spinner = binding.spCountry;
                    String text = spinner.getSelectedItem().toString();
                    if (username!=null && pass!=null && email!=null && birthdate!=null  ) {
                        // if(user==null){
                        //   Toast.makeText(Profile.this, "data is Empty", Toast.LENGTH_SHORT).show();
                        //  }else{
                        user = new UserTable();
                        user.setUsername(username);
                        user.setPassword(pass);
                        user.setEmail(email);
                        user.setBirthdate(birthdate);
                        user.setGender(gender);
                        user.setGender2(gender2);
                        user.setCountry(text);
                        viewModel.insertUser(user);
                        Toast.makeText(ProfileActivity.this, "Data successful save", Toast.LENGTH_SHORT).show();
                        //}

                    }
                    else {
                        Toast.makeText(ProfileActivity.this, "enter data", Toast.LENGTH_SHORT).show();
                    }
                    //   Intent intent=new Intent(getBaseContext(),HomeActivity.class);
                    // startActivity(intent);
                    // finish();
                }
            }
        });
        binding.deletId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user!=null){

                    viewModel.deleteUser(user);
                    binding.usernameId.setText("");
                    binding.password.setText("");
                    binding.etBirthdate.setText("");
                    binding.emailId.setText("");
                   //  binding.rbGender.setText("");
                  //   binding.rbGender2.setText("");
                 //    binding.spCountry.setTextDirection("");
                    //    User user1=new User();
                    // user1.setUsername("");
                    //user1.setPassword("");
                    //  user1.setEmail("");
                    // user1.setBirthdate("");
                    //user1.setCountry("");
                    Toast.makeText(ProfileActivity.this, "delete is done", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ProfileActivity.this, "no delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.editId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user!=null){
                    Intent intent=new Intent(ProfileActivity.this,EditeActivity.class);
                    intent.putExtra("userid",user.getKey());
                    intent.putExtra("username",user.getUsername());
                    intent.putExtra("userbirthdate",user.getBirthdate());
                    intent.putExtra("userpass",user.getPassword());
                    intent.putExtra("useremail",user.getEmail());
                    intent.putExtra("usergender",user.isGender());
                    intent.putExtra("usergender2",user.isGender2());
                    intent.putExtra("usercountry",user.getCountry());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ProfileActivity.this, "put the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private DatePickerDialog.OnDateSetListener datapikerListiner =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar c=Calendar.getInstance();
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,month);
            c.set(Calendar.DAY_OF_MONTH,day);
            String format=new SimpleDateFormat("dd MMM").format(c.getTime());
            binding.etBirthdate.setText(format);
        }};
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
