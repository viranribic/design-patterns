#include "Unary_Function.h"
#include <malloc.h>

PTRFUN unaryFunctionsMethods[]={
	(doubleFuncPtr)NULL,
	(doubleFuncPtr)negative_value_at
};

 UnaryFunction* new_UnaryFunction(int lb, int ub){
	UnaryFunction* ufunc=(UnaryFunction*)malloc(sizeof(UnaryFunction));
	
	ufunc->lowerBound=lb;
	ufunc->upperBound=ub;
	
	ufunc->table=unaryFunctionsMethods;

	return ufunc;
}

double negative_value_at(UnaryFunction* self,double x){
	doubleFuncPtr pfun = (doubleFuncPtr)self->table[0];
	return -pfun(self,x);
}

void tabulate(UnaryFunction* self){
	int x;
	doubleFuncPtr pFun=(doubleFuncPtr)self->table[0];
	for(x=self->lowerBound;x<=self->upperBound;x++){
		printf("f(%d)=%lf\n", x, pFun(self,x));
	}
}

int same_functions_for_ints(UnaryFunction* f1, UnaryFunction*f2, double tolerance){
	int x;
	if(f1->lowerBound != f2->lowerBound) return 0;
	if(f1->upperBound != f2->upperBound) return 0;

	doubleFuncPtr pfun1 = (doubleFuncPtr)f1->table[0];
    doubleFuncPtr pfun2 = (doubleFuncPtr)f2->table[0];

	for(x=f1->lowerBound;x<=f1->upperBound;x++){
		double delta= pfun1(f1,x) - pfun2(f2,x);
		if(delta<0) delta= -delta;
		if(delta>tolerance) return 0;
	}
	return 1;
}