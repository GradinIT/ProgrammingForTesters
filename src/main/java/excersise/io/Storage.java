package excersise.io;

public class Storage {

    private Integer[] objects;


    public Storage() {
        objects = new Integer[5];
    }

    public int getValue(int ind) {
        return objects[ind].intValue();
    }

    public void put(int ind, int val) {
        if (objects[ind] == null) {
            objects[ind] = val;
        }
        //else {
            //throw StorageException.class.cast(Throwable.class);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Integer object : objects) {
            res.append(object).append(" ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Storage s = new Storage();

        System.out.println(s);
        s.put(2, 67);
        System.out.println(s);
        s.put(3, 2549);
        System.out.println(s);
        s.put(2, 35);
        System.out.println(s);

        int i = 3;
        try {
            System.out.println("Value on position " + i + ": " +
                    s.getValue(i));
        } catch (NullPointerException e) {
            System.out.println("No object is stored on position " +
                    i + "!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index: " +
                    i + "!");
        }
                System.out.println(s);
    }

}