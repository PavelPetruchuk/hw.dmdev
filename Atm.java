//Создать класс, описывающий банкомат.+
//Банкомат задается тремя свойствами:+
//- количеством купюр номиналом 20, 50 и 100.+
//Сделать методы для добавления денег в банкомат+
//Сделать метод, снимающий деньги: он принимает сумму денег, а возвращает булево значение - успешность выполнения операции.
//При снятии денег метод должен распечатывать каким количеством купюр какого номинала выдаётся сумма.
//Создать конструктор с тремя параметрами - количеством купюр каждого номинала.
package Opp;

public class Atm {
    private final int TWENTY = 20;
    private final int FIFTY = 50;
    private final int ONEHUNDRED = 100;

    int[] Banknotes = {ONEHUNDRED, FIFTY, TWENTY};

    private int twentyNominationQuantity;
    private int fiftyNominationQuantity;
    private int oneHundredNominationQuantity;

    public void setTwentyNominationQuantity(int twentyNominationQuantity) {
        this.twentyNominationQuantity = twentyNominationQuantity;
    }

    public void setFiftyNominationQuantity(int fiftyNominationQuantity) {
        this.fiftyNominationQuantity = fiftyNominationQuantity;
    }

    public void setOneHundredNominationQuantity(int oneHundredNominationQuantity) {
        this.oneHundredNominationQuantity = oneHundredNominationQuantity;
    }


    public int getTwentyNominationQuantity() {
        return twentyNominationQuantity;
    }

    public int getFiftyNominationQuantity() {
        return fiftyNominationQuantity;
    }


    public int getOneHundredNominationQuantity() {
        return oneHundredNominationQuantity;
    }


    public Atm(int twentyNominationQuantity, int fiftyNominationQuantity, int oneHundredNominationQuantity) {
        this.twentyNominationQuantity = twentyNominationQuantity;
        this.fiftyNominationQuantity = fiftyNominationQuantity;
        this.oneHundredNominationQuantity = oneHundredNominationQuantity;
    }


    public void putMoney(int inputTwentyNominationQuantity, int inputFiftyNominationQuantity,
                         int inputOneHundredNominationQuantity) {

        twentyNominationQuantity+=inputTwentyNominationQuantity;
        fiftyNominationQuantity+=inputFiftyNominationQuantity;
        oneHundredNominationQuantity+=inputOneHundredNominationQuantity;
    }


    private int getNominationQuantity(int nomination) {
        return switch (nomination) {
            case TWENTY -> twentyNominationQuantity;
            case FIFTY -> fiftyNominationQuantity;
            case ONEHUNDRED -> oneHundredNominationQuantity;
            default -> 0;
        };
    }

    private void setNominationQuantity(int nomination, int value) {
        switch (nomination) {
            case TWENTY -> this.setTwentyNominationQuantity(value);
            case FIFTY -> this.setFiftyNominationQuantity(value);
            case ONEHUNDRED -> this.setOneHundredNominationQuantity(value);
            default -> {
            }
        }
    }

    private int getCurrentSum() {
        int twenty = twentyNominationQuantity * TWENTY;
        int fifty = fiftyNominationQuantity * FIFTY;
        int oneHundred = oneHundredNominationQuantity * ONEHUNDRED;
        return twenty + fifty + oneHundred;
    }

    public boolean withdrawMoney(int withdrawSum) {
        int[] withdrawNominationAmount = new int[Banknotes.length];
        int withdrawBalance = withdrawSum;
        int currentSum = this.getCurrentSum();
        if (withdrawBalance > currentSum) {
            return false;
        }
        for (int i = 0; i < Banknotes.length; i++) {
            int withdrawNominationAmountNeeded = withdrawBalance / Banknotes[i];
            int quantity = this.getNominationQuantity(Banknotes[i]);
            if (quantity < withdrawNominationAmountNeeded) {
                withdrawNominationAmountNeeded = quantity;
            }
            withdrawNominationAmount[i] = withdrawNominationAmountNeeded;
            withdrawBalance -= withdrawNominationAmountNeeded * Banknotes[i];
            if (withdrawBalance == 0) {
                break;
            }
        }
        if (withdrawBalance != 0) {
            return false;
        }
        for (int i = 0; i < Banknotes.length; i++) {
            int quantity = this.getNominationQuantity(Banknotes[i]);
            System.out.println("Выдается " + withdrawNominationAmount[i] + " купюры номиналом " + Banknotes[i]);
            this.setNominationQuantity(Banknotes[i], quantity - withdrawNominationAmount[i]);
        }
        return true;

    }
}




