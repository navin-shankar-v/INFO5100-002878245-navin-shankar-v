import java.time.LocalTime;
import java.util.UUID;

import static java.lang.System.out;

//Class 1
class Car{
    UUID carId;
    String brandName;
    String modelName;
    String carType;
    String carColor;
    String gearType;
    int yearMade;
    boolean isCarStart;
    String price;
    public Car(String brandName, String modelName,String carType, String carColor,String gearType,int yearMade,boolean isCarStart,String price){
        this.carId = UUID.randomUUID();
        this.brandName = brandName;
        this.modelName = modelName;
        this.carType = carType;
        this.carColor = carColor;
        this.gearType = gearType;
        this.yearMade = yearMade;
        this.isCarStart = isCarStart;
        this.price = price;
        out.println("Instance of class Car created with Unique Id "+carId);
    }
    public void testDrive(){ out.println("Today, I had the opportunity to test drive the "+modelName+" manufactured by "+brandName+".\nIt features a "+gearType+" transmission and is priced at "+price+". Notably, it boasts a distinctive "+carColor+" color."); }
    public void parkCar(){
        out.println("I parked the car in the garage");
    }
    public void startCar(){
        if (isCarStart){
            out.println("Car is Running");
        }else {
            out.println("Car is not operational");
        }
    }
    //-----First Nested Class Engine Created within Class Car-----
    class Engine{
        UUID carEngineID;
        String engineType;
        String engineOutput;
        public Engine(String engineType,String engineOutput){
            this.carEngineID =UUID.randomUUID();
            this.engineType = engineType;
            this.engineOutput = engineOutput;
            out.println("Instance of class Engine created with Unique Id "+carEngineID);
        }
        public void engineDetails(){ out.println("The "+modelName+" has a "+engineType+" which produces "+engineOutput); }
    }

    //-----Second Nested Class Wheel Created within Class Car-----
    class Wheel{
        UUID carWheelId;
        String wheelRadius;
        String wheelMaterial;
        String wheelColor;
        public Wheel(String wheelRadius,String wheelMaterial, String wheelColor){
            this.carWheelId = UUID.randomUUID();
            this.wheelRadius = wheelRadius;
            this.wheelMaterial = wheelMaterial;
            this.wheelColor = wheelColor;
            out.println("Instance of Nested class Wheel created with Unique Id "+carWheelId);
        }
        public void wheelDetails(){ out.println("The "+modelName+" has a wheel Radius of "+wheelRadius+" which is made up of "+wheelMaterial+" it has unique "+wheelColor+ "Color\n"); }
    }
}

//Class 2
class Sofa{
    UUID sofaId;
    String brand;
    String model;
    String material;
    double length;
    double width;
    int seatingCapacity;
    boolean isRecliner;
    boolean hasArmrest;
    public Sofa( String brand, String model, String material, int seatingCapacity, boolean isRecliner,boolean hasArmrest ,double length, double width){
        this.sofaId = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.material = material;
        this.seatingCapacity = seatingCapacity;
        this.isRecliner = isRecliner;
        this.hasArmrest = hasArmrest;
        this.width = width;
        this.length = length;
        out.println("Instance of class Sofa created with Unique Id "+sofaId);
    }
    public void buySofa(){ out.println("Bought a sofa in "+brand+" made from "+material+" the model name is "+model+" has a seating capacity of "+seatingCapacity); }
    public void sit(){
        out.println("Sitting in Sofa");
    }

    public void adjustSofa(){
        if(hasArmrest){
            out.println("Adjust the length "+length);
            out.println("Adjust width "+width);
            out.println("The adjusted chair has a length of "+length+" and a width of "+width+"\n");
        }else out.println("Doesn't require any adjust in chair \n");
    }
}

//Class 3
class AlarmClock{
    UUID alarmClockId;
    String brand;
    String model;
    String clockColor;
    String sound;
    String snoozeDuration;
    LocalTime timeDisplay;
    boolean hasSnoozeButton;
    boolean isActive;
    public AlarmClock(String brand, String model, String clockColor,LocalTime timeDisplay, boolean hasSnoozeButton){
        this.alarmClockId = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.clockColor = clockColor;
        this.timeDisplay = timeDisplay;
        this.hasSnoozeButton = hasSnoozeButton;
        this.sound = sound;
        this.snoozeDuration = snoozeDuration;
        out.println("Instance of class Alarm clock created with Unique Id "+alarmClockId);
    }
    public void boughtAlarmClock(){ out.println("Bought an alarm clock from "+brand+" the model name is "+model+" its color is "+clockColor); }
    public void snoozeButton(){
        if (hasSnoozeButton){
            out.println("Pressed the snooze button to snooze alarm");
        } else {
            out.println("Has no Snooze button");
        }
    }
    public void activeStatus(){
        if(isActive){
            out.println("The alarm is active \n");
        }else{
            out.println("The alarm is inactive \n");
        }
    }
}
//Class 4
class Book{
    UUID bookId;
    String title;
    String author;
    String genre;
    int yearOfPublication;
    int pageCount;
    String fontStyle;
    int chapterCount;
    boolean hasHardCover;
    float price;
    public Book(String title, String author, String genre, boolean hasHardCover, float price, int yearOfPublication, int pageCount, int chapterCount){
        this.bookId = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.hasHardCover = hasHardCover;
        this.price = price;
        this.yearOfPublication = yearOfPublication;
        this.pageCount = pageCount;
        this.chapterCount = chapterCount;
        this.fontStyle = fontStyle;
        out.println("Instance of class Book created with Unique Id "+bookId);
    }
    public void buyingBook(){ out.println("I purchased a book authored by "+author+" called "+title+". It falls into the genre of "+genre+" and was priced at "+price+". \n It is has "+chapterCount+" and has over "+pageCount+" it was published in the year "+yearOfPublication);}
    public void readingBook(){
        out.println("Im reading the book");
    }
    public void bookCover(){
        if(hasHardCover){
            out.println("The Book has hard cover \n");
        }else   out.println("The Book has no hard cover \n");
    }
}

//Class 5
class Person{
    UUID uniqueId;
    String name;
    int age;
    String gender;
    String number;
    String email;
    double height;
    float weight;
    String ethnicity;
    public Person(String name, String email,String number, int age, String gender, double height, float weight, String ethnicity){
        this.uniqueId = UUID.randomUUID();
        this.name = name;
        this.number=number;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.ethnicity = ethnicity;
        out.println("Instance of Person class created with Unique id " +uniqueId); //Printing a statement along with a Unique Id in constructor to indicate creation of Person instance
    }
    public void introduce(){
        out.println("The person introduced himself as "+name);
    }
    public void sharedNumber(){
        out.println("My mobile number is  " +number);
    }
    public void sendMail(){ out.println("He send a mail to this email address "+email ); }
    public void description(){ out.println("His is a  "+ ethnicity +" "+gender+" he was about "+height+" tall and he must weigh least "+weight+" pounds and his must be between "+age);}

    //Created a Nested class Address within Person Class
    static class Address{
        UUID addressId;
        int houseNumber;
        String streetName;
        String city;
        String state;
        public Address(int houseNumber, String streetName, String city, String state){
            this.addressId = UUID.randomUUID();
            this.houseNumber = houseNumber;
            this.streetName = streetName;
            this.city = city;
            this.state = state;
            out.println("Instance of Nested class Address created with Unique id " +addressId);
        }
        public void getAddressInfo(){ out.println("Got his address information "+houseNumber+" "+streetName+" "+city+" "+state+"\n"); }
    }
}
//class 6
class Backpack{
    UUID backpackId;
    String brand;
    String type;
    String bagColor;
    int numCompartments;
    int noOfZippers;
    boolean hasLaptopPocket;
    boolean isWaterResistant;
    String capacity;
    public Backpack(String brand, String type, String bagColor, int numCompartments, int noOfZippers ,boolean hasLaptopPocket, boolean isWaterResistant, String capacity){
        this.backpackId = UUID.randomUUID();
        this.brand = brand;
        this.type = type;
        this.bagColor = bagColor;
        this.numCompartments =numCompartments;
        this.noOfZippers = noOfZippers;
        this.hasLaptopPocket = hasLaptopPocket;
        this.isWaterResistant = isWaterResistant;
        this.capacity = capacity;
        out.println("Instance of class Backpack created with Unique Id "+backpackId);
    }
    public void foundBag(){ out.println("I found a "+bagColor+" color" +type+" backpack made by "+brand+" and has "+noOfZippers+" zippers"); }
    public void waterResistant(){
        if(isWaterResistant){
            out.println("Backpack is water resistant");
        }else out.println("Back pack is not water resistant");
    }
    public void fillCapacity(){ out.println("The Bag has a capacity of "+capacity+" and i filled in with notes \n"); }
}
//Class 7
class Jacket{
    UUID jacketId;
    String jacketColor;
    String jacketStyle;
    String jacketMaterial;
    String jacketBrand;
    int jacketSize;
    int jacketStock;
    double jacketPrice;
    boolean stockAvailable;

    public Jacket(String jacketColor, String jacketStyle, String jacketMaterial, String jacketBrand, int jacketSize, int jacketStock, double jacketPrice, boolean stockAvailable){
        this.jacketId = UUID.randomUUID();
        this.jacketBrand = jacketBrand;
        this.jacketMaterial = jacketMaterial;
        this.jacketColor = jacketColor;
        this.jacketStyle = jacketStyle;
        this.jacketSize = jacketSize;
        this.jacketStock = jacketStock;
        this.jacketPrice = jacketPrice;
        this.stockAvailable = stockAvailable;
        out.println("Instance of class Coffee created with Unique Id "+jacketId);
    }
    public void jacketTrail(){
        out.println("I went and tried the "+jacketBrand+" jacket crafted from "+jacketMaterial+".\n It came in the shade of "+jacketColor+" and had a price tag of "+jacketPrice+". The jacket boasted a "+jacketStyle+" design.");
    }
    public void stocksAvailable(){
        out.println("The number of Stock available is "+stockAvailable);
    }
    public void stockCheck(){
        if(stockAvailable){
            out.println("Stock is available \n");
        }else out.println("Stock is not available \n");
    }
}

//Class 8
class CoffeeMachine{
    UUID coffeeMachineId;
    String machineName;
    String cmBrandName;
    int machineCapacity;
    int machineWaterLevel;
    String coffeeStrength;
    String coffeeType;
    boolean isOn;
    int coffeesWanted;

    public CoffeeMachine(String machineName, String cmBrandName, String coffeeType, String coffeeStrength, boolean isOn,int coffeesWanted){
        this.coffeeMachineId = UUID.randomUUID();
        this.machineName = machineName;
        this.cmBrandName = cmBrandName;
        this.coffeeType = coffeeType;
        this.coffeeStrength = coffeeStrength;
        this.machineCapacity = 20;
        this.machineWaterLevel = 20;
        this.isOn = isOn;
        this.coffeesWanted = coffeesWanted;
        out.println("Instance of class Coffee created with Unique Id "+coffeeMachineId+".\n");
    }
    public void getNumCoffeeWanted(){
        out.println("The Number of Coffees wanted "+coffeesWanted);
    }
    public void makeCoffee(){
        if(coffeesWanted>machineCapacity){
            out.println("Number of Coffee's wanted exceed max limit. Machine can only make "+machineCapacity+" Coffee's");
        } else if (coffeesWanted==machineCapacity) {
            out.println("Max limit of the machine reached");
        }else{
            if(machineWaterLevel>=coffeesWanted){
                machineWaterLevel-=coffeesWanted;
                machineCapacity-=coffeesWanted;
                out.println("You have requested "+coffeesWanted+" Coffee's");
                out.println("Remaining water level "+machineWaterLevel);
                out.println("Remaining number of coffee's that can be made "+machineCapacity);
            }else {
                out.println("Not enough water to make "+coffeesWanted+" coffee's requested");
            }
        }
    }
    public void madeCoffee(){
        out.println("I made "+coffeesWanted+" coffee's using the coffee machine \n");
    }
}

//Main Class
public class Exercise0 {
    public static void main(String[] args) {

        //-----Object Creation for the classes-----
        Car car1 = new Car("Koenigsegg ","Gemera","Hyper Car","Carbon Green","nine-speed automatic gearbox",2023,true,"3.7 million");
        Car.Engine engine1 = car1.new Engine("2.0 L TFG twin-turbocharged I3 5.0 L twin-turbocharged V8","2,300 Horse power");
        Car.Wheel wheel1 = car1.new Wheel("21 x 10.5 up front and 22 x 11.5 at Back", "Forged Aluminium","Carbon-black");
        car1.testDrive();
        car1.parkCar();
        car1.startCar();
        engine1.engineDetails();
        wheel1.wheelDetails();

        Car car2 = new Car("Pagani","Utopia","Hyper Car","Shiny black","XTRAC 7-speed Transversal AMT",2020,true,"3.1 million");
        Car.Engine engine2 = car2.new Engine("V12 5890 CC Twin Turbo Chargers","815 horse power");
        Car.Wheel wheel2 = car2.new Wheel("265/35ZR21 at the front and 325/30ZR22 at the back", "Durable Cast Aluminum","Off White");
        car2.testDrive();
        car2.parkCar();
        car2.startCar();
        engine2.engineDetails();
        wheel2.wheelDetails();

        Car car3 = new Car("Bugatti ","Chiron","Hyper car","Black with Blue Stripe","7-speed dual-clutch automatic",2023,false,"3 million");
        Car.Engine engine3 = car3.new Engine("8.0 L (488 cu in) quad-turbocharged WR16","1,479 Horse Power");
        Car.Wheel wheel3 = car3.new Wheel("7.6 m", "Forged Aluminium Alloy","Mat-black");
        car3.testDrive();
        car3.parkCar();
        car3.startCar();
        engine3.engineDetails();
        wheel3.wheelDetails();

        Sofa sofa1 = new Sofa("Nial","chaise","wool",7,false,true,12.3,3.4);
        sofa1.buySofa();
        sofa1.sit();
        sofa1.adjustSofa();

        Sofa sofa2 = new Sofa("Lenny","Chesterfield","Velvet",5,true,false,0,0);
        sofa2.buySofa();
        sofa2.sit();
        sofa2.adjustSofa();

        Sofa sofa3 = new Sofa("Aro","Camelback","Chenille",9,false, true,8.4, 5);
        sofa3.buySofa();
        sofa3.sit();
        sofa3.adjustSofa();

        AlarmClock clock1 = new AlarmClock("Tis-sot", "Modern Analog clock","Golden",LocalTime.of(7,30),true);
        clock1.boughtAlarmClock();
        clock1.snoozeButton();
        clock1.activeStatus();

        AlarmClock clock2 = new AlarmClock("Titan", "Digital Alarm clock","Black",LocalTime.of(2,3),true);
        clock2.boughtAlarmClock();
        clock2.snoozeButton();
        clock2.activeStatus();

        AlarmClock clock3 = new AlarmClock("Omega", "Radio Alarm clock","Silver",LocalTime.of(1,45),false);
        clock3.boughtAlarmClock();
        clock3.snoozeButton();
        clock3.activeStatus();

        Book book1 = new Book("American Psycho","Bret Easton","Psychological Fiction",false,19,2013,1200,17);
        book1.buyingBook();
        book1.readingBook();
        book1.bookCover();

        Book book2 = new Book("Iron Flame","Carlton","Fiction",false,10,2010,1000,10);
        book2.buyingBook();
        book2.readingBook();
        book2.bookCover();

        Book book3 = new Book("King of Greed","Smith Will","Biography",true,15,2018,1530,13);
        book3.buyingBook();
        book3.readingBook();
        book3.bookCover();

        Person person1 = new Person("Navin", "navin@gmail.com","1234567890",33,"male",6.3,60,"Indian");
        Person.Address address1 = new Person.Address(2347, "North Loop", "San Jose", "California");
        person1.introduce();
        person1.sharedNumber();
        person1.sendMail();
        person1.description();
        address1.getAddressInfo();

        Person person2 = new Person("Daran","daran@gmail.com","234567189",36,"male",5.9,72,"Middle eastern");
        Person.Address address2 = new Person.Address(3421, "South Loop", "San Francisco", "California");
        person2.introduce();
        person2.sharedNumber();
        person2.sendMail();
        person2.description();
        address2.getAddressInfo();

        Person person3 = new Person("Danny","danny@gmail.com","6543216789",21,"male",5.8,78,"African-American");
        Person.Address address3 = new Person.Address(1245, "East Loop", "Santa Clara", "California");
        person3.introduce();
        person3.sharedNumber();
        person3.sendMail();
        person3.description();
        address3.getAddressInfo();

        Backpack bag1 = new Backpack("Wild craft","Laptop-bag","Dark Blue",3, 5,true,true,"25 Liters");
        bag1.foundBag();
        bag1.waterResistant();
        bag1.fillCapacity();

        Backpack bag2 = new Backpack("Adidas","Rucksack","Dark Green",2, 4,false,true,"35 Liters");
        bag2.foundBag();
        bag2.waterResistant();
        bag2.fillCapacity();

        Backpack bag3 = new Backpack("North Face","Functional Backpack","Brown",4,3,false,true,"55 Liters");
        bag3.foundBag();
        bag3.waterResistant();
        bag3.fillCapacity();

        Jacket jacket1 = new Jacket("Green","Bomber","stretch wool","Westside",39,10,25.99,true);
        jacket1.jacketTrail();
        jacket1.stocksAvailable();
        jacket1.stockCheck();

        Jacket jacket2 = new Jacket("Olive Green","Over Coat","stretch wool","Northside",37,6,35.99,true);
        jacket2.jacketTrail();
        jacket2.stocksAvailable();
        jacket2.stockCheck();

        Jacket jacket3 = new Jacket("Black","Biker Jacket","Leather","CafeRiders",40,0,15.99,false);
        jacket3.jacketTrail();
        jacket3.stocksAvailable();
        jacket3.stockCheck();

        CoffeeMachine coffee1 = new CoffeeMachine("Cold Brew Coffee Maker","Phalcon","Caffè macchiato","Strong",true,12);
        coffee1.getNumCoffeeWanted();
        coffee1.makeCoffee();
        coffee1.madeCoffee();

        CoffeeMachine coffee2 = new CoffeeMachine("Siphon Coffee Maker","Falcon","Black Coffee","Light",false,13);
        coffee2.getNumCoffeeWanted();
        coffee2.makeCoffee();
        coffee2.madeCoffee();

        CoffeeMachine coffee3 = new CoffeeMachine("Espresso machine","CoffeeDay","Espresso","Medium",true,20);
        coffee3.getNumCoffeeWanted();
        coffee3.makeCoffee();
        coffee3.madeCoffee();
    }
}