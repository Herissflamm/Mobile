package fr.mds.helloworld.data.ui.champion;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import fr.mds.helloworld.data.database.AppDatabase;

public class DeleteChampionDialogFragment extends DialogFragment {
    private int mChampionId;

    public static DeleteChampionDialogFragment newInstance(int championId) {
        DeleteChampionDialogFragment fragment = new DeleteChampionDialogFragment();
        fragment.mChampionId = championId;

        return fragment;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setMessage("Voulez-vous vraiment supprimer cette tâche ?")
                .setPositiveButton("Supprimer", (dialog, which) -> {
                    deleteTask();
                })
                .setNegativeButton("Annuler", (dialog, which) -> {
                    dialog.cancel();
                });

        return builder.create();
    }

    private void deleteTask() {
        final ChampionViewModel vm = new ViewModelProvider(this).get(ChampionViewModel.class);
        vm.setDao(AppDatabase.getInstance(getContext()).getChampionDao());
        vm.getById(mChampionId).observe(this, vm::deleteChampionTask);
    }
}
