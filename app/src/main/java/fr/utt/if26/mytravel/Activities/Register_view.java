package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.R;

public class Register_view extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private EditText mdp;
    private EditText mdp2;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        email = (EditText) findViewById(R.id.email_register);
        mdp = (EditText) findViewById(R.id.password_register);
        mdp2 = (EditText) findViewById(R.id.password_register2);

        register = (Button) findViewById(R.id.register_button);
        register.setOnClickListener(this);



    }

    private void validate(String password1, String password2) {
        if (password1.equals(password2)) {
            Toast toast = Toast.makeText(getApplicationContext(), "You Registered Successfully",
                    Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(Register_view.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast
                    .LENGTH_SHORT);
            toast.show();
            System.out.println("not good");
        }
    }


    @Override
    public void onClick(View v) {
        validate(mdp.getText().toString(), mdp2.getText().toString());
    }
}
