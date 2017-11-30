package com.gknledger.pack.rpcConn

import org.springframework.stereotype.Component
import net.corda.client.rpc.CordaRPCClient
import net.corda.core.messaging.CordaRPCOps
import net.corda.core.utilities.NetworkHostAndPort
import org.springframework.beans.factory.annotation.Value

private const val CORDA_USER_NAME = "user1"
private const val CORDA_USER_PASSWORD = "test"
private const val CORDA_NODE_HOST = "localhost"
private const val CORDA_RPC_PORT = "10006"

@Component
class NodeRPCConnection(
        @Value("localhost") host: String,
        @Value("user1") username: String,
        @Value("test") password: String,
        @Value("10006") rpcPort: Int) {

    val proxy: CordaRPCOps

    init {
        val rpcAddress = NetworkHostAndPort(host, rpcPort)
        val rpcClient = CordaRPCClient(rpcAddress)
        val rpcConnection = rpcClient.start(username, password)
        proxy = rpcConnection.proxy
    }
}