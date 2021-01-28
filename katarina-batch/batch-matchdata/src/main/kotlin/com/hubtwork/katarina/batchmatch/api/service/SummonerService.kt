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

    fun readAll(): List<Summoner> = repository.findAll()

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

    fun read(summonerName: String): Summoner {
        TODO("Not yet implemented")
        repository.findBySummonerName(summonerName)
    }

    fun scannedSuccessful(id: Int) {
        TODO("Not yet implemented")
        val summoner = repository.getOne(id)
        summoner.lastScanTime = LocalDateTime.now()
        repository.save(summoner)
    }

    fun delete() {
        TODO("Not yet implemented")
    }
}