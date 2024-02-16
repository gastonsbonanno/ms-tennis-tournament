package com.geopagos.mstennistournament.adapter.sqlserver.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tournament_results")
@Data
public class TournamentResultSqlServerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "execution_date")
    private Date executionDate;

    private String gender;

    @Column(name = "winner_name")
    private String winnerName;

    @Column(name = "matchs_played")
    private Long matchsPlayed;
}


