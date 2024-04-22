package fr.mds.helloworld.data.ui.champion;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.database.AppDatabase;

public class AddChampionDialogFragment extends DialogFragment {
    private EditText mNameInput;
    private EditText mLaneInput;

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        @SuppressLint("InflateParams")
        View dialogView = requireActivity().getLayoutInflater().inflate(R.layout.dialog_champion_add, null);

        builder.setView(dialogView)
                .setPositiveButton("Valider", (dialog, which) -> {
                    String name = mNameInput.getText().toString();
                    String role = mLaneInput.getText().toString();
                    if (name.length() >= 2 && name.length() <= 30) {
                        addChampionElement(name, role);
                    }
                }).setNegativeButton("Annuler", (dialog, which) -> {
                    requireDialog().cancel();
                });

        final AlertDialog dialog = builder.create();

        mNameInput = dialogView.findViewById(R.id.champion_add_editText);
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                boolean isLongEnough = text.length() >= 2;
                boolean isShortEnough = text.length() <= 30;
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(isShortEnough && isLongEnough);
            }
        });

        mLaneInput = dialogView.findViewById(R.id.champion_add_editLane);
        mLaneInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                boolean isLongEnough = text.length() == 3;
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(isLongEnough);
            }
        });

        return dialog;
    }

    private void addChampionElement(String title, String role) {
        ChampionViewModel vm = new ViewModelProvider(this).get(ChampionViewModel.class);
        vm.setDao(AppDatabase.getInstance(getContext()).getChampionDao());
        System.out.println(" Titre : " + title + " ROLE : " + role);
        vm.createChampion(title, role);
    }

    public static AddChampionDialogFragment newInstance() {
        return new AddChampionDialogFragment();
    }
}
