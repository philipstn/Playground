package pip.com.playground;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    public EditText etEmail;
    private EditText etPassword;

    public String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//auto hide keyboard on load////////////////////////////////////////////////
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
        );
///////////////////////////////////////////////////////////////////////////

        //id diambil dari id parent di xml
        setupUI(findViewById(R.id.parent));


        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        etEmail.setText(email, TextView.BufferType.EDITABLE);

    }


    public void tvSignup(View view) {
        email = etEmail.getText().toString();
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();

    }

    //function digunakan untuk hide keyboard pas hilang focus///////////////////////
    public void setupUI(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginActivity.this);
                    return false;
                }
            });
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
////////////////////////////////////////////////////////////////////////////////


    public void btnLogin(View view) {
        Intent intent = getIntent();
        email = intent.getStringExtra("emailRegister");
        password = intent.getStringExtra("passwordRegister");
        if (etEmail.getText().toString().equals("")) {
            etEmail.setError("cant emtpy");
        } else if (etPassword.getText().toString().equals("")) {
            etPassword.setError("cant empty");
        } else if (!(etEmail.getText().toString().equals(email))) {
            etEmail.setError("wrong email");
        } else if (!(etPassword.getText().toString().equals(password))) {
            etPassword.setError("wrong password");
        } else {
            Intent intentt = new Intent(this, MainActivity.class);
            startActivity(intentt);
            finish();
        }
    }


    public void imgbtn(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
