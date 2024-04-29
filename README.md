# Java 22 Coffee Machine Simulator

This is a Java portfolio project built with IntelliJ.  The implementation uses [finite-state automation](https://en.wikipedia.org/wiki/Finite-state_machine).

## User actions

### Main Menu
Upon starting program user is prompted with an action menu.
![main menu](images\main_menu.png)

### buy one of 3 different drink recipes
![buy options](images\buy_options.png)

### if there is not enough ingredients to make drink user will be notified
![not enough resources](images\not_enough_resources.png)

### user may cancel `buy` command with `back` command

Using back durring a buy simply ends the buy process and returns action menu; does not trigger `undo`
![stop purchasae](images\buy_stop.png)

### Refill ingredients

To check ingredients use `remaining` command.  In this example you can see after
a fill command is used the ingredients change.

![refill ingredients](images\refill.png)

### remove cash from the register

This is not a robbery.  As the purveyor of fine Java you need ability to take
cash from the register.

![take money](images\take.png)

- undo actions using `back` command
  - revert a `fill` command
  - revert a `buy` purchase
  - revert a `take` command

![undo take command](images\undo.png)

- exit the simulator using `exit` command

## Ideas For Enhancements

1. Error Handling in selectCoffee: Using Integer.parseInt(input) can throw a NumberFormatException if the input isn't a valid integer. It's good to wrap this in a try-catch block to handle unexpected inputs gracefully.

2. Enhanced User Feedback: Provide feedback on why an action failed or was canceled can improve user experience. For instance, explaining why a selection is invalid can guide users on what inputs are expected.

3. Refactoring for Readability: Consider separating concerns further by isolating functionalities like input handling, making it more modular and easier to manage changes.

4. Handling Edge Cases: Ensure that all edge cases are handled, such as running out of any of the resources or unexpected inputs at any point of interaction.