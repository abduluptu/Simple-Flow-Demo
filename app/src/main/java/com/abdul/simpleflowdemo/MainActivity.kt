package com.abdul.simpleflowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun main() {
    // collectFlowValues()
    collectZipOperatorValues()
}

//Step1: Create flow builder
lateinit var flow: Flow<Int>
fun setupFlow() {
    // flow{} - This is a builder function to construct arbitrary flows.
    /*flow = flow {
        (1..5).forEach {
            delay(500)
            emit(it)
        }
    }.map {
        it * it // multiply numbers
    }.flowOn(Dispatchers.IO)*/

    // flowOf() - It is used to create flow from a given set of values.
    /*flow = flowOf(1, 2, 3, 4, 5)
        .onEach {
            delay(500)
        }.flowOn(Dispatchers.IO)*/

    // asFlow() - It is an extension function that helps to convert type into flows.
    /* flow = (1..5).asFlow()
         .onEach {
             delay(500)
         }.flowOn(Dispatchers.IO)*/

    // channelFlow{} - This builder creates cold-flow with the elements using send provided by the builder itself.
    /*flow = channelFlow {
        (1..5).forEach {
            delay(500)
            send(it)
        }
    }.flowOn(Dispatchers.IO)*/
}

// Step2: collect values from flow
/*
fun collectFlowValues() = runBlocking {
    setupFlow()
    flow.collect {
        println(it)
    }
}*/

// Step3: Zip Operator - used for combine two flow builder values
lateinit var flowOne: Flow<String>
lateinit var flowTwo: Flow<String>
fun zipOperatorFlow() {
    flowOne = flowOf("Abdul", "Tarique", "Deepu")
        .onEach {
            delay(500)
        }.flowOn(Dispatchers.IO)
    flowTwo = flowOf("Khalid", "Anwar", "Verma")
        .onEach {
            delay(500)
        }.flowOn(Dispatchers.IO)
}

fun collectZipOperatorValues() = runBlocking {
    zipOperatorFlow()
    flowOne.zip(flowTwo) { firstString, secondString ->
        "$firstString $secondString"
    }.collect {
        println(it)
    }
}