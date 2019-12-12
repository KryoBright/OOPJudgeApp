package com.example.oopjudgeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private static Context context=null;
    private static ActivityLauncher launcher=null;
    private List<Team> mDataSet;
    private Integer chosenID;

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
        private final TextView teamName;
        private final TextView teamWins;
        private final TextView teamLoses;
        private final LinearLayout tourAll;

        public ViewHolderContainer(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            teamName = v.findViewById(R.id.textTeamName);
            teamWins = v.findViewById(R.id.textTeamWins);
            teamLoses = v.findViewById(R.id.textTeamLoses);
            tourAll=v.findViewById(R.id.teamBG);
        }


        public TextView getTeamWins() {
            return teamWins;
        }

        public TextView getTeamName() {
            return teamName;
        }

        public TextView getTeamLoses() {
            return teamLoses;
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
    public TeamListAdapter(List<Team> dataSet,Integer chID,Context con,ActivityLauncher launcher1) {
        mDataSet = dataSet;
        context=con;
        launcher=launcher1;
        chosenID=chID;
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
                    .inflate(R.layout.team_as_content, viewGroup, false);
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
                    Intent intent=new Intent(context,TeamRedactActivity.class);
                    intent.putExtra("id",-1);
                    launcher.startnext(intent);
                }
            });
        }
        else
        {
            if ((position-1)==chosenID)
                ((ViewHolderContainer) viewHolder).tourAll.setBackgroundColor(Color.RED);
            else ((ViewHolderContainer) viewHolder).tourAll.setBackgroundColor(Color.WHITE);
            ((ViewHolderContainer) viewHolder).teamName.setText(mDataSet.get(position-1).getName());
            ((ViewHolderContainer) viewHolder).teamWins.setText(mDataSet.get(position-1).getWins().toString());
            ((ViewHolderContainer) viewHolder).teamLoses.setText(mDataSet.get(position-1).getLoses().toString());

            ((ViewHolderContainer) viewHolder).tourAll.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    Intent intent=new Intent(context,TeamRedactActivity.class);
                    intent.putExtra("id",position-1);
                    launcher.startnext(intent);
                    return false;
                }
            });

            final ViewHolderContainer holder=((ViewHolderContainer) viewHolder);

            ((ViewHolderContainer) viewHolder).tourAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer oldID=chosenID;
                    chosenID=position-1;
                    notifyItemChanged(oldID);
                    if ((position-1)==chosenID)
                        holder.tourAll.setBackgroundColor(Color.RED);
                    else holder.tourAll.setBackgroundColor(Color.WHITE);
                }
            });
        }
    }

    public Integer getChosenID(){
        return chosenID;
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size()+1;
    }
}
