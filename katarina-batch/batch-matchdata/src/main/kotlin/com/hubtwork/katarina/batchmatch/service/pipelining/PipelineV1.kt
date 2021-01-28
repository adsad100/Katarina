package com.hubtwork.katarina.batchmatch.service.pipelining

interface PipelineV1 {

    /**
     *      Bring Up to Date Each Summoner's Match Data in DB
     *
     * 1. read List of summoners In DB      ->      List<Summoner>
     * 2. [ Loop in List<Summoner> ] Riot API ( Match V4 ) call with accountId of Summoner
     *      - Get LeagueEntry   ->  List<MatchReferenceDTO>     ->  List<KatarinaMatchDTO>
     *
     * 3.
     *
     */
    fun checkSummonersLastMatch()

    /**
     *
     *
     */
    fun getSummonersFromLeague()


}


