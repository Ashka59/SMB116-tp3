package cnam.smb116.smb116_tp3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

public class SecondActivity extends Activity {

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        this.str = intent.getStringExtra("ue");

//            this.question3();
        this.question4();
    }

    private void question3() {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
        finish();
    }

    private void question4() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SecondActivity.this, R.style.Theme_AppCompat_Dialog_Alert);

        alertDialogBuilder.setTitle(R.string.remove_UE);

        alertDialogBuilder
                .setMessage(R.string.remove_validation)
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    intent.putExtra("ueToRemove", str);
                    startActivity(intent);
                })
                .setNegativeButton("No", (dialog, id) -> finish());

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }
}