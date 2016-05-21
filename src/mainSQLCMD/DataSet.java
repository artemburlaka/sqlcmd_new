package mainSQLCMD;

import java.util.Arrays;

/**
 * Created by admin on 09.05.2016.
 */
public class DataSet {

    static  class Data {
        private String name;
        private Object value;

        public Data(String name, Object value) {

            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }
    }

    public Data[] data = new Data[100]; //TODO remove magic number 100
    public  int freeIndex = 0;

    public void put(String name, Object value) {
        data[freeIndex++] = new Data(name, value);
    }

    public Object[] getValue() {
        Object[] result = new Object[freeIndex];
        for (int i = 0; i < freeIndex; i++) {
            result[i] = data[i].getValue();
        }
        return result;
    }

    public String[] getNames() {
        String[] result = new String[freeIndex];
        for (int i = 0; i < freeIndex; i++) {
            result[i] = data[i].getName();
        }
        return result;
    }

    public Object get(String name) {
        for (int i = 0; i < freeIndex; i++) {
            if (data[i].getName().equals(name)) {
                return data[i].getValue();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "DataSet{\n" +
                "names: " + Arrays.toString(getNames()) + "\n" +
                "values: " + Arrays.toString(getValue()) + "\n" +
                "}";
    }
}
