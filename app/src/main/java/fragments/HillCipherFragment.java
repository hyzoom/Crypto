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

import java.util.Arrays;
import java.util.List;

import classic.HillCipher;
import classic.Playfair;

/**
 * Created by root on 5/5/16.
 */
public class HillCipherFragment extends Fragment {
    EditText plainText, cipherText, keyPlain, keyCipher;
    Button encryptButton, decryptButton;
    TextView encryptedText, decryptedText;
    String enc, dec, plainTextString, keyPlainString, cipherTextString, keyCigherString;
    int k[][] = new int[3][3];
    int k1[][] = new int[3][3];
    int count = 0, count1 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hill_cipher_fragment, container, false);
        plainText = (EditText) v.findViewById(R.id.plainText);
        cipherText = (EditText) v.findViewById(R.id.cipherText);
        keyPlain = (EditText) v.findViewById(R.id.keyPlain);
        keyCipher = (EditText) v.findViewById(R.id.keyCipher);

        encryptButton = (Button) v.findViewById(R.id.encryptButton);
        decryptButton = (Button) v.findViewById(R.id.decryptButton);

        encryptedText = (TextView) v.findViewById(R.id.encryptedText);
        decryptedText = (TextView) v.findViewById(R.id.decryptedText);


        final HillCipher ac = new HillCipher();

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plainTextString = plainText.getText().toString();
                keyPlainString = keyPlain.getText().toString();
                List<String> keyList = Arrays.asList(keyPlainString.split(","));

                if (!plainTextString.equals("") && !keyPlainString.equals("")) {
                    for (int i = 0; i < k.length; i++) {
                        for (int j = 0; j < k[i].length; j++) {
                            k[i][j] = Integer.parseInt(keyList.get(count++));
                        }
                    }
                    enc = ac.encrypt(plainTextString, k);
                    count = 0;
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
                List<String> keyInvList = Arrays.asList(keyCigherString.split(","));
                if (!cipherTextString.equals("") && !keyCigherString.equals("")) {
                    for (int i = 0; i < k1.length; i++) {
                        for (int j = 0; j < k1[i].length; j++) {
                            k1[i][j] = Integer.parseInt(keyInvList.get(count1++));
                        }
                    }
                    dec = ac.decrypt(cipherTextString, k1);
                    count1 = 0;
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
