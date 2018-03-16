package com.example.user_pc.lab06;

public class ListViewItem {
    private String name;
    private String tel;
    private String email;
    private String address;

    public ListViewItem () { }

    public void setName(String name) {
        this.name = name;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public String getTel() {
        return tel;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }


}
