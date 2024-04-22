package fr.mds.helloworld.data.ui.champion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.mds.helloworld.R;
import fr.mds.helloworld.data.models.Champion;

public class ChampionListAdapter extends RecyclerView.Adapter<ChampionViewHolder> {
    private List<Champion> mDataSet;
    private FragmentManager mFragmentManager;

    public ChampionListAdapter(List<Champion> dataset, @NonNull FragmentManager fm) {
        mDataSet = dataset;
        mFragmentManager = fm;
    }

    @NonNull
    @Override
    public ChampionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_champion, parent, false);
        return new ChampionViewHolder(view, mFragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull ChampionViewHolder holder, int position) {
        holder.bindTo(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
