package com.hafizdwp.explore_tokocrypto_coins_log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseViewModel
import com.hafizdwp.explore_tokocrypto_coins_log.data.Repository
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Coin
import com.hafizdwp.explore_tokocrypto_coins_log.data.local.table.Symbol
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author hafizdwp
 * 18/05/2021
 **/
class MainViewModel(private val repository: Repository) : BaseViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>>
        get() = _coins


    fun getAllCoins() = viewModelScope.launch {
        try {
//            val symbols: List<Symbol> = asyncAwait { repository.getAllSymbols() }
//            val stringSymbols = convertSymbolsToString(symbols)
//            val coins: List<Coin> = asyncAwait { repository.getCoinsBySymbols(stringSymbols) }


            val listCoin = arrayListOf<Coin>()
            val stringSymbols = convertSymbolsToString(listSymbol)
            val coins: List<Coin> = asyncAwait { repository.getCoinsBySymbols(stringSymbols) }
//            stringSymbols.forEach {
//                val coin = repository.getCoinsBySymbols(listOf(it))
//                listCoin.add(coin[0])
//            }

//            _coins.value = listCoin.sortedBy { it.name }
            _coins.value = coins.sortedBy { it.name }

        } catch (e: Exception) {
            logError(e.toString())
        }
    }

    private suspend fun convertSymbolsToString(symbols: List<Symbol>): List<String> {
        return withContext(Dispatchers.Default) {
            val strings = arrayListOf<String>()
            symbols.forEach {
                strings.add(it.id)
            }

            strings
        }
    }

    val listSymbol = arrayListOf(
            Symbol("tokocrypto"),
            Symbol("vechain"),
            Symbol("ethereum"),
            Symbol("binancecoin"),
            Symbol("tron"),
            Symbol("cartesi"),
            Symbol("theta-fuel"),
            Symbol("zilliqa"),
            Symbol("ankr"),
            Symbol("cardano"),
            Symbol("dogecoin"),
            Symbol("safepal"),
            Symbol("wazirx"),
            Symbol("ripple"),
            Symbol("nano"),
            Symbol("trust-wallet-token"),
            Symbol("bittorrent-2"),
            Symbol("reef-finance"),

            Symbol("litecoin"),
            Symbol("1inch"),
            Symbol("algorand"),
            Symbol("my-neighbor-alice"),
            Symbol("alpha-finance"),
            Symbol("cosmos"),
            Symbol("auto"),
            Symbol("avalanche-2"),
            Symbol("bakerytoken"),
            Symbol("band-protocol"),

            Symbol("basic-attention-token"),
            Symbol("bitcoin-cash"),
            Symbol("bitcoin-cash-abc-2"),
            Symbol("bitcoin"),
            Symbol("burger-swap"),
            Symbol("pancakeswap-token"),
            Symbol("compound-coin"),
            Symbol("cream-2"),
            Symbol("certik"),
            Symbol("dash"),
            Symbol("dego-finance"),
            Symbol("polkadot"),

            Symbol("elrond-erd-2"),
            Symbol("enjincoin"),
            Symbol("filecoin"),
            Symbol("force-protocol"),
            Symbol("fantom"),
            Symbol("the-graph"),
            Symbol("hedera-hashgraph"),
            Symbol("rupiah-token"),
            Symbol("injective-protokol"),
            Symbol("just"),
            Symbol("kava"),
            Symbol("kyber-network-crystal"),
            Symbol("kusama"),
            Symbol("ethlend"),

            Symbol("lina"),
            Symbol("chainlink"),
            Symbol("loopring"),
            Symbol("terra-luna"),
            Symbol("decentraland"),
            Symbol("matic-network"),
            Symbol("moviebloc"),
            Symbol("origin-protocol"),
            Symbol("prometeus"),
            Symbol("qtum"),
            Symbol("thorchain"),
            Symbol("the-sandbox"),
            Symbol("shiba-inu"),
            Symbol("smooth-love-potion"),
            Symbol("havven"),
            Symbol("solana"),
            Symbol("serum"),
            Symbol("sun"),
            Symbol("sushi"),
            Symbol("swipe"),
            Symbol("theta-token"),
            Symbol("unifi-protocol-dao"),
            Symbol("uniswap"),
            Symbol("waves"),
            Symbol("wink"),
            Symbol("stellar"),
            Symbol("tezos"),
            Symbol("venus"),
            Symbol("yearn-finance"),
            Symbol("yfii-finance"),
            Symbol("0x"),
    )
}