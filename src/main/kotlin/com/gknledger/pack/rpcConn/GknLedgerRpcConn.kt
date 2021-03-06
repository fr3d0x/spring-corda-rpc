package com.gknledger.pack.rpcConn

import org.springframework.stereotype.Component
import net.corda.client.rpc.CordaRPCClient
import net.corda.core.messaging.CordaRPCOps
import net.corda.core.utilities.NetworkHostAndPort
import net.corda.core.utilities.getOrThrow
import org.springframework.beans.factory.annotation.Value
import kotlin.concurrent.thread

private const val CORDA_USER_NAME = "config.rpc.username"
private const val CORDA_USER_PASSWORD = "config.rpc.password"
private const val CORDA_NODE_HOST = "config.rpc.host"
private const val CORDA_RPC_PORT = "config.rpc.port"

@Component
class NodeRPCConnection(
        @Value("\${$CORDA_NODE_HOST}") host: String,
        @Value("\${$CORDA_USER_NAME}") username: String,
        @Value("\${$CORDA_USER_PASSWORD}") password: String,
        @Value("\${$CORDA_RPC_PORT}") rpcPort: Int) {

    val proxy: CordaRPCOps

    init {
        val rpcAddress = NetworkHostAndPort(host, rpcPort)
        val rpcClient = CordaRPCClient(rpcAddress)
        val rpcConnection = rpcClient.start(username, password)
        proxy = rpcConnection.proxy
        proxy.waitUntilNetworkReady().getOrThrow()
    }
}