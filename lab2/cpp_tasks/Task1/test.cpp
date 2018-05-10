#include "client.hpp"
#include "derived.hpp"
#include "derived2.hpp"
#include "derived3.hpp"

//Program 1:
/*
int main(){
	Derived d;
	Client c(d);
	c.operate();
}
*/

//Program 2
/*
int main(){
	Derived2 d;
	Client c(d);
	c.operate();
}
*/

//Program 3
int main(){
	Derived3 d;
	Client c(d);
	c.operate();
}