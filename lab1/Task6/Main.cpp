#include <iostream>
#include <stdio.h>

typedef int(*fPtr)(void*);

class B{
public:
  virtual int prva()=0;
  virtual int druga()=0;
};

class D: public B{
public:
  virtual int prva(){return 0;}
  virtual int druga(){return 42;}
};

int main(){
	B* obj=new D();
	unsigned int vTable=*(unsigned int*)obj;

	fPtr func1=(fPtr)(*(unsigned int*)(vTable+0*sizeof(fPtr)));
	fPtr func2=(fPtr)(*(unsigned int*)(vTable+1*sizeof(fPtr)));
	
	printf("Funkcija 1 res: %d\n", func1(obj));
	printf("Funkcija 2 res: %d\n", func2(obj));

    return 0;
}