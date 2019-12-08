package com.example.oopjudgeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.tournament_set_layout.*

class TournamentRedactActivity : AppCompatActivity(),ActivityLauncher {
    override fun startnext(intent: Intent?) {
        startActivity(intent)
        finish();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tournament_set_layout)
        var id=intent.getIntExtra("id",-1)
        lateinit var curTour:Tournament
        if (id!=-1)
        {
            curTour=EverythingHolder.getAllTournaments().get(id)
            textViewTourName.setText(curTour.name)
            editTextTourDesc.setText(curTour.desc)
        }
        else curTour= Tournament()
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = MatchesAdapter(curTour.matches, this, this)
        tournamenSetMatches.setLayoutManager(viewManager)
        tournamenSetMatches.setAdapter(viewAdapter)
        buttonSaveTournament.setOnClickListener {
            var newTournament=Tournament()
            newTournament.name=textViewTourName.text.toString()
            newTournament.desc=editTextTourDesc.text.toString()
            newTournament.isFinished=false
            startnext(Intent(this,MainActivity::class.java))
        }
    }
}
