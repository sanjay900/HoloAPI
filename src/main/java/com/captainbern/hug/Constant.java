package com.captainbern.hug;

public class Constant {

    // Constant op-codes
    public static final byte CONSTANT_Utf8                  = 1;
    public static final byte CONSTANT_Integer               = 3;
    public static final byte CONSTANT_Float                 = 4;
    public static final byte CONSTANT_Long                  = 5;
    public static final byte CONSTANT_Double                = 6;
    public static final byte CONSTANT_Class                 = 7;
    public static final byte CONSTANT_String                = 8;
    public static final byte CONSTANT_Fieldref              = 9;
    public static final byte CONSTANT_Methodref             = 10;
    public static final byte CONSTANT_InterfaceMethodref    = 11;
    public static final byte CONSTANT_NameAndType           = 12;
    public static final byte CONSTANT_MethodHandle          = 15;
    public static final byte CONSTANT_MethodType            = 16;
    public static final byte CONSTANT_InvokeDynamic         = 18;

    private byte type;
    private int index;
    private byte[] data;

    public Constant(byte type, int index, byte[] data) {
        this.type = type;
        this.index = index;
        this.data = data;
    }

    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public byte[] getRawData() {
        return this.data;
    }

    public void setRawData(byte[] data) {
        this.data = data;
    }

    public byte[] getBytes() {
        if (this.type == CONSTANT_Utf8) {
            return Bytes.rMerge(Bytes.merge(new byte[]{(byte) (this.data.length >> 8), (byte) this.data.length}, this.data), this.type);
        }
        return Bytes.rMerge(this.data, this.type);
    }

    public String rawStringValue() {
        try {
            switch (this.type) {
                case CONSTANT_Utf8:
                    return new String(this.data, "UTF-8");
                default:
                    return this.data.toString();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to return the raw string value of: " + Integer.toHexString(this.type));
        }
    }
}