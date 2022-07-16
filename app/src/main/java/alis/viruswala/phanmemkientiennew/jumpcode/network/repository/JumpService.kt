package alis.viruswala.phanmemkientiennew.jumpcode.network.repository

import alis.viruswala.phanmemkientiennew.jumpcode.network.data.Request
import alis.viruswala.phanmemkientiennew.jumpcode.network.data.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface JumpService {

    @POST("app_conf")
    suspend fun getJumpCode(@Body param : Request): Response
}