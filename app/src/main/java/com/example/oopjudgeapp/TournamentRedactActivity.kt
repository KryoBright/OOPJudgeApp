package com.example.oopjudgeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.tournament_set_layout.*

class TournamentRedactActivity : AppCompatActivity(),ActivityLauncher {

    var id=0
    lateinit var viewAdapter: MatchesAdapter
    lateinit var curTour:Tournament;

    override fun startnext(intent: Intent?) {
            startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tournament_set_layout)
        id=intent.getIntExtra("id",-1)
        if (id!=-1)
        {
            curTour=EverythingHolder.getAllTournaments().get(id)
            textViewTourName.setText(curTour.name)
            editTextTourDesc.setText(curTour.desc)
        }
        else curTour= Tournament()
        val viewManager = LinearLayoutManager(this)
        viewAdapter = MatchesAdapter(curTour.matches, id,this, this)
        tournamenSetMatches.layoutManager = viewManager
        tournamenSetMatches.adapter = viewAdapter
        buttonSaveTournament.setOnClickListener {
            var newTournament=Tournament()
            newTournament.id=id
            newTournament.name=textViewTourName.text.toString()
            newTournament.desc=editTextTourDesc.text.toString()
            newTournament.isFinished=false
            var i=0
            while(i<curTour.matches.size)
            {
                newTournament.addMatch(curTour.matches[i],true)
                i++
            }
            EverythingHolder.addTournament(newTournament)
            finish()

        }
    }

    override fun onResume() {
        super.onResume()
    //    viewAdapter = MatchesAdapter(curTour.matches,id, this, this)
    //    tournamenSetMatches.swapAdapter(viewAdapter,false);
    }
}
