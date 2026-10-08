data class Habit (
    val id: Int,
    val name: String,
    var isCompleted: Boolean = false,
    var streak: Int = 0
)

fun main() {
    println("---Habit Tracker---")

    var nextId = 1
    val myHabits = mutableListOf<Habit>()

    while (true) {
        println("\nChoose an option:")
        println("1. View/Add Habits")
        println("2. Complete a Habit (Check Off)")
        println("3. Remove Habit")
        println("4. Exit")
        print("Enter choice: ")

        val choice = readln()

        if (choice == "1") {
            // View current habits or add one
            if (myHabits.isEmpty()) {
                println("\nYour tracker is empty!")
            } else {
                println("\nYour Habits:")
                myHabits.forEach { habit ->
                    val checkbox = if (habit.isCompleted) "X" else " "
                    println("[${checkbox}] ID ${habit.id}: ${habit.name} (Streak: ${habit.streak} days 🔥)")
                }
            }

            print("\nWant to add a new habit? (yes/no): ")
            val answer = readln().trim().lowercase()

            if (answer == "yes" || answer == "y") {
                print("Enter habit name: ")
                val name = readln()
                if (name.isNotBlank()) {
                    myHabits.add(Habit(id = nextId, name = name))
                    println("Added \"$name\" successfully!")
                    nextId++
                }
            }
        } // <-- FIXED: This brace now correctly closes Choice 1!

        else if (choice == "2") {
            if (myHabits.isEmpty()) {
                println("You don't have any habits to check off yet! Go add some first.")
                continue
            }

            println("\n--- Your Habits ---")
            myHabits.forEach { habit ->
                val checkbox = if (habit.isCompleted) "X" else " "
                println("[${checkbox}] ID ${habit.id}: ${habit.name} (Streak: ${habit.streak} days 🔥)")
            }

            print("\nEnter the ID or Name of the habit you completed: ")
            val userInput = readln().trim()

            // Find habit
            val habitToComplete = myHabits.find { habit ->
                // FIXED: Changed to Kotlin's standard .equals(..., ignoreCase = true)
                habit.id.toString() == userInput || habit.name.equals(userInput, ignoreCase = true)
            }

            if (habitToComplete != null) {
                habitToComplete.isCompleted = true
                habitToComplete.streak += 1
                println("\nNice job! Checked off \"${habitToComplete.name}\". Streak updated to ${habitToComplete.streak} days! 🎉")
            } else {
                println("\nCould not find a habit matching \"$userInput\". Please try again.")
            }
        }

        else if (choice == "3") {
            // Remove a habit from the tracking list
            if (myHabits.isEmpty()) {
                println("No habits to remove.")
                continue
            }
            print("Enter the ID of the habit to remove: ")
            val inputId = readln().toIntOrNull()

            // Remove the item if the condition matches
            val removed = myHabits.removeIf { it.id == inputId }
            if (removed) {
                println("Habit successfully removed.")
            } else {
                println("Invalid ID. No habit was removed.")
            }
        }

        else if (choice == "4") {
            println("Goodbye! Keep building those streaks! 👋")
            break
        }

        else {
            println("Invalid selection. Please choose 1, 2, 3, or 4.")
        }
    }
}