package machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeMachine {
    private enum AppState {MAIN_MENU, BUY, FILL, TAKE, REMAINING, BACK, EXIT}

    private enum DrinkChoice {ESPRESSO, LATTE, CAPPUCCINO}

    private List<CoffeeMachineHistory> history;
    private AppState appState;
    private final Scanner scanner;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;

    public CoffeeMachine(int water, int milk, int coffeeBeans, int disposableCups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.disposableCups = disposableCups;
        this.money = money;
        this.scanner = new Scanner(System.in);
        this.appState = AppState.MAIN_MENU;
        this.history = new ArrayList<>();
    }

    public void start() {
        while (true) {
            switch (appState) {
                case MAIN_MENU:
                    printMainMenu();
                    break;
                case BUY:
                    DrinkChoice selectedDrink = selectCoffee();
                    if (selectedDrink != null) {
                        captureState(); // Capture state only after confirming a valid selection
                        buyCoffee(selectedDrink);
                    }
                    appState = AppState.MAIN_MENU;
                    break;
                case FILL:
                    captureState();
                    fillMachine();
                    appState = AppState.MAIN_MENU;
                    break;
                case TAKE:
                    captureState();
                    takeMoney();
                    appState = AppState.MAIN_MENU;
                    break;
                case REMAINING:
                    printInventory();
                    appState = AppState.MAIN_MENU;
                    break;
                case BACK:
                    goBack();
                    appState = AppState.MAIN_MENU;
                    break;
                case EXIT:
                    return;
            }
        }
    }

    private void goBack() {
        if (!history.isEmpty()) {
            CoffeeMachineHistory lastState = history.removeLast();
            water = lastState.water;
            milk = lastState.milk;
            coffeeBeans = lastState.coffeeBeans;
            disposableCups = lastState.disposableCups;
            money = lastState.money;
            //System.out.println("Reverted to previous state.");
        } else {
            //System.out.println("No history to revert back to.");
            return;
        }
    }

    private void printMainMenu() {
        System.out.println("\nWrite action (buy, fill, take, remaining, back, exit):");
        String input = scanner.next().toUpperCase();

        switch (input) {
            case "BUY":
                appState = AppState.BUY;
                break;
            case "FILL":
                appState = AppState.FILL;
                break;
            case "TAKE":
                appState = AppState.TAKE;
                break;
            case "REMAINING":
                appState = AppState.REMAINING;
                break;
            case "BACK":
                appState = AppState.BACK;
                break;
            case "EXIT":
                appState = AppState.EXIT;
                break;
            default:
                System.out.println("Invalid action.");
        }
    }

    public void printInventory() {
        System.out.print("\nThe coffee machine has:");
        System.out.printf("\n%d ml of water", water);
        System.out.printf("\n%d ml of milk", milk);
        System.out.printf("\n%d g of coffee beans", coffeeBeans);
        System.out.printf("\n%d disposable cups", disposableCups);
        System.out.printf("\n$%d of money", money);
    }

    private void buyCoffee(DrinkChoice selectedDrinkChoice) {

        switch (selectedDrinkChoice) {
            case ESPRESSO:
                makeCoffee(250, 0, 16, 4);
                break;
            case LATTE:
                makeCoffee(350, 75, 20, 7);
                break;
            case CAPPUCCINO:
                makeCoffee(200, 100, 12, 6);
                break;
            default:
                System.out.println("invalid selection");
        }

    }

    private DrinkChoice selectCoffee() {

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - stop buying process:");

        String input = scanner.next().trim();

        // Check if the user wants to cancel buy order
        if ("back".equalsIgnoreCase(input)) {
            System.out.println("Cancelling purchase...");
            return null;
        }

        int choiceInput = Integer.parseInt(input);
        boolean isValidSelection = (choiceInput >= 1 && choiceInput <= DrinkChoice.values().length);

        if (!isValidSelection) {
            //System.out.println("invalid selection");
            return null;
        }

        DrinkChoice[] drinkChoices = DrinkChoice.values();
        return drinkChoices[choiceInput - 1];
    }

    private void makeCoffee(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int cost) {
        if (water >= waterNeeded && milk >= milkNeeded && coffeeBeans >= coffeeBeansNeeded && disposableCups > 0) {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= waterNeeded;
            this.milk -= milkNeeded;
            this.coffeeBeans -= coffeeBeansNeeded;
            this.disposableCups -= 1;
            this.money += cost;
        } else {

            if (water < waterNeeded) {
                System.out.println("Sorry, not enough water!");
            }

            if (milk < milkNeeded) {
                System.out.println("Sorry, not enough milk!");
            }

            if (coffeeBeans < coffeeBeansNeeded) {
                System.out.println("Sorry, not enough coffee beans!");
            }

            if (disposableCups == 0) {
                System.out.println("Sorry, not enough cups!");
            }
        }
    }

    public void fillMachine() {

        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        disposableCups += scanner.nextInt();
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d\n\n", money);
        money = 0;
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        machine.start();
    }

    private void captureState() {
        history.add(new CoffeeMachineHistory(water, milk, coffeeBeans, disposableCups, money));
    }

    private static class CoffeeMachineHistory {
        private final int water;
        private final int milk;
        private final int coffeeBeans;
        private final int disposableCups;
        private final int money;

        public CoffeeMachineHistory(int water, int milk, int coffeeBeans, int disposableCups, int money) {
            this.water = water;
            this.milk = milk;
            this.coffeeBeans = coffeeBeans;
            this.disposableCups = disposableCups;
            this.money = money;
        }

        @Override
        public String toString() {
            return String.format("Water: %d, Milk: %d, Coffee Beans: %d, Cups: %d, Money: $%d",
                    water, milk, coffeeBeans, disposableCups, money);
        }
    }
}
