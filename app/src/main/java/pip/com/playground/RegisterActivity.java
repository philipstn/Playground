package pip.com.playground;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    public EditText etEmailNew;
    private EditText etPasswordNew;
    private EditText etCPasswordNew;
    public String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        etEmailNew = findViewById(R.id.et_email_new);
        etPasswordNew = findViewById(R.id.et_password_new);
        etCPasswordNew = findViewById(R.id.et_cpassword_new);

        etEmailNew.setText(email, TextView.BufferType.EDITABLE);
        setupUI(findViewById(R.id.parent));
    }


    public void tvreturn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public void setupUI(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(RegisterActivity.this);
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


    public void btnRegister(View view) {
        email = etEmailNew.getText().toString();
        password = etPasswordNew.getText().toString();

        if (etEmailNew.getText().toString().equals("")) {
            etEmailNew.setError("cant empty");
        } else if (!(etEmailNew.getText().toString().contains("@") && etEmailNew.getText().toString().contains("."))) {
            etEmailNew.setError("wrong email format");
        } else if (!(etPasswordNew.getText().toString().equals(etCPasswordNew.getText().toString()))) {
            etCPasswordNew.setError("password didnt match");
        } else if (etPasswordNew.getText().toString().equals("")) {
            etPasswordNew.setError("cant empty");
        } else if (etCPasswordNew.getText().toString().equals("")) {
            etCPasswordNew.setError("cant empty");
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("emailRegister", email);
            intent.putExtra("passwordRegister", password);
            setResult(RESULT_OK, intent);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Account succesfully created", Toast.LENGTH_SHORT).show();
        }
    }
}
