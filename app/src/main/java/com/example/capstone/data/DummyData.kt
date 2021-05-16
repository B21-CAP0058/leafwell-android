package com.example.capstone.data

import com.example.capstone.R

//For testing purpose. You can delete it when it is not useful anymore
object DummyData {
    fun generateDummyData():List<DummyEntity>{
        val dummyData = ArrayList<DummyEntity>()

        dummyData.add(
            DummyEntity(
                1,
                "Title Tanaman 1",
                "Sub_title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.plant
            )
        )
        dummyData.add(
            DummyEntity(
                1,
                "Title Tanaman 2",
                "Sub_title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.plant
            )
        )
        dummyData.add(
            DummyEntity(
                3,
                "Title Tanaman 3",
                "Sub_title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.plant
            )
        )

        dummyData.add(
            DummyEntity(
                4,
                "Title Tanaman 4",
                "Sub_title",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.plant
            )
        )

        return dummyData
    }
}