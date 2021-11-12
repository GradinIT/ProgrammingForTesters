package exercise.io;

import lombok.Builder;
import lombok.ToString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjektIO {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("objects.obj")));
        for(int i = 1 ; i < 11 ; i++) {
            objectOutputStream.writeObject(Data.builder().value(i).build());
        }
        objectOutputStream.writeObject(null);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("objects.obj")));
        Data data = (Data)objectInputStream.readObject();
        while( data != null ) {
            System.out.println(data);
            data = (Data)objectInputStream.readObject();
        }
    }
    @Builder
    @ToString
    static class Data implements Serializable {
        private final Integer value;
    }
}
