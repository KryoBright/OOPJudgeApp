package com.example.oopjudgeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = MatchesAdapter(EverythingHolder.getAllMatches(), this, this)
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
