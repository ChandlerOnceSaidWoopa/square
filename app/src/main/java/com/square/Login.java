package com.square;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Fragment {

    static Login fragment = null;
    private static final String uname = "Saiyam";
    private static final String password = "Saiyam";
    private EditText Username;
    private EditText Password;
    private LoginCallbacks result;

    public static Login newInstance() {
        if (fragment == null)
            fragment = new Login();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button btn1 = view.findViewById(R.id.button1);
        Username = view.findViewById(R.id.UserName);
        Password = view.findViewById(R.id.Password);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                String u = Username.getText().toString().trim();
                String p = Password.getText().toString().trim();
                if(u.contentEquals(uname) && p.contentEquals(password))
                    result.onSuccess(uname);
            }
        });

        TextView tv2 = view.findViewById(R.id.textView2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Toast Message for clicking Register Here", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        result = (LoginCallbacks)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        result = null;
    }

}