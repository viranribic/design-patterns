#include <iostream>

class PlainOldClass{
  public:
    void setX(int x){x_=x;};
    int getX(){return x_;};
    void setY(int y){y_=y;};
    int getY(){return y_;};
  private:
    int x_;
    int y_;
  };

 class Base{
  public:
    //if in doubt, google "pure virtual"
    virtual void set(int x)=0;
    virtual int get()=0;
  };


  class CoolClass: public Base{
  public:
    virtual void set(int x){x_=x;};
    virtual int get(){return x_;};
  private:
    int x_;
  };
  
  int main(){
    PlainOldClass poc;
    Base* pb=new CoolClass;
    poc.setX(42);
    pb->set(42);
    poc.setY(44);
  }  
