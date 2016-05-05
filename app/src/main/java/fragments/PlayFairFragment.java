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

import classic.AffineCaeser;
import classic.Playfair;

/**
 * Created by root on 4/30/16.
 */
public class PlayFairFragment extends Fragment {
    EditText plainText, cipherText, keyPlain, keyCipher;
    Button encryptButton, decryptButton;
    TextView encryptedText, decryptedText;
    String enc, dec, plainTextString, keyPlainString, cipherTextString, keyCigherString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.playfair_fragment, container, false);
        plainText = (EditText) v.findViewById(R.id.plainText);
        cipherText = (EditText) v.findViewById(R.id.cipherText);
        keyPlain = (EditText) v.findViewById(R.id.keyPlain);
        keyCipher = (EditText) v.findViewById(R.id.keyCipher);

        encryptButton = (Button) v.findViewById(R.id.encryptButton);
        decryptButton = (Button) v.findViewById(R.id.decryptButton);

        encryptedText = (TextView) v.findViewById(R.id.encryptedText);
        decryptedText = (TextView) v.findViewById(R.id.decryptedText);


        final Playfair ac = new Playfair();

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plainTextString = plainText.getText().toString();
                keyPlainString = keyPlain.getText().toString();
                if (!plainTextString.equals("") && !keyPlainString.equals("")) {
                    enc = ac.encrypt1(plainTextString, keyPlainString);
                    encryptedText.setText(enc);
                    cipherText.setText(enc);
                    Log.e("enc", enc);
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
                } else {
                    Toast.makeText(getActivity(), "enter cipher text", Toast.LENGTH_SHORT).show();
                }
                decryptedText.setText(dec);
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
