/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contact.list;

/**
 *
 * @author mahmud
 */
public class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String group;

    public Contact(String name, String phoneNumber, String email, String address, String group) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGroup() {
        return group;
    }
    public void showcontact(){
        System.out.println(this.name);
        System.out.println(this.phoneNumber);
        System.out.println(this.email);
        System.out.println(this.address);
        System.out.println(this.group);
    }

    @Override
    public String toString() {
        return "" + name + "\n" + phoneNumber + "\n" + email + "\n" + address + "\n" + group +"\n";
    }
    
}
