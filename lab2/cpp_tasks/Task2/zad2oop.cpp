#include <iostream>
#include <stdlib.h>

typedef struct {
	int x;int y;
} Point;


class Shape{
public:
	virtual void draw()=0;
	virtual void move(Point* offset)=0;
};

class Circle: public Shape{
private:
	Point center_;
	double radius_;
public:
	virtual void draw(){ std::cerr <<"in drawCircle\n"; }
	virtual void move(Point* offset){
		std::cerr << "Moving Circle by "<<offset->x<<" "<<offset->y<<".\n";
	}
};

class Square: public Shape{
private:
	Point center_;
	double side_;
public:
	virtual void draw(){ std::cerr <<"in drawSquare\n"; }
	virtual void move(Point* offset){
		std::cerr << "Moving Square by "<<offset->x<<" "<<offset->y<<".\n";
	}
};

class Rhomb: public Shape{
private:
	Point center_;
	double diagonalp_;
	double diagonalq_;
public:
	virtual void draw(){ std::cerr <<"in drawRhomb\n"; }
	virtual void move(Point* offset){
		std::cerr << "Moving Rhomb by "<<offset->x<<" "<<offset->y<<".\n";
	}
};

void drawShapes(Shape** shapes,int n){
	for(int i=0;i<n;i++)
		shapes[i]->draw();
}

void moveShapes(Shape** shapes,int n, Point** offsets){
	for(int i=0;i<n;i++)
		shapes[i]->move(offsets[i]);
}

int main(){
	Shape* shapes[5];
    shapes[0]=(Shape*)new Circle;
    shapes[1]=(Shape*)new Square;
    shapes[2]=(Shape*)new Square;
    shapes[3]=(Shape*)new Circle;
    shapes[4]=(Shape*)new Rhomb;
    
    
    Point* offsets[5];
    offsets[0]=new Point;
    offsets[0]->x=1;
    offsets[0]->y=1;
    
    offsets[1]=new Point;
    offsets[1]->x=2;
    offsets[1]->y=2;
    
    offsets[2]=new Point;
    offsets[2]->x=3;
    offsets[2]->y=3;
    
    offsets[3]=new Point;
    offsets[3]->x=4;
    offsets[3]->y=4;

    offsets[4]=new Point;
    offsets[4]->x=5;
    offsets[4]->y=5;
    
    drawShapes(shapes, 5);
    moveShapes(shapes, 5 , offsets);
}
