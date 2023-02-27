package com.example.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{

        val exerciseList= ArrayList<ExerciseModel>()

        val lunge= ExerciseModel(
            1,
            "Lunge",
            R.drawable.lunge,
            false,
            false
        )

        exerciseList.add(lunge)

        val plank= ExerciseModel(
            2,
            "Plank",
            R.drawable.plank,
            false,
            false
        )

        exerciseList.add(plank)
        val push_up= ExerciseModel(
            3,
            "Push Up",
            R.drawable.push_up,
            false,
            false
        )

        exerciseList.add(push_up)

        val side_plank= ExerciseModel(
            4,
            "Side Plank",
            R.drawable.side_plank,
            false,
            false
        )

        exerciseList.add(side_plank)
        val squat= ExerciseModel(
            5,
            "Squat",
            R.drawable.squat,
            false,
            false
        )

        exerciseList.add(squat)
        val step_up_onto_chair= ExerciseModel(
            6,
            "Step up onto Chair",
            R.drawable.step_up_onto_chair,
            false,
            false
        )

        exerciseList.add(step_up_onto_chair)
        val triceps_dip_on_chair= ExerciseModel(
            7,
            "Triceps Dip on Chair",
            R.drawable.triceps_dip_on_chair,
            false,
            false
        )

        exerciseList.add(triceps_dip_on_chair)
        val wall_sit= ExerciseModel(
            8,
            "Wall Sit",
            R.drawable.wall_sit,
            false,
            false
        )

        exerciseList.add(wall_sit)

        return exerciseList
    }
}