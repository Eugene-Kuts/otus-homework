package ru.otus.hw03.lombok;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@NoArgsConstructor
public class ContactList {
    private List<Contact> contactList = new ArrayList<>();
/*
    public void addContact(int id, String name, String lastName){
        if(!hasContact(new Contact(id, name, lastName)))
            this.contactList.add(new Contact(id, name, lastName));
    }*/

    public void addContact(Contact contactToAdd){
        if(!hasContact(contactToAdd))
            this.contactList.add(contactToAdd);
    }

    public String getContactFirstNameById(int id) {
        for (Contact cont : contactList){
            if (cont.getId() == id){
                return cont.getFirstName();
            }
        }
        throw new UnsupportedOperationException("Нет пользователя c id= " + id);
    }

    public String getContactLastNameById(int id)  {
        for (Contact cont : contactList){
            if (cont.getId() == id){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return cont.getLastName();
            }
        }
        throw new UnsupportedOperationException("Нет пользователя c id= " + id);
    }

    private boolean hasContact(Contact newCont){
        for (Contact cont : contactList){
            if (cont.equals(newCont) && cont.hashCode() == newCont.hashCode()){
                return true;
            }
        }
        return false;
    }
}
