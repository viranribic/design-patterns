#include <iostream>
#include <assert.h>
#include <stdlib.h>
//using namespace std;

  struct Point{
    int x; int y;
  };
  struct Shape{
    enum EType {circle, square, rhomb};
    EType type_;
  };
  struct Circle{
     Shape::EType type_;
     double radius_;
     Point center_;
  };
  struct Square{
     Shape::EType type_;
     double side_;
     Point center_;
  };
  struct Rhomb{
    Shape::EType type_;
    double diagonalp_;
    double diagonalq_;
    Point center_;
  };

  void drawSquare(struct Square*){
    std::cerr <<"in drawSquare\n";
  }
  void drawCircle(struct Circle*){
    std::cerr <<"in drawCircle\n";
  }
  void drawShapes(Shape** shapes, int n){
    for (int i=0; i<n; ++i){
      struct Shape* s = shapes[i];
      switch (s->type_){
      case Shape::square:
        drawSquare((struct Square*)s);
        break;
      case Shape::circle:
        drawCircle((struct Circle*)s);
        break;
      default:
        assert(0); exit(0);
      }
    }
  }

  void moveSquare(struct Square* sqr, Point* offset){
    std::cerr << "Sqaure of center "<<sqr->center_.x<<" "<<sqr->center_.y<<" ";
    sqr->center_.x+=offset->x;
    sqr->center_.y+=offset->y;
    std::cerr << "moved to"<<sqr->center_.x<<" "<<sqr->center_.y<<".\n ";

  }

 void moveCircle(struct Circle* crc, Point* offset){
    std::cerr << "Circle of center "<<crc->center_.x<<" "<<crc->center_.y<<" ";
    crc->center_.x+=offset->x;
    crc->center_.y+=offset->y;
    std::cerr << "moved to "<<crc->center_.x<<" "<<crc->center_.y<<".\n ";
  }

  void moveShapes(Shape** shapes, int n, Point** offset){
    for(int i=0;i<n;i++){
      struct Shape* s=shapes[i];
      switch(s->type_){
        case Shape::square:
          moveSquare((struct Square*)s,offset[i]);
          break;
        case Shape::circle:
          moveCircle((struct Circle*)s,offset[i]);
          break;
        default:
          assert(0); exit(0);
      }
    }
  }

  int main(){
    Shape* shapes[5];
    shapes[0]=(Shape*)new Circle;
    shapes[0]->type_=Shape::circle;
    shapes[1]=(Shape*)new Square;
    shapes[1]->type_=Shape::square;
    shapes[2]=(Shape*)new Square;
    shapes[2]->type_=Shape::square;
    shapes[3]=(Shape*)new Circle;
    shapes[3]->type_=Shape::circle;

    shapes[3]=(Shape*)new Rhomb;
    shapes[3]->type_=Shape::rhomb;


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