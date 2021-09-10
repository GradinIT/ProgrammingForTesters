## Vad är en Class

* En class är javas minsta körbara ( exekverbara enhet)
* En typisk class defineras enligt
```java
public class MyClass {
    
}
```
* Klassen ovan är public vilket betyder att den måste ligga i en fil som heter MyClass.java
* En Class är en definition av en dataTyp
* I Klassen ovan definerar vi alltså en datatyp som heter MyClass och är av typen MyClass
* En class kan ha variabler(värden) och metoder(funktioner)
```java 
public class MyClass {

    private Integer myIntegerValue; // det här är en variabel
    
    /**
     * nedan har vi en metod
     */
    public getMyIntegerValue() {  
        return this.myIntegerValue;
    }
}
```
* Nu ovan har vi lagt till en variabel och en metod 
