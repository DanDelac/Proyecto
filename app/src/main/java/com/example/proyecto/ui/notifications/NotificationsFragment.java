package com.example.proyecto.ui.notifications;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.databinding.FragmentSettingsBinding;
import com.example.proyecto.ui.changepassword.changePassword;
import com.example.proyecto.ui.editaccount.editAccount;
import com.example.proyecto.ui.login.Login;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentSettingsBinding binding;
    TextView txvUser, txvMail,txvSetAcc,txvSetPass,txvExit;

    public static final String LOG_PREF="log";
    String idUser,useAccount,useName,useLastN,useCorre;
    String aux,aux1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NotificationsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textNotifications;
        txvUser=root.findViewById(R.id.txt_setting_user);
        txvMail=root.findViewById(R.id.txt_setting_mail);
        txvSetAcc=root.findViewById(R.id.txt_setting_edit_acc);
        txvSetPass=root.findViewById(R.id.txt_setting_edit_pas);
        txvExit=root.findViewById(R.id.txt_setting_exit);

        SharedPreferences preferences = getActivity().getSharedPreferences(LOG_PREF,0);
        idUser = preferences.getString("idUser","nnn");
        useName = preferences.getString("useName","nnn");
        useLastN = preferences.getString("useLastN","nnn");
        useCorre = preferences.getString("useCorre","nnn");

        txvUser.setText(useLastN+", "+useName);
        txvMail.setText(useCorre);

        txvSetAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), editAccount.class);
                startActivity(i);
            }
        });
        txvSetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), changePassword.class);
                startActivity(i);
            }
        });
        txvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAcept();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void dialogAcept() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.question_Exit))
                .setPositiveButton(getString(R.string.question_Yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        goLogin();
                    }})
                .setNegativeButton(getString(R.string.question_No), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.show();
    }


    private void goLogin() {

        SharedPreferences log = getActivity().getSharedPreferences(LOG_PREF,0);
        SharedPreferences.Editor editor = log.edit();
        editor.putString("log","nnn");
        editor.commit();
        Intent i = new Intent(getContext(), Login.class);
        startActivity(i);
    }
}