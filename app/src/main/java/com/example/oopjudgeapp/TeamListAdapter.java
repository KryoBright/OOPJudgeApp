package com.example.oopjudgeapp;

import android.content.Context;
import android.content.Intent;
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
            button = (TextView) v.findViewById(R.id.buttonText);
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
            teamName = (TextView) v.findViewById(R.id.textTeam1);
            teamWins = (TextView) v.findViewById(R.id.textResults);
            teamLoses = (TextView) v.findViewById(R.id.textTeam2);
            tourAll=v.findViewById(R.id.tournamentContainerFull);
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
    public TeamListAdapter(List<Team> dataSet,Context con,ActivityLauncher launcher1) {
        mDataSet = dataSet;
        context=con;
        launcher=launcher1;
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
                    //will transfer to new activity with no intent
                }
            });
        }
        else
        {
            ((ViewHolderContainer) viewHolder).teamName.setText(mDataSet.get(position-1).getName());
            ((ViewHolderContainer) viewHolder).teamWins.setText(mDataSet.get(position-1).getWins().toString());
            ((ViewHolderContainer) viewHolder).teamLoses.setText(mDataSet.get(position-1).getLoses().toString());

            ((ViewHolderContainer) viewHolder).tourAll.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    Intent intent=new Intent(context,TournamentRedactActivity.class);
                    launcher.startnext(intent);
                    return false;
                }
            });
        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size()+1;
    }
}
