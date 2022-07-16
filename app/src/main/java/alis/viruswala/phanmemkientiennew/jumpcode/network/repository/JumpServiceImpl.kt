package alis.viruswala.phanmemkientiennew.jumpcode.network.repository

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import alis.viruswala.phanmemkientiennew.jumpcode.network.data.Request
import alis.viruswala.phanmemkientiennew.jumpcode.network.data.Response
import alis.viruswala.phanmemkientiennew.jumpcode.network.di.RetrofitHelper
import alis.viruswala.phanmemkientiennew.jumpcode.network.repository.JumpService

class JumpServiceImpl {

    private val service : JumpService = RetrofitHelper.service()

    suspend fun getJumpCodeUrl(param : Request): Flow<Response> = callbackFlow {
        trySend(service.getJumpCode(param))
        awaitClose()
    }
}