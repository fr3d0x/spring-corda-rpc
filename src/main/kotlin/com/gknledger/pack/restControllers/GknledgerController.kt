package com.gknledger.pack.restControllers

import com.gknledger.pack.rpcConn.NodeRPCConnection
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.slf4j.LoggerFactory

private const val CONTROLLER_NAME = "config.controller.name"

@RestController
@RequestMapping("/api") // The paths for GET and POST requests are relative to this base path.
class GknledgerController(private val rpc: NodeRPCConnection,
                          @Value("Controller") private val controllerName: String) {


    companion object {
        private val logger = LoggerFactory.getLogger(RestController::class.java)
    }

    private val myName = rpc.proxy.nodeInfo().legalIdentities.first().name

    /** Returns the node's name. */
    @GetMapping(value = "/myname", produces = arrayOf("text/plain"))
    private fun myName() = myName.toString()

}