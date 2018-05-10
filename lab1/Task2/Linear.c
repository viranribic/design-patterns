#include "Unary_Function.h"
#include "Linear.h"
#include <malloc.h>

PTRFUN linearMethods[]={
	(doubleFuncPtr)value_at_Lin,
	(doubleFuncPtr)negative_value_at
};

Linear* new_Linear(int lb, int ub, double a_coef, double b_coef){
	Linear* linear=(Linear *)malloc(sizeof(Linear));
	linear->table=linearMethods;
	linear->lowerBound=lb;
	linear->upperBound=ub;
	linear->a=a_coef;
	linear->b=b_coef;
	return linear;
}

double value_at_Lin(UnaryFunction* self,double x){
	Linear *lin=(Linear*) self;
	return (lin->a)*x+(lin->b);
}