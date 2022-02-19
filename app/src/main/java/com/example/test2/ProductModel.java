package com.example.test2;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {
    private String bName;
    private String oName;
    private String bAddress;
    private String bNum;
    private String bEmail;
    private String dPicker;
    private String productID;

    public ProductModel() {
    }

    public ProductModel(String bName, String oName, String bAddress, String bNum, String bEmail, String dPicker, String productID) {
        this.bName = bName;
        this.oName = oName;
        this.bAddress = bAddress;
        this.bNum = bNum;
        this.bEmail = bEmail;
        this.dPicker = dPicker;
        this.productID = productID;
    }

    protected ProductModel(Parcel in) {
        bName = in.readString();
        oName = in.readString();
        bAddress = in.readString();
        bNum = in.readString();
        bEmail = in.readString();
        dPicker = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getbAddress() {
        return bAddress;
    }

    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
    }

    public String getbNum() {
        return bNum;
    }

    public void setbNum(String bNum) {
        this.bNum = bNum;
    }

    public String getbEmail() {
        return bEmail;
    }

    public void setbEmail(String bEmail) {
        this.bEmail = bEmail;
    }

    public String getdPicker() {
        return dPicker;
    }

    public void setdPicker(String dPicker) {
        this.dPicker = dPicker;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bName);
        parcel.writeString(oName);
        parcel.writeString(bAddress);
        parcel.writeString(bNum);
        parcel.writeString(bEmail);
        parcel.writeString(dPicker);
        parcel.writeString(productID);
    }
}
