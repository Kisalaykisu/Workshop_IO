package com.bridgelabz;

import java.util.*;

public class AddressBook {
    static String name;
    static boolean is_Running = false;
    public HashMap<String, ContactInfo> addressBook;
    public HashMap<String, AddressBook> multiAdressBook = new HashMap<>();


    public AddressBook() {
        addressBook = new HashMap<>();
    }

    //Driver code
    public static void main(String[] args) {
        System.out.println("Welcome to the ADDRESS BOOK");

        AddressBook obj=new AddressBook();

        AddressBook addressBookObj1 = new AddressBook();
        AddressBook addressBookObj2 = new AddressBook();
        AddressBook addressBookObj3 = new AddressBook();
        obj.multiAdressBook.put("AB1", addressBookObj1);
        obj.multiAdressBook.put("AB2", addressBookObj2);
        obj.multiAdressBook.put("AB3", addressBookObj3);

        while (!is_Running) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 1 ,2 ,3 for diff addressBook and 4 to exit");
            int option = scanner.nextInt();
            String key = null;
            switch (option) {
                case 1:
                    key = "AB1";
                    break;
                case 2:
                    key = "AB2";
                    break;
                case 3:
                    key = "AB3";
                    break;
            }
            if (option == 4) break;
            System.out.println(" Enter 1 to create a new contact \n 2 to exit \n 3 to edit existing contact \n 4 to delete an existing contact");
            int choice = scanner.nextInt();
            if (choice == 1) {
                ContactInfo contact = new ContactInfo();
                contact.setContactInfo();
                name = contact.firstName.toUpperCase(Locale.ROOT) + " " + contact.lastName.toUpperCase(Locale.ROOT);
                if (obj.multiAdressBook.get(key).addressBook.keySet().stream().noneMatch(k -> k.equals(name))){                 //JAVA STREAMS is used to check if any duplicate
                    obj.multiAdressBook.get(key).addressBook.put(name, contact);                                                //contact already exist in the addressBook
                    obj.multiAdressBook.get(key).addressBook.get(name).displayContactInfo();
                }
                else System.out.println("Contact already exist duplicate not allowed");
            } else if (choice == 2) {
                is_Running = true;
            } else if (choice == 3) {
                obj.multiAdressBook.get(key).editContact();
            } else if (choice == 4) {
                obj.multiAdressBook.get(key).deleteContact();
            }
        }
        obj.searchContactBasedOnCity();
    }

    /**
     * Method to delete an existing contact
     */
    public void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first and last name of the contact you want to delete from AddressBook: ");
        String name = scanner.nextLine().toUpperCase(Locale.ROOT);
        if (addressBook.containsKey(name)) {
            addressBook.remove(name);
            System.out.println("Contact removed");
        } else
            System.out.println("Contact not found");
    }

    /**
     * Method to edit an existing contact
     */
    public void editContact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your first name and Last name  : ");
        String name = sc.nextLine().toUpperCase(Locale.ROOT);
        if (addressBook.containsKey(name)) {
            System.out.println("Enter the number you want to edit\n1.Address\n2.City\n3.State\n4.Zipcode\n5.Phone Number\n6.Email");
            int number = sc.nextInt();
            sc.nextLine();
            switch (number) {
                case 1 -> {
                    System.out.println("Enter new Address");
                    addressBook.get(name).setAddress(sc.nextLine());
                }
                case 2 -> {
                    System.out.println("Enter new City");
                    addressBook.get(name).setCity(sc.nextLine());
                }
                case 3 -> {
                    System.out.println("Enter new State");
                    addressBook.get(name).setState(sc.nextLine());
                }
                case 4 -> {
                    System.out.println("Enter new ZipCode");
                    addressBook.get(name).setZipcode(sc.nextLine());
                }
                case 5 -> {
                    System.out.println("Enter new Phone number");
                    addressBook.get(name).setPhoneNo(sc.nextLine());
                }
                case 6 -> {
                    System.out.println("Enter new Email");
                    addressBook.get(name).setEmail(sc.nextLine());
                }
                default -> System.out.println("Please input a valid number (1-6)");
            }
            addressBook.get(name).displayContactInfo();
        } else System.out.println("Contact not found");
    }

    /**
     * UC8 Method
     */
    public void searchContactBasedOnCity(){

        ArrayList<String> persons = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the city to search the contacts based on city");
        String searchCity = scanner.nextLine();

        for (Map.Entry<String,AddressBook> addressBookEntry: multiAdressBook.entrySet()) {
            addressBookEntry.getValue();
            System.out.println(addressBookEntry.getKey());
            for (Map.Entry<String,ContactInfo> contactEntry: addressBookEntry.getValue().addressBook.entrySet()) {
                String result = addressBookEntry.getValue().addressBook.get(contactEntry.getKey()).showContact();
                System.out.println(contactEntry.getKey());
                if (result.contains(searchCity)){
                    System.out.println("person = "+contactEntry.getKey());
                    persons.add(contactEntry.getKey());
                }
            }
        }
        System.out.println("List of persons in the required city are: "+persons);
    }
}

/**
 * Template class for creating a contact
 */
class ContactInfo {
    String firstName, lastName, address, city, state, zipcode, phoneNo, email;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to save contact details
     */
    public void setContactInfo() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter First Name: \n Last Name: \n Address: \n City: \n State: \n Zipcode: \n PhoneNO: \n Email: \n");
        setFirstName(sc.nextLine());
        setLastName(sc.nextLine());
        setAddress(sc.nextLine());
        setCity(sc.nextLine());
        setState(sc.nextLine());
        setZipcode(sc.nextLine());
        setPhoneNo(sc.nextLine());
        setEmail(sc.nextLine());
    }

    /**
     * Method to display the contact details
     */
    public void displayContactInfo() {
        System.out.print(" First Name: " + firstName + "\n Last Name: " + lastName + "\n Address: " + address +
                "\n City: " + city + "\n State: " + state + "\n Zipcode: " + zipcode + "\n PhoneNO: " + phoneNo + "\n Email: " + email + "\n");
    }

    public String showContact(){
        return  " First Name: " + firstName + "\n Last Name: " + lastName + "\n Address: " + address +
                "\n City: " + city + "\n State: " + state + "\n Zipcode: " + zipcode + "\n PhoneNO: " + phoneNo + "\n Email: " + email + "\n";
    }
}