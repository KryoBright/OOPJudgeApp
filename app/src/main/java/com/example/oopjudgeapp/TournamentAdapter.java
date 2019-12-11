package com.example.oopjudgeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TournamentAdapter extends RecyclerView.Adapter<TournamentAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private static Context context=null;
    private static ActivityLauncher launcher=null;
    private List<Tournament> mDataSet;

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
        private final TextView tourName;
        private final TextView tourScore;
        private final TextView tourWinner;
        private final LinearLayout tourAll;

        public ViewHolderContainer(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            tourName = v.findViewById(R.id.textTourName);
            tourScore = v.findViewById(R.id.textTourScore);
            tourWinner = v.findViewById(R.id.textTourWinner);
            tourAll=v.findViewById(R.id.tournamentContainerFull);
        }


        public TextView getNameView() {
            return tourName;
        }

        public TextView getTourScore() {
            return tourScore;
        }

        public TextView getTourWinner() {
            return tourWinner;
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
    public TournamentAdapter(List<Tournament> dataSet,Context con,ActivityLauncher launcher1) {
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
                    .inflate(R.layout.tournament_content, viewGroup, false);
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
                    Intent intent=new Intent(context,TournamentRedactActivity.class);
                    intent.putExtra("id",-1);
                    launcher.startnext(intent);
                }
            });
        }
        else
        {
            ((ViewHolderContainer) viewHolder).tourName.setText(mDataSet.get(position-1).getName());
            if (mDataSet.get(position-1).isFinished())
            {
                ((ViewHolderContainer) viewHolder).tourWinner.setText(mDataSet.get(position-1).getAllTeamsAndScoresSorted().get(0).first.getName());
                ((ViewHolderContainer) viewHolder).tourScore.setText(mDataSet.get(position-1).getAllTeamsAndScoresSorted().get(0).second.toString());
            }
            else
            {
                ((ViewHolderContainer) viewHolder).tourWinner.setText("Not defined yet");
                ((ViewHolderContainer) viewHolder).tourScore.setText("Not defined yet");
            }
            ((ViewHolderContainer) viewHolder).tourAll.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    Intent intent=new Intent(context,TournamentRedactActivity.class);
                    intent.putExtra("id",mDataSet.get(position-1).getId());
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
