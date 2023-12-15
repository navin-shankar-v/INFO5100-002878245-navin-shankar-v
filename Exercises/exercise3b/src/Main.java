import java.io.*;
import java.util.Random;

import static java.lang.System.out;

abstract class Shape implements Serializable{
    private final String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract float calculateAreaOfShape();

    public abstract float calculatePerimeterOfShape();

    public String getColor() {
        return color;
    }
}

class Square extends Shape {
    float sideOfSquare;

    public Square(String color, float sideOfSquare) {
        super(color);
        this.sideOfSquare = sideOfSquare;
    }

    @Override
    public float calculateAreaOfShape() {
        float squareArea;
        squareArea = sideOfSquare * sideOfSquare;
        return squareArea;
    }

    @Override
    public float calculatePerimeterOfShape() {
        float squarePerimeter;
        squarePerimeter = 4 * sideOfSquare;
        return squarePerimeter;
    }
}

class Circle extends Shape {
    float circleRadius;

    public Circle(String color, float circleRadius) {
        super(color);
        this.circleRadius = circleRadius;
    }

    @Override
    public float calculateAreaOfShape() {
        float circleArea;
        circleArea = (float) (Math.PI * (circleRadius * circleRadius) / 2);
        return circleArea;
    }

    @Override
    public float calculatePerimeterOfShape() {
        float circlePerimeter;
        circlePerimeter = (float) (2 * Math.PI * circleRadius);
        return circlePerimeter;
    }
}

class Triangle extends Shape {
    float triangleBase;
    float triangleHeight;

    public Triangle(String color, float triangleBase, float triangleHeight) {
        super(color);
        this.triangleBase = triangleBase;
        this.triangleHeight = triangleHeight;
    }

    @Override
    public float calculateAreaOfShape() {
        float triangleArea;
        triangleArea = (triangleBase * triangleHeight) / 2;
        return triangleArea;
    }

    @Override
    public float calculatePerimeterOfShape() {
        float trianglePerimeter;
        trianglePerimeter = triangleBase + triangleHeight + (float) Math.sqrt(triangleBase * triangleBase + triangleHeight * triangleHeight);
        return trianglePerimeter;
    }
}

class Rectangle extends Shape {
    float rectangleLength;
    float rectangleBreath;

    public Rectangle(String color, float rectangleLength, float rectangleBreath) {
        super(color);
        this.rectangleBreath = rectangleBreath;
        this.rectangleLength = rectangleLength;
    }

    @Override
    public float calculateAreaOfShape() {
        float rectangleArea;
        rectangleArea = rectangleLength * rectangleBreath;
        return rectangleArea;
    }

    @Override
    public float calculatePerimeterOfShape() {
        float rectanglePerimeter;
        rectanglePerimeter = 2 * (rectangleLength * rectangleBreath);
        return rectanglePerimeter;
    }
}

class ShapeInfo {
    // Displaying shape information
    public void displayShapeInfo(Shape shape) {
        out.println(shape.getClass().getSimpleName() + " Shape Info");
        out.println("Area: " + shape.calculateAreaOfShape());
        out.println("Perimeter: " + shape.calculatePerimeterOfShape());
        out.println("Color: " + shape.getColor());
        out.println();
    }

    public void seriliazedShapeInfo(Shape shape){
        out.println("Deserialized "+shape.getClass().getSimpleName()+" Shape Info");
        out.println("Area: " + shape.calculateAreaOfShape());
        out.println("Perimeter: " + shape.calculatePerimeterOfShape());
        out.println("Color: " + shape.getColor());
        out.println();
    }
}

class SerializeDeserialize {
    public static void serialize(Shape shape, String shapeData) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(shapeData);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(shape);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Shape deserialize(String shapeData) {
        try (FileInputStream fileInputStream = new FileInputStream(shapeData);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Shape) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        ShapeInfo info = new ShapeInfo();


        // Generating random input values for each shape
        float squareSide = random.nextFloat() * 10.0f;
        float circleRadius = random.nextFloat() * 5.0f;
        float triangleBase = random.nextFloat() * 8.0f;
        float triangleHeight = random.nextFloat() * 10.0f;
        float rectangleLength = random.nextFloat() * 12.0f;
        float rectangleBreadth = random.nextFloat() * 9.0f;

        // Created instances of different shapes with random inputs
        Shape square = new Square("Red", squareSide);
        Shape circle = new Circle("Green", circleRadius);
        Shape triangle = new Triangle("Black", triangleBase, triangleHeight);
        Shape rectangle = new Rectangle("Blue", rectangleLength, rectangleBreadth);

        // Calculated and displayed area and perimeter for each shape
        info.displayShapeInfo(square);
        info.displayShapeInfo(circle);
        info.displayShapeInfo(triangle);
        info.displayShapeInfo(rectangle);

        SerializeDeserialize.serialize(square, "square.ser");
        SerializeDeserialize.serialize(circle, "circle.ser");
        SerializeDeserialize.serialize(triangle, "triangle.ser");
        SerializeDeserialize.serialize(rectangle, "rectangle.ser");

        Shape deserializedSquare = SerializeDeserialize.deserialize("square.ser");
        Shape deserializedCircle = SerializeDeserialize.deserialize("circle.ser");
        Shape deserializedTriangle = SerializeDeserialize.deserialize("triangle.ser");
        Shape deserializedRectangle = SerializeDeserialize.deserialize("rectangle.ser");

        info.seriliazedShapeInfo(deserializedSquare);
        info.seriliazedShapeInfo(deserializedCircle);
        info.seriliazedShapeInfo(deserializedTriangle);
        info.seriliazedShapeInfo(deserializedRectangle);
    }
}

