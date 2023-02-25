package datastructures;

import java.util.Arrays;

public class DynamicString { 
    DynamicArray<Character> data;

    public DynamicString(String str) { 
        data =  new DynamicArray<Character>();
        for (char chr : str.toCharArray())
            data.add(chr);
    }

    public DynamicString() { 
        this("");
    }

    public void append(String str) {
        for (char chr : str.toCharArray())
            data.add(chr);
    } 

    public char charAt(int index) {
        return data.get(index);
    } 

    public int indexOf(String str) {
        for (int i = 0; i < data.size() - str.length() + 1; i++) { 
            if (str.equals(this.substring(i, i + str.length())))
                    return i;
        }
        return -1;
    }

    /* return updated data as String */
    public String delete(int start, int end) {
        DynamicArray<Character> newData =  new DynamicArray<Character>();
        for (int k = 0; k < data.size(); k++)  {
            if (k >= start && k < end)
                continue;
            newData.add(data.get(k));
        }
        data = newData;
        return this.toString(); 
    }

    public String delete(int index) {
        return delete(index, index + 1);
    }

    public int length() {
        return data.size();
    }

    public String substring(int start, int end) {
        char[] subStr = new char[end - start];
        for (int i = 0; i < subStr.length; i++)
            subStr[i] = (char)data.get(start + i);  
        return String.valueOf(subStr);
    }

    @Override
    public String toString() {
        return String.valueOf(getCopyData()); 
    }


    public char[] getCopyData() { 
        Object[] chars = data.copyData();
        char[] temp = new char[chars.length];
        for (int i = 0; i < temp.length; i++)
            temp[i] = (char)chars[i];
        return temp;
    }
}
