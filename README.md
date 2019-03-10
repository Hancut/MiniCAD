# MiniCAD

It's a project that represents a mini-replica of autoCAD. The input is read from a file like so: first line represents the number of shapes that will be draw on the canvas,
next line must be writen canvas's dimensions + color + transperecy and after that different geometrics forms (squares, lines, rectangles, circles, polygons).
In this project I've used next design patterns: Visitor (shapes are visitable and function of drawing is visitor), Factory (create new forms/shapes), Singleton (ShapeFactory - is only one factory).
