package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.Summoner
import com.hubtwork.katarina.batchmatch.api.repository.SummonerRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SummonerService(private val repository: SummonerRepository){
    fun create(summoner: Summoner) {
        repository.save(summoner)

    }

    fun getAllSummonerCount(): Long =
        repository.findAll().size.toLong()

    fun getAllSummonerEntry(): List<Summoner> =
        repository.findAll()

    fun getSummonersForScan(): List<Pair<Int, String>> {
        val summoners = repository.getSummonersToFindMatch()
        if (summoners.isNotEmpty())
        {
            return summoners.map { Pair(it.id, it.accountId) }
        }
        return listOf()
    }

    fun isSummonerExist(accountId: String) :Boolean =
        repository.checkSummonerExist(accountId).isNotEmpty()

    fun getSummonerBySummonerName(summonerName: String): Summoner =
        repository.findBySummonerName(summonerName)

    fun scannedSuccessful(id: Int) :String {
        // There couldn't be Error find summoner by given ID. ( in business logic )
        val summoner = repository.getOne(id)
        summoner.lastScanTime = LocalDateTime.now()
        repository.save(summoner)
        return "[ NOTICE ] MARK ${ summoner.summonerName }'s scanned time correctly in DB "
    }

    fun deleteSummonerBySummonerName(summonerName: String): String {
        val summoner = repository.findBySummonerName(summonerName) ?: return "[ ERR ] $summonerName is not exist in DB"
        repository.deleteById(summoner.id)
        return "[ NOTICE ] $summonerName has been deleted in DB "
    }
}