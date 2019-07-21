package ru.otus.hw03.lombok;

public class Main {
    public static void main(String[] args){
        Contact pupkin = new Contact( 1,"Ivan", "Pupkin");
        System.out.println( pupkin.toString());

        ContactList contractList = new ContactList();
        contractList.addContact(pupkin);

        System.out.println(contractList.getContactFirstNameById(1));

        System.out.println(contractList.getContactLastNameById(1));

        System.out.println(contractList.getContactFirstNameById(2));
    }
}
