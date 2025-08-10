package com.practicum.denettest.data

import com.practicum.denettest.domain.Node
import kotlinx.coroutines.flow.Flow
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import java.security.SecureRandom

class NodeRepository(private val nodeDao: NodeDao) {
    fun getChildren(parentName: String?): Flow<List<Node>> = nodeDao.getChildren(parentName)

    suspend fun getNodeByName(name: String): Node? = nodeDao.getNodeByName(name)

    suspend fun addNode(node: Node) = nodeDao.insert(node)

    suspend fun deleteNode(name: String) = nodeDao.delete(name)

    fun generateEthereumAddress(): String {
        val secureRandom = SecureRandom()
        val privateKeyBytes = ByteArray(32)
        secureRandom.nextBytes(privateKeyBytes)
        val privateKey = java.math.BigInteger(1, privateKeyBytes)
        val keyPair = ECKeyPair.create(privateKey)
        val address = Keys.getAddress(keyPair)
        return "0x$address"
    }
}