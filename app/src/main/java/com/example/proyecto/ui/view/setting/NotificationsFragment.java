package com.example.proyecto.ui.view.setting;

import android.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.R;
import com.example.proyecto.databinding.FragmentSettingsBinding;
import com.example.proyecto.ui.view.changepassword.changePassword;
import com.example.proyecto.ui.view.editaccount.editAccount;
import com.example.proyecto.ui.view.login.Login;


public class NotificationsFragment extends Fragment {
    private NotificationsViewModel notificationsViewModel;
    private FragmentSettingsBinding binding;

    public static final String LOG_PREF="log";
    String idUser,useName,useLastN,useCorre;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(NotificationsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences preferences = getActivity().getSharedPreferences(LOG_PREF,0);
        idUser = preferences.getString("idUser","nnn");
        useName = preferences.getString("useName","nnn");
        useLastN = preferences.getString("useLastN","nnn");
        useCorre = preferences.getString("useCorre","nnn");

        binding.txtSettingUser.setText(useLastN+", "+useName);
        binding.txtSettingMail.setText(useCorre);

        binding.txtSettingEditAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), editAccount.class);
                startActivity(i);
            }
        });
        binding.txtSettingEditPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), changePassword.class);
                startActivity(i);
            }
        });
        binding.txtSettingExit.setOnClickListener(new View.OnClickListener() {
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
        editor.putString("log","dislog");
        editor.commit();
        Intent i = new Intent(getContext(), Login.class);
        startActivity(i);
    }
}