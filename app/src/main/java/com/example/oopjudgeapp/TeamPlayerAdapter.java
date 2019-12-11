package com.example.oopjudgeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamPlayerAdapter extends RecyclerView.Adapter<TeamPlayerAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private static Context context=null;
    private static ActivityLauncher launcher=null;
    private List<Player> mDataSet;
    private List<Boolean> incData;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);
        }
    }

    public static class ViewHolderButton extends ViewHolder{
        private final TextView button;
        public ViewHolderButton(View v) {
            super(v);
            button = v.findViewById(R.id.buttonText);
        }
    }

    public static class ViewHolderContainer extends ViewHolder{
        private final TextView playerName;
        private final TextView playerEff;
        private final LinearLayout tourAll;

        public ViewHolderContainer(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            playerName = v.findViewById(R.id.textPlayerName);
            playerEff = v.findViewById(R.id.textEffectivity);
            tourAll=v.findViewById(R.id.playerBG);
        }

        public TextView getPlayerEff() {
            return playerEff;
        }

        public TextView getPlayerName() {
            return playerName;
        }

        public LinearLayout getTourAll() {
            return tourAll;
        }
    }

    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public TeamPlayerAdapter(List<Player> dataSet,List<Boolean> inclusion,Context con,ActivityLauncher launcher1) {
        mDataSet = dataSet;
        context=con;
        launcher=launcher1;
        incData=inclusion;
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        if (viewType==0) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.button_as_content, viewGroup, false);
            return new ViewHolderButton(v);
        }
        else{
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.player_as_content, viewGroup, false);
            return new ViewHolderContainer(v);
        }
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (position==0)
        {
            ((ViewHolderButton) viewHolder).button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int i = 0;
                    Intent intent=new Intent(context,AddOrRedactPlayerActivity.class);
                    intent.putExtra("id",-1);
                    launcher.startnext(intent);
                }
            });
        }
        else
        {
            if (incData.get(position-1).booleanValue())
                ((ViewHolderContainer) viewHolder).tourAll.setBackgroundColor(Color.RED);
            else ((ViewHolderContainer) viewHolder).tourAll.setBackgroundColor(Color.WHITE);
            final Player cur=mDataSet.get(position-1);
            ((ViewHolderContainer) viewHolder).playerName.setText(mDataSet.get(position-1).getName());
            Double eff=cur.getEffectivity();
            ((ViewHolderContainer) viewHolder).playerEff.setText(eff.toString());

            ((ViewHolderContainer) viewHolder).tourAll.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    Intent intent=new Intent(context,AddOrRedactPlayerActivity.class);
                    intent.putExtra("id",cur.getId());
                    launcher.startnext(intent);
                    return false;
                }
            });

            final ViewHolderContainer holder=((ViewHolderContainer) viewHolder);

            ((ViewHolderContainer) viewHolder).tourAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    incData.set(position-1,!incData.get(position-1));
                    if (incData.get(position-1))
                        holder.tourAll.setBackgroundColor(Color.RED);
                    else holder.tourAll.setBackgroundColor(Color.WHITE);
                }
            });
        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)
    public void updData(List<Player> dataSet){
        mDataSet=dataSet;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size()+1;
    }
}
