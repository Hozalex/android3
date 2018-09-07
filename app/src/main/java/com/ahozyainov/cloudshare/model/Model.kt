package com.ahozyainov.cloudshare.model

class Model {
    private var mList = arrayListOf<Int>()

    init {
        mList.add(0)
        mList.add(0)
        mList.add(0)
    }

    fun getElementValue(index: Int): Int {
        return mList[index]
    }

    fun setElementValue(index: Int, value: Int) {
        mList[index] = value
    }
}