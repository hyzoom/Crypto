package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crypto.R;

import classic.CaeserCipher;
import classic.Vigenere;

/**
 * Created by root on 5/5/16.
 */
public class VigenereFragment extends Fragment {
    EditText plainText, cipherText, keyPlain, keyCipher;
    Button encryptButton, decryptButton;
    TextView encryptedText, decryptedText;
    String enc, dec, plainTextString, keyPlainString, cipherTextString, keyCigherString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vigenere_fragment, container, false);
        plainText = (EditText) v.findViewById(R.id.plainText);
        cipherText = (EditText) v.findViewById(R.id.cipherText);
        keyPlain = (EditText) v.findViewById(R.id.keyPlain);
        keyCipher = (EditText) v.findViewById(R.id.keyCipher);

        encryptButton = (Button) v.findViewById(R.id.encryptButton);
        decryptButton = (Button) v.findViewById(R.id.decryptButton);

        encryptedText = (TextView) v.findViewById(R.id.encryptedText);
        decryptedText = (TextView) v.findViewById(R.id.decryptedText);


        final Vigenere ac = new Vigenere();

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plainTextString = plainText.getText().toString();
                keyPlainString = keyPlain.getText().toString();

                if (!plainTextString.equals("") && !keyPlainString.equals("")) {
                    enc = ac.encrypt(plainTextString, keyPlainString);
                    Log.e("enc", enc);
                    encryptedText.setText(enc);
                    cipherText.setText(enc);
                } else {
                    Toast.makeText(getActivity(), "enter plain text", Toast.LENGTH_SHORT).show();
                }

//                plainText.setText("");
//                keyPlain.setText("");
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cipherTextString = cipherText.getText().toString();
                keyCigherString = keyCipher.getText().toString();
                if (!cipherTextString.equals("") && !keyCigherString.equals("")) {
                    dec = ac.decrypt(cipherTextString, keyCigherString);
                    Log.e("dec", dec);
                    decryptedText.setText(dec);
                } else {
                    Toast.makeText(getActivity(), "enter cipher text", Toast.LENGTH_SHORT).show();
                }

//                cipherText.setText("");
//                keyCipher.setText("");
            }
        });
        keyPlain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptedText.setText("");
            }
        });

        return v;
    }

}