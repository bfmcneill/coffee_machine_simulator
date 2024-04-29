# Coffee Machine Simulator

This coffee machine simulator will let you do the following:
- buy 3 different drink recipes
- refill ingredients so drinks can be made
- check ingredient levels
- remove cash from the register
- undo actions using `back` command
  - revert a `fill` command
  - revert a `buy` purchase
  - revert a `take` command
- cancel `buy` command with `back` command
  - simply ends the buy process and returns action menu; does not trigger `undo`
- exit the simulator

## Ideas For Enhancements

1. Error Handling in selectCoffee: Using Integer.parseInt(input) can throw a NumberFormatException if the input isn't a valid integer. It's good to wrap this in a try-catch block to handle unexpected inputs gracefully.

2. Enhanced User Feedback: Provide feedback on why an action failed or was canceled can improve user experience. For instance, explaining why a selection is invalid can guide users on what inputs are expected.

3. Refactoring for Readability: Consider separating concerns further by isolating functionalities like input handling, making it more modular and easier to manage changes.

4. Handling Edge Cases: Ensure that all edge cases are handled, such as running out of any of the resources or unexpected inputs at any point of interaction.