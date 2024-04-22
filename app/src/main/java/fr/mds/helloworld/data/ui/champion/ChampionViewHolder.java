package fr.mds.helloworld.data.ui.champion;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.models.Champion;

public class ChampionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private int mTaskId;

    private TextView mName;
    private TextView mLane;
    private final ImageButton mUpdateButton;
    private final ImageButton mDeleteButton;
    private final FragmentManager mFragmentManager;

    public ChampionViewHolder(@NonNull View itemView, @NonNull FragmentManager fragmentManager) {
        super(itemView);
        mName = itemView.findViewById(R.id.champion_name);
        mLane = itemView.findViewById(R.id.champion_role);
        mUpdateButton = itemView.findViewById(R.id.champion_update);
        mDeleteButton = itemView.findViewById(R.id.champion_delete);
        mFragmentManager = fragmentManager;
    }

    public void bindTo(Champion element) {
        this.mTaskId = element.getId();
        mName.setText(element.getName());
        mLane.setText(element.getLane());

        mUpdateButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.champion_update) {
            UpdateChampionDialogFragment fragment = UpdateChampionDialogFragment.newInstance(mTaskId);
            fragment.show(mFragmentManager, "dialog");
            return;
        }

        if (viewId == R.id.champion_delete) {
            DeleteChampionDialogFragment fragment = DeleteChampionDialogFragment.newInstance(mTaskId);
            fragment.show(mFragmentManager, "dialog");
            return;
        }
    }
}
