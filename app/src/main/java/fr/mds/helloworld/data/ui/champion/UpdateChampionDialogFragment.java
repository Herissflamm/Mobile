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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.database.AppDatabase;
import fr.mds.helloworld.data.models.Champion;

public class UpdateChampionDialogFragment extends DialogFragment {
    private EditText mTaskTitle;
    private EditText mTaskIsDone;
    private int mChampionId;
    private ChampionViewModel mViewModel;
    private boolean mIsTitleValid = false;
    private boolean mIsDoneValid = false;

    public static UpdateChampionDialogFragment newInstance(int championId) {
        UpdateChampionDialogFragment fragment = new UpdateChampionDialogFragment();
        fragment.mChampionId = championId;

        return fragment;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        @SuppressLint("inflateParams")
        final View dialogView = requireActivity().getLayoutInflater().inflate(R.layout.dialog_champion_update, null);

        builder.setView(dialogView)
                .setPositiveButton("Valider", (dialog, which) -> {
                    if (!mIsDoneValid || !mIsTitleValid) { return; }
                    String name = mTaskTitle.getText().toString();
                    String laneStr = mTaskIsDone.getText().toString();
                    updateChampion(name, laneStr);
                })
                .setNegativeButton("Annuler", (dialog, which) -> {
                    requireDialog().cancel();
                });

        mTaskTitle = dialogView.findViewById(R.id.champion_update_editText);
        mTaskIsDone = dialogView.findViewById(R.id.champion_update_editText2);

        mViewModel = new ViewModelProvider(this).get(ChampionViewModel.class);
        mViewModel.setDao(AppDatabase.getInstance(getContext()).getChampionDao());
        mViewModel.getById(mChampionId).observe(this, champion ->  {
            mTaskTitle.setText(champion.getName());
            mTaskIsDone.setText(champion.getLane());
        });

        final AlertDialog dialog = builder.create();

        mTaskTitle.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mIsTitleValid = isTitleValid(s.toString());
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(mIsDoneValid && mIsTitleValid);
            }
        });

        mTaskIsDone.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mIsDoneValid = isDoneValid(s.toString());
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(mIsDoneValid && mIsTitleValid);
            }
        });

        return dialog;
    }

    private boolean isTitleValid(String text) {
        boolean isLongEnough = text.length() >= 2;
        boolean isShortEnough = text.length() <= 30;
        return isLongEnough && isShortEnough;
    }

    private boolean isDoneValid(String text) {
        return text.equals("TOP") || text.equals("JGL") || text.equals("MID") || text.equals("ADC") || text.equals("SUP");
    }

    private void updateChampion(String name, String lane) {
        mViewModel.getById(mChampionId).observe(this, (champion) -> {
            champion.setName(name);
            champion.setLane(lane);
            mViewModel.updateChampionTask(champion);
        });
    }
}
