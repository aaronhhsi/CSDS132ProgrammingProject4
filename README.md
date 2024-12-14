Programming Project 3
Due Friday, November 1

IMPORTANT: Read the Do's and Dont's in the Course Honor Policy found the Canvas site.
I. Overview

The purpose of this project is to give you practice designing a class/type hierarchy. It is important that you spend time designing your class hierarchy before you start coding. If you properly organize your classes and/or interfaces, you can achieve the desired program behavior below with significantly less code than with a poorly organized hierarchy. This project will also how there are limitations to what we can do with Java's classes and interfaces.
II. Code Readability

New for this assignment: The comments above the class or interface and above each method should be written in JavaDoc format. You will be introduced to JavaDoc style commenting in the labs. You can also find a description in the Java in a Nutshell text. Be sure to run JavaDoc and view the webpage in order to verify that you have implemented the comments correctly.

Your code should follow the following guideline:

    All variables (fields, parameters, local variables) should be given appropriate and descriptive names.
    All variable and method names should start with a lowercase letter. All class and interface names should start with an uppercase letter.
    The class body should be organized so that all the fields are at the top of the file, the constructors are next, the non-static methods next, and the static methods at the bottom.
    There should not be two statements on the same line.
    All code should be properly indented (see page 644 of the Lewis book for an example of good style). The amount of indentation is up to you, but it should be at least 2 spaces, and it should be used consistently throughout the code.
    You should be consistent in your use of {, }. The closing } should be on its own line and indented the same amount as the line containing the opening {.
    There should be an empty line between each method.
    There should be a space separating each operator from its operands as well as a space after each comma.
    There should be a comment at the top of the file that is in proper JavaDoc format and includes both your name and a description of what the class represents. The comment should include tags for the author.
    There should be a comment directly above each method (including constructors) that is in proper JavaDoc format and states what task the method is doing, not how it is doing it. The comment should include tags for any parameters, return values and exceptions, and the tags should include appropriate comments that indicate the purpose of the inputs, the value returned, and the meaning of the exceptions.
    There should be a comment directly above each field that, in one line, states what the field is storing.
    There should be a comment either above or to the right of each non-field variable indicating what the variable is storing. Any comments placed to the right should be aligned so they start on the same column.
    There should be a comment above each loop that indicates the purpose of the loop. Ideally, the comment would consist of any preconditions (if they exist) and the subgoal for the loop iteration.
    Any code that is complicated should have a short comment either above it or aligned to the right that explains the logic of the code.

III. Program Testing

New for this assignment: The testing routines should be included in a JUnit test class.

You are to submit both:

    A written testing report idenfying the kinds of tests needed to thoroughly test your project.
    One (or more) JUnit test files that test the shape classes of your project.

The testing report does not need to list the actual tests done. Those will be in the JUnit files. The test should describe what kinds of tests are needed to demonstrate that your shape classes behave correctly. How many different scenarios (i.e. conditions) are needed to cover the classes. If there are loops involved, how do you define the "test 0, test 1, test many" and "test first, test middle, test last" guidelines for that loop? Imagine that you are writing the testing report for your boss who does not know about programming details, but needs to have confidence that your code works.

For the JUnit tests, you should mostly be using either assertEquals, assertDoubleEquals, or assertArrayEquals methods. If you wish to test that an exception is thrown in certain situations, you cause use try/catch where you can have the fail method of JUnit run if the exception is not thrown.

You do not have to write JUnit tests for the DrawingPad class.

Testing inherited routines: You are not required to have tests for methods that a class inherits but does not override. However, many companies will require you to write tests for them. The reason is that later updates may choose to override the methods and you want the tests already there for when that happens. A very good practice is to write your tests before you design your program based on what the desired final results are, and then the tests will verify that the classes perform correctly regardless of how you decide to create your hierarchy.
IV. Java Programming (60% of your grade)

Design Rules: Your project must contain the following types and each type must contain the listed methods. The project may use any combination of classes, abstract classes, or interfaces that you feel is appropriate. The project may add additional types to the ones listed. The classes/types may contain additional methods to the ones listed if you feel they are needed. You may use any combination of inheritance, method overriding, and method overloading to achieve the needed behavior. Part of the coding grade will be the quality of the hierarchy you create.

Hint (repeated from above): Spend a lot of time designing your hierarchy before you code. A well designed hierarchy will reduce the amount of code you have to write.
Programming (60% of the project grade)

This project will have you build a hierarchy of shapes similar to, but also different from, the lecture hierarchy. To that hierarchy, we are going to add a canvas window so that your program can draw the shapes you create.
Part 1, Creating a hierarchy of types

In addition to the listed methods, each shape class should override the equals method.

    Because of small math errors that occur during arithmetic on double values, two points should be considered "equal" if their corresponding coordinates are withing .000001 of each other.
    Define the other shapes to be equal if they have equal points. You can decide how you want to handle shapes, like polygons, where the points can be listed in different orders.

Your project should contain the following types. Each type can be a class, abstract class, or an interface. You are welcome (and probably should) create any additional types, public, and private methods as you feel are needed.
Please note: the descriptions of these methods are what the method should do, not how you are to program it. Spend time thinking about how to organize your code before you code. Your goal is to create a good hierarchy that lets you achieve all the behavior below without writing a large amount of code.

    Point: The Point type consists of two double values and represents a 2-dimensional point. The Point type should have the following methods:
        getX returns the x-coordinate of the point
        getY returns the y-coordinate of the point
        setX takes an double as input and changes the x-coordinate of the point.
        setY takes an double as input and changes the y-coordinate of the point.
        rotateAbout takes a Point and a double as input. The double is an angle, in radians, and the method should rotate this point about the input point by the input angle. That means, treat the input point as the origin and rotate this point.
        To treat the input point as the origin, you subtract this point's x-coordinate by the input point's x-coordinate, and subtract this point's y-coordinate by the input point's y-coordinate. Then you do the rotation:
        x' = x cos t - y sin t
        y' = x sin t + y cos t
        and then add the input points x-coordinate and y-coordinates to the x' and y' values, and set the result to be this point's new coordinates.

    Line: The Line type consists of two Point values and represents a 2D line segment. A Line type instance should be created with either 4 double values representing the coordinates of the endpoints of the line, or it can be created with 2 Point values representing the endpoints of the line. The Line type should have the following methods:
        getFirstPoint: returns the first endpoint of the line.
        getSecondPoint: returns the second endpoint of the line.
        setFirstPoint: takes a Point as input and changes the first endpoint of the line.
        setSecondPoint: takes a Point as input and changes the second endpoint of the line.
        getLines: returns an array containing all Line types that make up this line (i.e. the array should contain only this line.

    Rectangle: The Rectangle type represents a rectangle. A Rectangle type instance should be created with one Point representing the center of the Rectangle plus two lengths representing the height and width. The Rectangle type should have the following methods:
        getCenter: returns a Point that represents the center of the rectangle.
        getWidth: returns the width of the rectangle.
        getHeight: returns the height of the rectangle.
        setCenter: takes a Point as input and sets the center of the rectangle to this input point.
        setWidth: takes a double as input that is the new width for the rectangle.
        setHeight: takes a double as input that is the new height for the rectangle.
        rotate: takes a double as input that represents an angle in radians, and it rotates the rectangle about its center byt the input angle.
        getPoints: returns an array consisting of the 4 Points that make up the corners of the rectangle
        getLines: returns an array containing the 4 Lines that make up the border of the rectangle.

    Square: The Square type represents a square. A Square type instance should be created with one Point representing the center of the Square plus one double representing both the height and width. The Square type should have the following methods:
        getCenter: returns a Point that represents the center of the square.
        getWidth: returns the width of the square.
        getHeight: returns the height of the square.
        setCenter: takes a Point as input and sets the center of the square to this input point.
        setWidth: takes a double as input that is the new width for the square.
        setHeight: takes a double as input that is the new height for the square.
        rotate: takes a double as input that represents an angle in radians, and it rotates the square about its center by the input angle.
        getPoints: returns an array consisting of the 4 Points that make up the corners of the square
        getLines: returns an array containing the 4 Lines that make up the border of the square.

    Triangle: The Triangle type represents a triangle. A Triangle type instance should be created with three Point values representing the three points of a triangle. The Triangle type should have the following methods:
        getCenter: returns a Point that represents the center of the triangle. The center can be calculated by taking two lines, each from one angle to the midpoint of the opposite side, and then calculating the intersection of those lines. If the end points of the first line is (x1,y1) and (x2,y2) and the end points of the second line are (x3,y3) and (x4,y4), the intersection point is:
        x = ((x1*y2 - y1*x2)*(x3-x4) - (x1-x2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4))
        y = ((x1*y2 - y1*x2)*(y3-y4) - (y1-y2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4))
        setCenter: takes a Point as input and moves the triangle so it's new center is the input point.
        rotate: takes a double as input that represents an angle in radians, and it rotates the triangle about its center by the input angle.
        getPoints: returns an array consisting of the 3 Points that make up the corners of the triangle.
        getLines: returns an array consisting of the 3 Lines that make up the border of the triangle.

    Polygon: The Polygon type represents an arbitrary polygon. A Polygon type instance should be created with a variable length input (or array) of (at least three) Point values representing the points that make up the polygon. The Polygon type should have the following methods:
        getCenter: returns a Point that represents the "center" of the polygon. Since this is an arbitrary polygon, the center will be defined as the center of the bounding rectangle of the polygon. (The top edge of the bounding rectangle is at the top most point of the polygon, the left edge of the bounding rectangle is at the leftmost point of the polygon, and so forth.)
        setCenter: takes a Point as input and moves the polygon so it's new center is the input point.
        rotate: takes a double as input that represents an angle in radians, and it rotates the polygon about its center by the input angle. (Note that the rotation could change the center of the polygon. That is okay since we are only roughly defining a center given that this polygon is completely arbitrary.)
        getPoints: returns an array consisting of the Points that make up the polygon.
        getLines: returns an array consisting of the Lines that make up the polyhon.

    NGon: The NGon type represents regular polygon with an arbitrary number of sides. A NGon type instance should be created with one Point representing the center of the NGon plus one double representing the side length. The NGon type should have the following methods:
        getCenter: returns a Point that represents the center of the polygon.
        getSideLength: returns the length of each side of the polygon.
        getNumSides: returns the number of sides of the polygon.
        setCenter: takes a Point as input and moves the polygon so that its center is the input point.
        setSideLength: takes a double as input that is the new length of each side of the polygon.
        rotate: takes a double as input that represents an angle in radians, and it rotates the polygon about its center by the input angle.
        getPoints: returns an array consisting of the n Points that make up the corners of the polygon.
        getLines: returns an array containing the n Lines that make up the edges of the polygon.
        Here is a "simple" way to calculate the points and/or lines. The distance from the center of the polygon to the midpoint of a side is n / (2 tan(Pi / n)). From that, you can set the end points of one side. Then repeat for each side, of the n-gon. Place point k twice as far away from point (k-2) as point (k-1) is, on a straight line, and then rotate that point about point (k-1) by the size of the interior/exterior angle. The interior angle is calculated by Pi (n-2) / n.

    EquilateralTriangle: The EquilateralTriangle type represents triangle with three equal length sides. A EquilaterlTriangle type instance should be created with one Point representing the center of the EquilateralTriangle plus one double representing the side length. The EquilateralTriangle type should have the following methods:
        getCenter: returns a Point that represents the center of the triangle.
        getSideLength: returns the length of each side of the triangle.
        setCenter: takes a Point as input and moves the triangle so that its center is the input point.
        setSideLength: takes a double as input that is the new length of each side of the triangle.
        rotate: takes a double as input that represents an angle in radians, and it rotates the triangle about its center by the input angle.
        getPoints: returns an array consisting of the 3 Points that make up the corners of the triangle.
        getLines: returns an array containing the 3 Lines that make up the edges of the triangle.

Part 2: Creating a canvas/drawing area

The "drawing pad" will be a window with a display that lets you draw the shapes created above.

    The DrawingPad class. The DrawingPad class will create a canvas where you can draw shapes. The DrawingPad class should have the following constructor:
        The constructor takes two int values as input, and these will be the size of the canvas. Your constructor needs to do the following:
            Create a JFrame object.
            Create a Canvas object. A Canvas is a Java Swing object that we can draw on.
            Call the setSize method of Canvas using the values input to the constructor. This will set the size of the drawing area.
            Call the setBackground method of Canvas to set the canvas's background to Color.WHITE.
            Create a JPanel.
            Call the add method of JPanel, and make the input be the Canvas instance.
            Call the add method of JFrame, and make the input be the JPanel instance.
            Call the pack method of JFrame with no inputs. (A simple, but crude, way to get the JFrame to automatically size to fit the canvas.
            Display the JFrame.
    The Drawing class should have the following methods:

        Graphics getGraphicsContext(): The method takes no input and returns the graphics context you need to draw on a canvas. You get the graphics context from the canvas by calling Canvas's getGraphics method. However, once you get the graphics context out of from the canvas you should store it. Repeated calls to this method should then return the stored value instead of calling Canvas's getGraphics method a second time. Important: Some versions (the Oracle version in particular) of the Java JDK have a glitch where, if you keep calling Canvas's getGraphics method after you received a graphics context the drawing will stop working properly.

        The method should work as follows. If you already have a Graphics instance stored, return it. Otherwise, call the getGraphics method of Canvas to get the canvas's graphics context and return it. (Before returning it, you will want to store it!) Also, the first time you get a valid graphics context, call it's setColor method to set the color of the graphics context to Color.BLACK. Note: the getGraphics method of Canvas will return null if the canvas is not yet ready to be drawn on (for example, the JFrame is not visible). You need to check for this (or catch an exception) so you don't call the setColor method on a null object.

        draw : There should be a method draw that takes a single input. You need a version of the method that works for all shapes of the project. The method will use the above method to get the graphics context and then draw (in black) the shape on the canvas. The easiest way to draw is to use the drawLine method of Java.awt.Graphics to draw each line of the input shape.

        erase: The method should take no input, it gets the graphics context using the above method, changes the color to Color.WHITE, uses the fillRect method of java.awt.Graphics to fill the entire canvas area with white, and then changes the color of the graphics context back to Color.BLACK.

